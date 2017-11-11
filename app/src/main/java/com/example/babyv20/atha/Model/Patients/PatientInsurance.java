package com.example.babyv20.atha.Model.Patients;

/**
 * Created by BABY v2.0 on 3/4/2017.
 */

public class PatientInsurance {

    private String provider, schemeName, memberName, policyNumber, dateValid;

    public PatientInsurance() {
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getDateValid() {
        return dateValid;
    }

    public void setDateValid(String dateValid) {
        this.dateValid = dateValid;
    }
}
