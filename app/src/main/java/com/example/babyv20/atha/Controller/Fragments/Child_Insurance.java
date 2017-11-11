package com.example.babyv20.atha.Controller.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.FragmentListner;
import com.example.babyv20.atha.Model.Patients.PatientInsurance;
import com.example.babyv20.atha.R;
import com.example.babyv20.atha.Utilities.UIEssentials;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by BABY v2.0 on 3/3/2017.
 */

public class Child_Insurance extends Fragment implements AppFragment {

    private FragmentListner listner;
    private PatientInsurance insurance;
    private Context context;
    private EditText provider, scheme, memberName, policyNo, validDate;
    private Calendar aDate = Calendar.getInstance(TimeZone.getDefault());
    private Button save;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.context = context;
            listner = (FragmentListner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_insurance, container, false);
        insurance = new PatientInsurance();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view) {
        provider = (EditText) view.findViewById(R.id.editTextInsProvider);
        scheme = (EditText) view.findViewById(R.id.editTextInsScheme);
        memberName = (EditText) view.findViewById(R.id.editTextInsMemberName);
        policyNo = (EditText) view.findViewById(R.id.editTextPolicyNo);


        validDate = (EditText) view.findViewById(R.id.editTextInsValidDate);
        validDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(context, dateListener, aDate.get(Calendar.YEAR),
                        aDate.get(Calendar.MONTH),aDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        save = (Button)view.findViewById(R.id.buttonSave_insurance);
        save.setOnClickListener(saveForm);
    }

    @Override
    public void grabDataFromView() {
        insurance.setProvider(provider.getText().toString());
        insurance.setSchemeName(scheme.getText().toString());
        insurance.setMemberName(memberName.getText().toString());
        insurance.setProvider(policyNo.getText().toString());
        insurance.setDateValid(validDate.getText().toString());
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getPatientInsurance(insurance);
        }
    };

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            validDate.setText(new StringBuilder().append(String.valueOf(year))
                            .append("-")
                            .append(pad(monthOfYear+1))
                            .append("-")
                            .append(pad(dayOfMonth)));
        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
