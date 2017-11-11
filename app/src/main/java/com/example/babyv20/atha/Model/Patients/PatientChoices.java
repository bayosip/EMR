package com.example.babyv20.atha.Model.Patients;

/**
 * Created by BABY v2.0 on 3/2/2017.
 */

public class PatientChoices {

    private String modeContact, allowSms, allowEmail, allowInfoEx, allowRegUSe;
    public PatientChoices (){

    }

    public String getModeContact() {
        return modeContact;
    }

    public void setModeContact(String modeContact) {
        this.modeContact = modeContact;
    }

    public String getAllowSms() {
        return allowSms;
    }

    public void setAllowSms(String allowSms) {
        this.allowSms = allowSms;
    }

    public String getAllowEmail() {
        return allowEmail;
    }

    public void setAllowEmail(String allowEmail) {
        this.allowEmail = allowEmail;
    }

    public String getAllowInfoEx() {
        return allowInfoEx;
    }

    public void setAllowInfoEx(String allowInfoEx) {
        this.allowInfoEx = allowInfoEx;
    }

    public String getAllowRegUSe() {
        return allowRegUSe;
    }

    public void setAllowRegUSe(String allowRegUSe) {
        this.allowRegUSe = allowRegUSe;
    }
}
