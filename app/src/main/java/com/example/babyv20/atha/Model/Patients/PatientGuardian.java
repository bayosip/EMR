package com.example.babyv20.atha.Model.Patients;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public class PatientGuardian {

    private String Name, sex, relationship, address, phone, email;
    public PatientGuardian (){
    }

    public String getGName() {
        return Name;
    }

    public void setGName(String name) {
        Name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
