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
import com.example.babyv20.atha.Model.Patients.PatientContact;
import com.example.babyv20.atha.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public class Child_Contact extends Fragment implements AppFragment {

    private EditText street, city, state, postcode, emgcontact,
            emgphone, homePhone, mobile, email;
    private Spinner country;
    private Map<View, String> formData = new HashMap<>();
    private FragmentListner listner;
    private PatientContact contact;
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
        View view = inflater.inflate(R.layout.child_contact, container, false);
        contact = new PatientContact();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view){
        street = (EditText)view.findViewById(R.id.editTextStreet);
        city = (EditText)view.findViewById(R.id.editTextCity);
        state = (EditText)view.findViewById(R.id.editTextState);
        postcode = (EditText)view.findViewById(R.id.editTextPostCode);
        emgcontact = (EditText)view.findViewById(R.id.editTextEmergencyName);
        emgphone = (EditText)view.findViewById(R.id.editTextEmergencyPhone);
        homePhone = (EditText)view.findViewById(R.id.editTextHomePhone);
        mobile = (EditText)view.findViewById(R.id.editTextMobile);
        email = (EditText)view.findViewById(R.id.editTextEmail);
        country = (Spinner)view.findViewById(R.id.spinnerCountry);
        save = (Button) view.findViewById(R.id.buttonSave_contact);
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
        contact.setpAddress(address.toString());
        contact.setpEmgContact(emgcontact.getText().toString());
        contact.setpEmgPhone(emgphone.getText().toString());
        contact.setpPhone(homePhone.getText().toString());
        contact.setpMobile(mobile.getText().toString());
        contact.setpEmail(email.getText().toString());
    }


    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getPatientContact(contact);
        }
    };
}
