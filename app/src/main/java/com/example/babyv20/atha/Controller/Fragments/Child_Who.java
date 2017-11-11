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
import android.widget.Spinner;

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.FragmentListner;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.R;
import com.example.babyv20.atha.Utilities.UIEssentials;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public class Child_Who extends Fragment implements AppFragment {

    private EditText firstName, surname, Initial, dateOfBirth, socialID, licenseID, billingNote;
    private Spinner title, sex, maritalStatus;
    private PatientWho who;
    private FragmentListner listner;
    private Context context;
    private Button save;
    private Calendar date = Calendar.getInstance(TimeZone.getDefault());

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
        View view = inflater.inflate(R.layout.child_who_is_patient, container, false);
        who = new PatientWho();
        initialiseWidgets(view);
        return view;
    }

    public void initialiseWidgets(View view){
        firstName = (EditText) view.findViewById(R.id.editTextFn);
        surname = (EditText) view.findViewById(R.id.editTextSn);
        Initial = (EditText) view.findViewById(R.id.editTextInitial);
        dateOfBirth = (EditText) view.findViewById(R.id.editTextDOB);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(context,listener, date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        socialID = (EditText) view.findViewById(R.id.editTextSocialID);
        licenseID =  (EditText) view.findViewById(R.id.editTextLicense);
        billingNote = (EditText) view.findViewById(R.id.editTextComments);
        title = (Spinner)view.findViewById(R.id.spinnerTitle);
        sex = (Spinner)view.findViewById(R.id.spinnerSex);
        maritalStatus = (Spinner)view.findViewById(R.id.spinnerMartialStatus);
        save = (Button)view.findViewById(R.id.buttonSave_Who);
        save.setOnClickListener(saveForm);
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getWho(who);
        }
    };
    @Override
    public void grabDataFromView() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(String.valueOf(title.getSelectedItem()));
        fullName.append(" ");
        fullName.append(firstName.getText().toString());
        fullName.append(" ");
        fullName.append(Initial.getText().toString());
        fullName.append(" ");
        fullName.append(surname.getText().toString());
        who.setPatientName(fullName.toString());
        who.setPatientSex(String.valueOf(sex.getSelectedItem()));
        who.setMaritalStatus(String.valueOf(maritalStatus.getSelectedItem()));
        who.setSocialID(socialID.getText().toString());
        who.setLicenseID(licenseID.getText().toString());
        who.setBillingNote(billingNote.getText().toString());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            int yearCurrent = date.get(Calendar.YEAR);
            int monthCurrent = date.get(Calendar.MONTH);

            int patientAge = yearCurrent - year;

            if (patientAge < 0) {
                UIEssentials.message(context, "Wrong Year! maximum year: " + String.valueOf(yearCurrent));
            }
            else {

                if (patientAge == 0){
                    int monthDiff = monthCurrent - (monthOfYear);

                    if (monthDiff<0){

                        UIEssentials.message(context, "Wrong Month! maximum Month: " +
                                String.valueOf(monthCurrent));
                    } else{
                        dateOfBirth.setText(new StringBuilder().append(String.valueOf(year))
                                .append("-")
                                .append(pad(monthOfYear+1))
                                .append("-")
                                .append(pad(dayOfMonth)));
                    }
                }
                else {
                    dateOfBirth.setText(new StringBuilder().append(String.valueOf(year))
                            .append("-")
                            .append(pad(monthOfYear+1))
                            .append("-")
                            .append(pad(dayOfMonth)));
                }
            }
        }

    };

    //Adds 0 to dates less than 10 e.g 1 -> 01
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
