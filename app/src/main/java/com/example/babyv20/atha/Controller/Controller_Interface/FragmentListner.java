package com.example.babyv20.atha.Controller.Controller_Interface;

import com.example.babyv20.atha.Model.Patients.Patient;
import com.example.babyv20.atha.Model.Patients.PatientContact;
import com.example.babyv20.atha.Model.Patients.PatientEmployer;
import com.example.babyv20.atha.Model.Patients.PatientGuardian;
import com.example.babyv20.atha.Model.Patients.PatientInsurance;
import com.example.babyv20.atha.Model.Patients.PatientMedIssues;
import com.example.babyv20.atha.Model.Patients.PatientMisc;
import com.example.babyv20.atha.Model.Patients.PatientStats;
import com.example.babyv20.atha.Model.Patients.PatientVisits;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.Model.Patients.PatientChoices;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public interface FragmentListner extends App_activity{

    void getFormInfoFromFragment();
    void getPatientChoices(PatientChoices choices);
    void getPatientContact (PatientContact contact);
    void getPatientEmployer(PatientEmployer employer);
    void getPatientGuardian(PatientGuardian guardian);
    void getPatientInsurance(PatientInsurance insurance);
    void getPatientMisc(PatientMisc misc);
    void getStat(PatientStats stats);
    void getWho (PatientWho who);
}