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
import com.example.babyv20.atha.Model.Patients.PatientMisc;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by BABY v2.0 on 3/3/2017.
 */

public class Child_Misc extends Fragment implements AppFragment{

    private PatientMisc misc;
    private FragmentListner listner;
    private EditText date, reason;
    private Button save;
    private Context context;
    private Calendar aDate = Calendar.getInstance(TimeZone.getDefault());

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
        View view = inflater.inflate(R.layout.child_miscellaneous, container, false);
        misc = new PatientMisc();
        initialiseWidgets(view);
        return view;
    }

    @Override
    public void initialiseWidgets(View view) {
        date = (EditText) view.findViewById(R.id.editTextDeceased);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, dateListener, aDate.get(Calendar.YEAR),
                        aDate.get(Calendar.MONTH),aDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        reason = (EditText) view.findViewById(R.id.editTextReasonDeceased);
        save = (Button) view.findViewById(R.id.buttonSave_misc);
        save.setOnClickListener(saveForm);
    }

    @Override
    public void grabDataFromView() {
       misc.setDateOfDeath(date.getText().toString());
        misc.setReason(reason.getText().toString());
    }

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            date.setText(new StringBuilder().append(String.valueOf(year))
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

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.getPatientMisc(misc);
        }
    };
}


