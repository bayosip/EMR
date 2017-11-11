package com.example.babyv20.atha.Model.Patients;

import java.io.Serializable;

/**
 * Created by BABY v2.0 on 3/4/2017.
 */

public class Patient implements Serializable {

    private PatientChoices choices;
    private PatientWho who;
    private PatientContact contact;
    private PatientEmployer employer;
    private PatientGuardian guardian;
    private PatientInsurance insurance;
    private PatientMisc misc;
    private PatientStats stats;


    public Patient() {
    }

    public PatientChoices getChoices() {
        return choices;
    }

    public void setChoices(PatientChoices choices) {
        this.choices = choices;
    }

    public PatientWho getWho() {
        return who;
    }

    public void setWho(PatientWho who) {
        this.who = who;
    }

    public PatientContact getContact() {
        return contact;
    }

    public void setContact(PatientContact contact) {
        this.contact = contact;
    }

    public PatientEmployer getEmployer() {
        return employer;
    }

    public void setEmployer(PatientEmployer employer) {
        this.employer = employer;
    }

    public PatientGuardian getGuardian() {
        return guardian;
    }

    public void setGuardian(PatientGuardian guardian) {
        this.guardian = guardian;
    }

    public PatientInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(PatientInsurance insurance) {
        this.insurance = insurance;
    }

    public PatientMisc getMisc() {
        return misc;
    }

    public void setMisc(PatientMisc misc) {
        this.misc = misc;
    }

    public PatientStats getStats() {
        return stats;
    }

    public void setStats(PatientStats stats) {
        this.stats = stats;
    }
}
