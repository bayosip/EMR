package com.example.babyv20.atha.Controller.tabs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.babyv20.atha.Controller.Activities.Forms;
import com.example.babyv20.atha.Controller.Controller_Interface.OnItemClickCallBack;
import com.example.babyv20.atha.Controller.RecyclerList.DemographListAdaptor;
import com.example.babyv20.atha.Controller.RecyclerList.ListItem;
import com.example.babyv20.atha.R;

import java.util.ArrayList;
import java.util.List;

public class TabDemographic extends Fragment implements OnItemClickCallBack{

    private DemographListAdaptor listAdaptor;
    private RecyclerView patientDemoList;
    private TextView name;
    private  String[] headers ;
    private int icons;
    private Context context;
    private String PARCEL_KEY = "data";
    private Bundle bundle;
    private String data;
    String patientName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.context= context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headers = getResources().getStringArray(R.array.demo_list);
        icons = android.R.drawable.ic_menu_add;
        getPatientName ();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_patient_demographic, container, false);
        initiateWidgets(view);
        return view;
    }

    private void getPatientName (){

        Bundle data = getArguments();

        if (data == null){
            return;
        }

        patientName = data.getString("Patient Name");

    }

    private void initiateWidgets(View view){
        patientDemoList = (RecyclerView) view.findViewById(R.id.patientDemoList);
        patientDemoList.setLayoutManager(new LinearLayoutManager(context));
        listAdaptor = new DemographListAdaptor(getList(), context, this);
        patientDemoList.setAdapter(listAdaptor);
        name = (TextView) view.findViewById(R.id.textViewPatientName_demo);
        name.setText(patientName);
    }

    private ArrayList<ListItem> getList (){
        ArrayList<ListItem> listOfUIComp = new ArrayList<>();

        for (String header: headers) {
            ListItem itemUI = new ListItem();
            itemUI.setTitle(header);
            itemUI.setResID(icons);
            listOfUIComp.add(itemUI);
        }
        return listOfUIComp;
    }

    @Override
    public void onItemClick(int listItemPosition) {
        startActivity(new Intent(getActivity(), Forms.class)
                .putExtra("Fragment Number", listItemPosition));
    }
}
