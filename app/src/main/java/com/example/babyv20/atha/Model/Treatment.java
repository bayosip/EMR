package com.example.babyv20.atha.Model;

/**
 * Created by Adebayo on 08/04/2015.
 */
public class Treatment {

    private String patientName, staffNameAdd, TreatmentCategory,
            TreatmentName, TreatmentDescription, timeStamp;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStaffNameAdd() {
        return staffNameAdd;
    }

    public void setStaffNameAdd(String staffNameAdd) {
        this.staffNameAdd = staffNameAdd;
    }

    public String getTreatmentCategory() {
        return TreatmentCategory;
    }

    public void setTreatmentCategory(String treatmentCategory) {
        TreatmentCategory = treatmentCategory;
    }

    public String getTreatmentName() {
        return TreatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        TreatmentName = treatmentName;
    }

    public String getTreatmentDescription() {
        return TreatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        TreatmentDescription = treatmentDescription;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
