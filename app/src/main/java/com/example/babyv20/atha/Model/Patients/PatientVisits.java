package com.example.babyv20.atha.Model.Patients;

/**
 * Created by BABY v2.0 on 3/5/2017.
 */

public class PatientVisits {

    private String pName, category, hospFacility, billFacility,
                    sensitivity, dateOfService, OnsetDate;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHospFacility() {
        return hospFacility;
    }

    public void setHospFacility(String hospFacility) {
        this.hospFacility = hospFacility;
    }

    public String getBillFacility() {
        return billFacility;
    }

    public void setBillFacility(String billFacility) {
        this.billFacility = billFacility;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(String dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getOnsetDate() {
        return OnsetDate;
    }

    public void setOnsetDate(String onsetDate) {
        OnsetDate = onsetDate;
    }
}
