package com.example.babyv20.atha.Controller.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.TabListner;
import com.example.babyv20.atha.Model.Patients.PatientVisits;
import com.example.babyv20.atha.R;


public class TabVisits extends Fragment implements AppFragment {


    private Context context;
    private PatientVisits visits;
    private Spinner visitType, sensitivity;
    private EditText hospFacility, billFacility, dateService, onsetDate;
    private Button save;
    private TextView name;
    private TabListner listner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.context= context;
            listner = (TabListner)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_patient_visits, container, false);
        visits = new PatientVisits();
        return view;
    }

    private String getPatient (){

        Bundle data = getArguments();

        if (data == null){
            return null;
        }

        String patientName = data.getString("Patient Name");
        return  patientName;
    }

    @Override
    public void initialiseWidgets(View view) {
        hospFacility = (EditText)view.findViewById(R.id.editTextFacility);
        billFacility = (EditText) view.findViewById(R.id.editTextBillingFacility);
        dateService = (EditText) view.findViewById(R.id.editTextDOS);
        onsetDate = (EditText) view.findViewById(R.id.editTextOnset);
        visitType = (Spinner) view.findViewById(R.id.spinnerVisitType);
        sensitivity = (Spinner) view.findViewById(R.id.spinnerSensitivity);
        save = (Button) view.findViewById(R.id.buttonSaveVisit);
        save.setOnClickListener(saveForm);
        name = (TextView) view.findViewById(R.id.textViewPatientName_visit);
        name.setText(getPatient());
    }

    @Override
    public void grabDataFromView() {
        visits.setCategory(String.valueOf(visitType.getSelectedItem()));
        visits.setHospFacility(hospFacility.getText().toString());
        visits.setDateOfService(dateService.getText().toString());
        visits.setOnsetDate(onsetDate.getText().toString());
        visits.setBillFacility(billFacility.getText().toString());
        visits.setSensitivity(String.valueOf(sensitivity.getSelectedItem()));
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.saveVisitToDatabase(visits);
        }
    };
}
