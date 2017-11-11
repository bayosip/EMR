package com.example.babyv20.atha.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataCollector {

    /*
    * Loads each drop down list item with patient information
    * */
    public static  HashMap<String, String> getInfo(String[] data){

        List<String> listHeader = new ArrayList();
        HashMap<String, String> details = new HashMap();
        listHeader.add("Personal details");
        listHeader.add("PatientWho Anatomy");
        listHeader.add("PatientWho Handling");

        StringBuilder [] patientData = new StringBuilder[listHeader.size()];


       patientData[0].append("Name: " + data[0]+"\n");
        patientData[0].append("Age: " + data[1]+ "\n");
        patientData[0].append("Sex: "+ data[2] + "\n");
        patientData[0].append("Date of Birth: " + data[3]);

        patientData[1].append("Height: " + data[7]+ "cm\n");
        patientData[1].append("Weight: "+data[8] + "kg\n" );
        patientData[1].append("Blood Type: "+data[10]);

        patientData[2].append( "Parent/Guardian: " + data[4]+ "\n");
        patientData[2].append("Ward: "+data[5] + "\n" );
        patientData[2].append("Assigned Doctor: "+data[6] + "\n");
        patientData[2].append("Diagnosis: "+data[9] + "\n");

        for (int i = 0; i< listHeader.size(); i++) {
            details.put(listHeader.get(i), patientData[i].toString()); // Header, Child data
        }
        return  details;
    }
}
