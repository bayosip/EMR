package com.example.babyv20.atha.Controller.Fragments;

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

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.FragmentListner;
import com.example.babyv20.atha.Model.Patients.PatientEmployer;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.R;

/**
 * Created by BABY v2.0 on 3/3/2017.
 */

public class Child_Employer extends Fragment implements AppFragment{

    private FragmentListner listner;
    private PatientEmployer employer;
    private EditText mEmployer, street, city, state, postcode, occupation, industry;
    private Spinner country, sOccupation, sIndustry;
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
        View view = inflater.inflate(R.layout.child_employer, container, false);
        employer = new PatientEmployer();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view) {
        street = (EditText)view.findViewById(R.id.editTextEmpAddress);
        city = (EditText)view.findViewById(R.id.editTextEmpCity);
        state = (EditText)view.findViewById(R.id.editTextEmpState);
        postcode = (EditText)view.findViewById(R.id.editTextEmpPostCode);
        occupation = (EditText)view.findViewById(R.id.editTextOccupation);
        industry = (EditText)view.findViewById(R.id.editTextEmergencyPhone);
        mEmployer = (EditText)view.findViewById(R.id.editTextEmployer);
        sOccupation = (Spinner)view.findViewById(R.id.spinnerOccupation);
        sIndustry = (Spinner)view.findViewById(R.id.spinnerIndustry);
        country = (Spinner)view.findViewById(R.id.spinnerCountry);
        save = (Button)view.findViewById(R.id.buttonSave_employer);
        save.setOnClickListener(saveForm);
    }

    @Override
    public void grabDataFromView() {
        StringBuilder address = new StringBuilder();
        address.append(street.getText().toString());
        address.append(", ");
        address.append(city.getText().toString());
        address.append(", ");
        address.append(state.getText().toString());
        address.append(", ");
        address.append(postcode.getText().toString());
        address.append(", ");
        address.append(String.valueOf(country.getSelectedItem()));

        employer.setpEmployer(mEmployer.getText().toString());
        employer.setpEmployAddress(address.toString());
        String strInd= String.valueOf(sIndustry.getSelectedItem());
        String strOcc = String.valueOf(sOccupation.getSelectedItem());

        if (strInd.equalsIgnoreCase("Other")) employer.setpIndustry(industry.getText().toString());
        else employer.setpIndustry(strInd);

        if (strOcc.equalsIgnoreCase("Other")) employer.setpOccupation(occupation.getText().toString());
        else employer.setpOccupation(strOcc);
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getPatientEmployer(employer);
        }
    };
}
