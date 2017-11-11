package com.example.babyv20.atha.Model.Patients;;

/**
 * Created by Adebayo on 18/02/2015.
 */
public class PatientWho {

    private String  patientName;
    private String patientSex;
    private String patientDOB;
    private String socialID;
    private String licenseID;
    private String maritalStatus;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String email;
    private String billingNote;

    public PatientWho() {
    }

    public String getBillingNote() {
        return billingNote;
    }

    public void setBillingNote(String billingNote) {
        this.billingNote = billingNote;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPatientSex(String patientSex) {this.patientSex = patientSex;}

    public void setPatientDOB(String patientDOB) {this.patientDOB = patientDOB;}

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

}
