package com.example.babyv20.atha.Controller.Controller_Interface;

import com.example.babyv20.atha.Model.Patients.PatientMedIssues;
import com.example.babyv20.atha.Model.Patients.PatientVisits;

/**
 * Created by BABY v2.0 on 3/9/2017.
 */

public interface TabListner extends App_activity {

    void saveVisitToDatabase (PatientVisits visits);
    void saveMedIssueToDatabase (PatientMedIssues issues);
}
