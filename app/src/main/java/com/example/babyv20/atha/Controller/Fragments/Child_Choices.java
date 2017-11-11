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
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.Model.Patients.PatientChoices;
import com.example.babyv20.atha.R;

/**
 * Created by BABY v2.0 on 3/3/2017.
 */

public class Child_Choices extends Fragment implements AppFragment {

    private FragmentListner listner;
    private Spinner modeContact, sms, email, infoEX, regUse;
    private Button save;
    private PatientChoices choices;

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
        View view = inflater.inflate(R.layout.child_choices, container, false);
        choices= new PatientChoices();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view) {

        modeContact = (Spinner)view.findViewById(R.id.spinnerBestContact);
        sms = (Spinner)view.findViewById(R.id.spinnerSMS);
        email = (Spinner)view.findViewById(R.id.spinnerAllowEmail);
        infoEX = (Spinner)view.findViewById(R.id.spinnerInfoEx);
        regUse = (Spinner)view.findViewById(R.id.spinnerImmRegUSe);
        save = (Button) view.findViewById(R.id.buttonSave_choice);
        save.setOnClickListener(saveForm);
    }

    @Override
    public void grabDataFromView() {
        choices.setModeContact(String.valueOf(modeContact.getSelectedItem()));
        choices.setAllowSms(String.valueOf(sms.getSelectedItem()));
        choices.setAllowEmail(String.valueOf(email.getSelectedItem()));
        choices.setAllowInfoEx(String.valueOf(infoEX.getSelectedItem()));
        choices.setAllowRegUSe(String.valueOf(regUse.getSelectedItem()));
    }


    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                grabDataFromView();
                listner.getPatientChoices(choices);
        }
    };
}
