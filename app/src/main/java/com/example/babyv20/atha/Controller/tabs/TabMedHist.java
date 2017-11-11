package com.example.babyv20.atha.Controller.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.babyv20.atha.Controller.Controller_Interface.AppFragment;
import com.example.babyv20.atha.Controller.Controller_Interface.TabListner;
import com.example.babyv20.atha.Model.Patients.PatientMedIssues;
import com.example.babyv20.atha.R;


public class TabMedHist extends Fragment implements AppFragment {

    private Spinner medIssues,  occurrence, outcome;
    private EditText beginDate, medSpecifics, endDate, referredBy;
    private TextView name;
    private Button saveMedHist;
    private PatientMedIssues medHistory;
    private Context context;
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
        View view = inflater.inflate(R.layout.tab_patient_health_info, container, false);
        medHistory = new PatientMedIssues();
        initialiseWidgets(view);
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


        medIssues = (Spinner)view.findViewById(R.id.spinnerMedicalIssueType);
        medSpecifics = (EditText)view.findViewById(R.id.editTextIssueTitile);
        occurrence = (Spinner)view.findViewById(R.id.spinnerOccurrence);
        outcome = (Spinner)view.findViewById(R.id.spinnerOutcome);
        beginDate = (EditText) view.findViewById(R.id.editTextStartDate);
        endDate = (EditText) view.findViewById(R.id.editTextEndDate);
        referredBy = (EditText) view.findViewById(R.id.editTextReferrals);
        saveMedHist = (Button) view.findViewById(R.id.buttonSaveIssue);
        saveMedHist = (Button) view.findViewById(R.id.buttonSaveIssue);
        saveMedHist.setOnClickListener(saveForm);
        name = (TextView)view.findViewById(R.id.textViewPatientName_iss);
        name.setText(getPatient());
    }


    @Override
    public void grabDataFromView() {
        medHistory.setpName(name.getText().toString());
        medHistory.setIssType(String.valueOf(medIssues.getSelectedItem()));
        medHistory.setIssSpecifics(medSpecifics.getText().toString());
        medHistory.setIssBeginDate(beginDate.getText().toString());
        medHistory.setIssEndDate(endDate.getText().toString());
        medHistory.setIssOccurrence(String.valueOf(occurrence.getSelectedItem()));
        medHistory.setIssReferrals(referredBy.getText().toString());
        medHistory.setIssOutcome(String.valueOf(outcome.getSelectedItem()));
    }

    Button.OnClickListener saveForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabDataFromView();
            listner.saveMedIssueToDatabase(medHistory);
        }
    };
}
