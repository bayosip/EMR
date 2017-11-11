package com.example.babyv20.atha.Controller.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.FragmentListner;
import com.example.babyv20.atha.Model.Patients.PatientStats;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.R;

/**
 * Created by BABY v2.0 on 3/3/2017.
 */

public class Child_Stat extends Fragment implements AppFragment{

    private PatientStats stats;
    private FragmentListner listner;
    private Spinner language, religion, nationality;
    private Button save;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listner = (FragmentListner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_stats, container, false);
        stats = new PatientStats();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view) {
        language = (Spinner) view.findViewById(R.id.spinnerLanguage);
        religion = (Spinner) view.findViewById(R.id.spinnerReligion);
        nationality = (Spinner) view.findViewById(R.id.spinnerNationality);
        save = (Button) view.findViewById(R.id.buttonSave_stats);
    }

    @Override
    public void grabDataFromView() {
        stats.setLanguage(String.valueOf(language.getSelectedItem()));
        stats.setNationality(String.valueOf(nationality.getSelectedItem()));
        stats.setReligion(String.valueOf(religion.getSelectedItem()));
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getStat(stats);
        }
    };
}
