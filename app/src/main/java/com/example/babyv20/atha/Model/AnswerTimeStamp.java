package com.example.babyv20.atha.Model;;

/**
 * Created by ao43 on 23/04/15.
 * This class collects the time stamp for each answered
 * checklist question per patient per treatment procedure
 */
public class AnswerTimeStamp {

    private String timeStamp, treatmentName;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }
}
