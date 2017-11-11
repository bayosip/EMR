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
import com.example.babyv20.atha.Model.Patients.PatientGuardian;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.R;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public class Child_Guardian extends Fragment implements AppFragment {

    private EditText guardianName, street, city, state, postcode, Phone, email;
    private FragmentListner listner;
    private PatientGuardian guardian;
    private Spinner sex, relation, country;
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
        View view = inflater.inflate(R.layout.child_guardian, container, false);
        guardian = new PatientGuardian();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view) {
        guardianName = (EditText)view.findViewById(R.id.editTextGuardName);
        street = (EditText)view.findViewById(R.id.editTextGuardStreet);
        city = (EditText)view.findViewById(R.id.editTextGuardCity);
        state = (EditText)view.findViewById(R.id.editTextGuardState);
        postcode = (EditText)view.findViewById(R.id.editTextGuardPostCode);
        Phone = (EditText)view.findViewById(R.id.editTextHomePhone);
        email = (EditText)view.findViewById(R.id.editTextEmail);
        country = (Spinner)view.findViewById(R.id.spinnerCountry);
        relation = (Spinner)view.findViewById(R.id.spinnerGuardRelationShip);
        sex = (Spinner)view.findViewById(R.id.spinnerSex);
        save = (Button) view.findViewById(R.id.buttonSave_guardian);
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
        guardian.setGName(guardianName.getText().toString());
        guardian.setSex(String.valueOf(sex.getSelectedItem()));
        guardian.setAddress(address.toString());
        guardian.setPhone(Phone.getText().toString());
        guardian.setRelationship(String.valueOf(relation.getSelectedItem()));
        guardian.setEmail(email.getText().toString());
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getPatientGuardian(guardian);
        }
    };
}
