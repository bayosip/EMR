package com.example.babyv20.atha.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.babyv20.atha.Model.Patients.Patient;
import com.example.babyv20.atha.Model.Patients.PatientMedIssues;
import com.example.babyv20.atha.Model.Patients.PatientVisits;
import com.example.babyv20.atha.Utilities.UIEssentials;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Adebayo on 15/02/2015.
 */
public class DatabaseAdapter  {

    private DataBaseHandler dbHandler;
    private boolean notNull = false;
    private final static String TAG = DatabaseAdapter.class.getSimpleName();

    //Adding new rows to each table in database
   private Context context;

    public DatabaseAdapter(Context context) {
        this.context =context;
        this.dbHandler = new DataBaseHandler(context);
        this.dbHandler.getWritableDatabase();
    }

    public long addPatient(Patient patient){
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(patient);
            oos.flush();
            oos.close();
            bos.close();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            //TODO: Handle the exception
            Log.e(TAG, ex.toString());

        }

        try{

        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        values.put(dbHandler.KEY_PI_NAME, patient.getWho().getPatientName());
        values.put(dbHandler.KEY_PI_OBJECT, bytes);
        /*values.put(dbHandler.KEY_PI_DOB, patient.getPatientDOB());
        values.put(dbHandler.KEY_PI_GUARDIAN, patient.getPatientGuardian());
        values.put(dbHandler.KEY_PI_WARD, patient.getPatientWard());
        values.put(dbHandler.KEY_PI_DOCTOR, patient.getPatientDoctor());
        values.put(dbHandler.KEY_PI_HEIGHT, patient.getPatientHeight());
        values.put(dbHandler.KEY_PI_WEIGHT, patient.getPatientWeight());
        values.put(dbHandler.KEY_PI_DIAGNOSIS, patient.getPatientDiagnosis());
        values.put(dbHandler.KEY_PI_BLOOD, patient.getPatientBloodType());
        values.put(dbHandler.KEY_PI_PREVIOUS_CANCER, patient.getPreviousCancer());
        values.put(dbHandler.KEY_PI_PREVIOUS_TREATMENT, patient.getPreviousTreatment());
        values.put(dbHandler.KEY_PI_CURRENT_MED, patient.getCurrentMedication());
        values.put(dbHandler.KEY_PI_OTHER_MED, patient.getOtherMedication());*/

        Log.d("Database Operation", "Registration Successful");
        long id = db.insert(dbHandler.TABLE_PATIENT_INFO, null, values);
        return  id;}
        catch (SQLiteException e){
            Log.d("Database Operation", "Error: "+e);
            return 0;
        }
    }


    public long addPatientVisit (PatientVisits visit){
       try {
           ContentValues values = new ContentValues();
           SQLiteDatabase db = dbHandler.getWritableDatabase();

           values.put(dbHandler.KEY_VISIT_PNAME, visit.getpName());
           values.put(dbHandler.KEY_VISIT_CATEGORY, visit.getCategory());
           values.put(dbHandler.KEY_VISIT_HFACILITY, visit.getHospFacility());
           values.put(dbHandler.KEY_VISIT_BFACILITY, visit.getBillFacility());
           values.put(dbHandler.KEY_VISIT_SENSITIVITY, visit.getSensitivity());
           values.put(dbHandler.KEY_VISIT_SDATE, visit.getDateOfService());
           values.put(dbHandler.KEY_VISIT_ODATE, visit.getOnsetDate());
           long id = db.insert(dbHandler.TABLE_PATIENT_VISIT, null, values);

           return id;
       }catch(SQLiteException e){
           Log.d("Database Operation", "Error: "+e);
               return 0;
           }
    }

    public long addPatientMedIssues(PatientMedIssues medIssue){
        try {
            ContentValues values = new ContentValues();
            SQLiteDatabase db = dbHandler.getWritableDatabase();

            values.put(dbHandler.KEY_ISSUES_PNAME, medIssue.getpName());
            values.put(dbHandler.KEY_ISSUES_TYPE, medIssue.getIssType());
            values.put(dbHandler.KEY_ISSUES_SPECIFICS, medIssue.getIssSpecifics());
            values.put(dbHandler.KEY_ISSUES_BDATE, medIssue.getIssBeginDate());
            values.put(dbHandler.KEY_ISSUES_EDATE, medIssue.getIssEndDate());
            values.put(dbHandler.KEY_ISSUES_OCCURRENCE, medIssue.getIssOccurrence());
            values.put(dbHandler.KEY_ISSUES_REFERRAL, medIssue.getIssReferrals());
            values.put(dbHandler.KEY_ISSUES_OUTCOME, medIssue.getIssOutcome());
            long id = db.insert(dbHandler.TABLE_PATIENT_MED_ISSUE, null, values);

            return id;
        }catch(SQLiteException e){
            Log.d("Database Operation", "Error: "+e);
            return 0;
        }
    }


    public  long insertNameAnswerTimes (AnswerTimeStamp time){
        try {
            ContentValues values = new ContentValues();

            SQLiteDatabase db = dbHandler.getWritableDatabase();
            String[] COLUMN_NAMES_ANS_TIME = {dbHandler.KEY_TIMESTAMP_NAME, dbHandler.KEY_TIMESTAMP1,
                    dbHandler.KEY_TIMESTAMP2, dbHandler.KEY_TIMESTAMP3, dbHandler.KEY_TIMESTAMP4,
                    dbHandler.KEY_TIMESTAMP5, dbHandler.KEY_TIMESTAMP6, dbHandler.KEY_TIMESTAMP7,
                    dbHandler.KEY_TIMESTAMP8, dbHandler.KEY_TIMESTAMP9, dbHandler.KEY_TIMESTAMP10,
                    dbHandler.KEY_TIMESTAMP11};

            values.put(COLUMN_NAMES_ANS_TIME[0],time.getTreatmentName());
            values.put(COLUMN_NAMES_ANS_TIME[1], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[2], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[3], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[4], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[5], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[6], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[7], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[8], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[9], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[10], "Pending");
            values.put(COLUMN_NAMES_ANS_TIME[11], "Pending");
            long id = db.insert(dbHandler.TABLE_TIMESTAMP, null, values);

            return id;
        }catch(SQLiteException e){
            Log.d("Database Operation", "Error: "+e);
            return 0;
        }
    }


    public long addAnswersTimesStamp(AnswerTimeStamp time, int pos, String name){

        try {
            ContentValues values = new ContentValues();
            SQLiteDatabase db = dbHandler.getWritableDatabase();

            String[] COLUMN_NAMES = { dbHandler.KEY_TIMESTAMP_NAME, dbHandler.KEY_TIMESTAMP1,
                    dbHandler.KEY_TIMESTAMP2, dbHandler.KEY_TIMESTAMP3, dbHandler.KEY_TIMESTAMP4,
                    dbHandler.KEY_TIMESTAMP5, dbHandler.KEY_TIMESTAMP6, dbHandler.KEY_TIMESTAMP7,
                    dbHandler.KEY_TIMESTAMP8, dbHandler.KEY_TIMESTAMP9, dbHandler.KEY_TIMESTAMP10,
                    dbHandler.KEY_TIMESTAMP11};

            values.put(COLUMN_NAMES[pos +1], time.getTimeStamp());
            long id = db.update(dbHandler.TABLE_TIMESTAMP, values, COLUMN_NAMES[0] + " =?",
                    new String[]{name});

            return id;
        }catch (SQLiteException e){
            Log.d("Database Operation", "Error: "+e);
            return 0;
        }
    }

    public void addLogins(  LogonData logonData){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        values.put(dbHandler.KEY_LOGS_SNAME, logonData.getStaffName());
        values.put(dbHandler.KEY_LOGS_PNAME, logonData.getPatientName());
        values.put(dbHandler.KEY_LOGS_TIMESTAMP, logonData.getTimeStamp());
        values.put(dbHandler.KEY_LOGS_ACTION, logonData.getActionTaken());

        db.insert(dbHandler.TABLE_LOGINS, null, values);

    }

    public Patient getPatient(String name){
        Patient patient =null;
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        ByteArrayInputStream inputStream =null;
        ObjectInputStream obj = null;


        String [] COLUMN_NAMES = {dbHandler.KEY_PI_OBJECT};
        Cursor cursor = db.query(dbHandler.TABLE_PATIENT_INFO, COLUMN_NAMES, dbHandler.KEY_PI_NAME + " = '" +
                name + "'", null, null, null, null);

        byte[] bytes = cursor.getBlob(cursor.getColumnIndex(dbHandler.KEY_PI_OBJECT));
        patient = (Patient) DataCompression.decompressObject(bytes);

        return patient;
    }

    /* public String[] getPatientInfoName(String name){
        SQLiteDatabase db = dbHandler.getReadableDatabase();

       String[] COLUMN_NAMES = { dbHandler.KEY_PI_NAME, dbHandler.KEY_PI_AGE,
                dbHandler.KEY_PI_SEX, dbHandler.KEY_PI_DOB, dbHandler.KEY_PI_GUARDIAN, dbHandler.KEY_PI_WARD
                , dbHandler.KEY_PI_DOCTOR, dbHandler.KEY_PI_HEIGHT, dbHandler.KEY_PI_WEIGHT, dbHandler.KEY_PI_DIAGNOSIS
                , dbHandler.KEY_PI_BLOOD, dbHandler.KEY_PI_PREVIOUS_CANCER, dbHandler.KEY_PI_PREVIOUS_TREATMENT,
                dbHandler.KEY_PI_CURRENT_MED, dbHandler.KEY_PI_OTHER_MED};
        String [] COLUMN_NAMES = {dbHandler.KEY_PI_OBJECT};
        Cursor cursor = db.query(dbHandler.TABLE_PATIENT_INFO, COLUMN_NAMES, dbHandler.KEY_PI_NAME + " = '" +
                name + "'", null, null, null, null);

        String [] info=  new String[COLUMN_NAMES.length];
        while (cursor.moveToNext()) {
            info[0]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_NAME));
            info[1]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_AGE));
            info[2]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_SEX));
            info[3]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_DOB));
            info[4]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_GUARDIAN));
            info[5]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_WARD));
            info[6]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_DOCTOR));
            info[7]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_HEIGHT));
            info[8]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_WEIGHT));
            info[9]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_DIAGNOSIS));
            info[10]= cursor.getString(cursor.getColumnIndex(dbHandler.KEY_PI_BLOOD));
            info[11]= cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[11]));
            info[12]= cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[12]));
            info[13]= cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[13]));
            info[14]= cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[14]));
        }
        return  info;
    }*/

    /**This method accesses the TABLE_QUESTIONS table from database
    * and reads the values in column KEY_Q3 and returns these
    * values as an ArrayList<String> Object
    * */


    public String[] getAllAnswerTimes(String name) {


        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String[] COLUMN_NAMES = { dbHandler.KEY_TIMESTAMP_NAME, dbHandler.KEY_TIMESTAMP1,
                dbHandler.KEY_TIMESTAMP2, dbHandler.KEY_TIMESTAMP3, dbHandler.KEY_TIMESTAMP4,
                dbHandler.KEY_TIMESTAMP5, dbHandler.KEY_TIMESTAMP6, dbHandler.KEY_TIMESTAMP7,
                dbHandler.KEY_TIMESTAMP8, dbHandler.KEY_TIMESTAMP9, dbHandler.KEY_TIMESTAMP10,
                dbHandler.KEY_TIMESTAMP11};

        Cursor cursor = db.query(dbHandler.TABLE_TIMESTAMP, COLUMN_NAMES, dbHandler.KEY_TIMESTAMP_NAME  + " ='"+
                name + "'",null, null, null, null);

        String[] ansTimeStampList = new String[COLUMN_NAMES.length];

        while (cursor.moveToNext()) {

            ansTimeStampList[0] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP_NAME));
            ansTimeStampList[1] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP1)) ;
            ansTimeStampList[2] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP2));
            ansTimeStampList[3] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP3));
            ansTimeStampList[4] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP4));
            ansTimeStampList[5] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP5));
            ansTimeStampList[6] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP6));
            ansTimeStampList[7] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP7));
            ansTimeStampList[8] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP8));
            ansTimeStampList[9] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP9));
            ansTimeStampList[10] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP10));
            ansTimeStampList[11] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_TIMESTAMP11));
        }

        // return answers
        return ansTimeStampList;
    }

    public String[] getStaffInfo(String user){

        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String[] column_NamesHS = {dbHandler.KEY_STAFF_NAME, dbHandler.KEY_STAFF_ROLE, dbHandler.KEY_STAFF_USER_NAME,
                dbHandler.KEY_STAFF_PASS_WORD};


        Cursor cursor = db.query(dbHandler.TABLE_STAFF, column_NamesHS, dbHandler.KEY_STAFF_USER_NAME + " = '" + user + "'"
                , null, null, null, null);

        String[] staff = new String[column_NamesHS.length];
        while (cursor.moveToNext()){
            staff[0] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_STAFF_NAME));
            staff[1] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_STAFF_ROLE));
            staff[2] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_STAFF_USER_NAME));
            staff[3] = cursor.getString(cursor.getColumnIndex(dbHandler.KEY_STAFF_PASS_WORD));
        }
        return  staff;
    }



   public ArrayList<String[]> getAllPatientIssues(String name) {

        ArrayList<String[]> medIssuesList;
        medIssuesList = new ArrayList<String[]>();
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String[] COLUMN_NAMES = {dbHandler.KEY_ISSUES_PNAME, dbHandler.KEY_ISSUES_TYPE
                , dbHandler.KEY_ISSUES_SPECIFICS, dbHandler.KEY_ISSUES_BDATE, dbHandler.KEY_ISSUES_EDATE,
                dbHandler.KEY_ISSUES_OCCURRENCE, dbHandler.KEY_ISSUES_REFERRAL,
                dbHandler.KEY_ISSUES_OUTCOME};

        Cursor cursor = db.query(dbHandler.TABLE_PATIENT_MED_ISSUE, COLUMN_NAMES,
                dbHandler.KEY_ISSUES_PNAME + " = '" + name + "'", null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String[] map = new String[]{cursor.getString(cursor.getColumnIndex(
                        dbHandler.KEY_ISSUES_PNAME))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_TYPE))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_SPECIFICS))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_BDATE))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_EDATE))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_OCCURRENCE))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_REFERRAL))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_ISSUES_OUTCOME))};
                medIssuesList.add(map);
            } while (cursor.moveToNext());
        }
        return medIssuesList;
    }


    public ArrayList<String[]> getAllPatientVisits(String name) {

        ArrayList<String[]> visitList;
        visitList = new ArrayList<String[]>();
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String[] COLUMN_NAMES = {dbHandler.KEY_VISIT_PNAME, dbHandler.KEY_VISIT_CATEGORY
                , dbHandler.KEY_VISIT_HFACILITY, dbHandler.KEY_VISIT_BFACILITY,
                dbHandler.KEY_VISIT_SENSITIVITY, dbHandler.KEY_VISIT_SDATE, dbHandler.KEY_VISIT_ODATE};

        Cursor cursor = db.query(dbHandler.TABLE_PATIENT_MED_ISSUE, COLUMN_NAMES,
                dbHandler.KEY_VISIT_PNAME + " = '" + name + "'", null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String[] map = new String[]{cursor.getString(cursor.getColumnIndex(
                        dbHandler.KEY_VISIT_PNAME))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_CATEGORY))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_HFACILITY))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_HFACILITY))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_SENSITIVITY))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_SDATE))
                        , cursor.getString(cursor.getColumnIndex(dbHandler.KEY_VISIT_ODATE))};
                visitList.add(map);
            } while (cursor.moveToNext());
        }
        return visitList;
    }

    public ArrayList<String[]> getLogData( String patientName){

        try {
            ArrayList <String[]> patientActivity ;
            patientActivity= new ArrayList<String[]>();
            SQLiteDatabase db = dbHandler.getReadableDatabase();


            String[] COLUMN_NAMES ={dbHandler.KEY_LOGS_SNAME, dbHandler.KEY_LOGS_PNAME,
                     dbHandler.KEY_LOGS_ACTION, dbHandler.KEY_LOGS_TIMESTAMP};

            Cursor cursor = db.query(dbHandler.TABLE_LOGINS, COLUMN_NAMES,
                    dbHandler.KEY_LOGS_PNAME+ "='"+ patientName +"'",
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                do {

                    String[] map = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[0])),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[1])),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[2])),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[3]))};
                    patientActivity.add(map);
                }while (cursor.moveToNext());
            }

            return  patientActivity;

        }catch (SQLiteException e){

            UIEssentials.message(context, "Error "+ e);
            Log.d("Database Operation", "Error: "+e);
            return null;
        }
    }

    public String[] getSingleLogData( String patientTime){

        try {
            SQLiteDatabase db = dbHandler.getReadableDatabase();

            String[] COLUMN_NAMES ={dbHandler.KEY_LOGS_SNAME
                    , dbHandler.KEY_LOGS_TIMESTAMP};

            Cursor cursor = db.query(dbHandler.TABLE_LOGINS, COLUMN_NAMES,
                    dbHandler.KEY_LOGS_TIMESTAMP+ "='"+ patientTime +"'",
                    null, null, null, null);

                String[] patientActivity= new String[COLUMN_NAMES.length];
                while (cursor.moveToNext()){
                    patientActivity[0] =cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[0]));
                    patientActivity[1] = cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[1]));
                }

            return  patientActivity;

        }catch (SQLiteException e){
            Log.d("Database Operation", "Error: "+e);

            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getAllLogData( ){

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList <HashMap<String, String>> allActivities = new ArrayList<HashMap<String, String>>();

        String[] COLUMN_NAMES ={dbHandler.KEY_LOGS_SNAME, dbHandler.KEY_LOGS_PNAME,
                dbHandler.KEY_LOGS_TIMESTAMP, dbHandler.KEY_LOGS_ACTION};
        Cursor cursor = db.query(dbHandler.TABLE_LOGINS, COLUMN_NAMES, null, null, null, null, null);

        while (cursor.moveToNext()){

            HashMap<String,String> map = new HashMap<String, String>();

            map.put(COLUMN_NAMES[0], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[0])));
            map.put(COLUMN_NAMES[1], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[1])));
            map.put(COLUMN_NAMES[2], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[2])));
            map.put(COLUMN_NAMES[3], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[3])));
            allActivities.add(map);
        }
        return  allActivities;

    }

    public ArrayList<HashMap<String, String>> getTodaysVisits(String today ){

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList <HashMap<String, String>> allHospVisits = new ArrayList<HashMap<String, String>>();

        String[] COLUMN_NAMES ={dbHandler.KEY_VISIT_PNAME, dbHandler.KEY_VISIT_CATEGORY,
                dbHandler.KEY_VISIT_HFACILITY, dbHandler.KEY_VISIT_BFACILITY,
                dbHandler.KEY_VISIT_SENSITIVITY, dbHandler.KEY_VISIT_SDATE, dbHandler.KEY_VISIT_ODATE};
        Cursor cursor = db.query(dbHandler.TABLE_PATIENT_VISIT, COLUMN_NAMES,
                dbHandler.KEY_LOGS_TIMESTAMP+ "='"+ today +"'", null, null, null, null);

        while (cursor.moveToNext()){

            HashMap<String,String> map = new HashMap<>();

            map.put(COLUMN_NAMES[0], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[0])));
            map.put(COLUMN_NAMES[1], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[1])));
            map.put(COLUMN_NAMES[2], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[2])));
            map.put(COLUMN_NAMES[3], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[3])));
            map.put(COLUMN_NAMES[4], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[4])));
            map.put(COLUMN_NAMES[5], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[5])));
            map.put(COLUMN_NAMES[6], cursor.getString(cursor.getColumnIndex(COLUMN_NAMES[6])));
            allHospVisits.add(map);
        }
        return  allHospVisits;

    }




        static class DataBaseHandler extends  SQLiteOpenHelper {

            private static final int DATABASE_VERSION = 1;

            private static final String DATABASE_NAME = "Patient Info.db";

            private static final String TABLE_PATIENT_INFO = "PATIENT_INFO";
            private static final String TABLE_PATIENT_VISIT = "PATIENT_VISIT";
            private static final String TABLE_PATIENT_MED_ISSUE = "PATIENT_MEDICAL_ISSUES";
            private static final String TABLE_STAFF = "HOSPITAL_STAFF";
            private static final String TABLE_LOGINS = "LOGINS";
            private static final String TABLE_TIMESTAMP = "TIMESTAMP";

            private static final String KEY_PI_ID = "_patientID";
            private static final String KEY_PI_NAME = "patientName";
            private static final String KEY_PI_OBJECT = "PatientObject";

            /*private static final String KEY_PI_AGE = "patientAge";
            private static final String KEY_PI_SEX = "patientSex";
            private static final String KEY_PI_DOB = "patientDOB";
            private static final String KEY_PI_GUARDIAN = "patientGuardian";
            private static final String KEY_PI_WARD = "patientWard";
            private static final String KEY_PI_DOCTOR = "patientDoctor";
            private static final String KEY_PI_HEIGHT = "patientHeight";
            private static final String KEY_PI_WEIGHT = "patientWeight";
            private static final String KEY_PI_DIAGNOSIS = "patientDiagnosis";
            private static final String KEY_PI_BLOOD = "patientBloodType";
            private static final String KEY_PI_PREVIOUS_CANCER = "PreviousIllness";
            private static final String KEY_PI_PREVIOUS_TREATMENT = "PreviousTreatment";
            private static final String KEY_PI_CURRENT_MED = "CurrentMedication";
            private static final String KEY_PI_OTHER_MED = "OtherMedication";*/


            private static final String KEY_LOGS_SNAME = "StaffName" ;
            private static final String KEY_LOGS_PNAME = "PatientName";
            private static final String KEY_LOGS_TIMESTAMP = "AnswerTimeStamp";
            private static final String KEY_LOGS_ACTION = "ActionTaken";

            private static final String KEY_STAFF_NAME ="Name";
            private static final String KEY_STAFF_ROLE = "Role";
            private static final String KEY_STAFF_USER_NAME = "Username";
            private static final String KEY_STAFF_PASS_WORD = "Password";

            private static final String KEY_VISIT_PNAME = "Name";
            private static final String KEY_VISIT_CATEGORY = "Category";
            private static final String KEY_VISIT_HFACILITY= "HospitalFacility";
            private static final String KEY_VISIT_BFACILITY = "BillingFacility";
            private static final String KEY_VISIT_SENSITIVITY = "Sensitivity";
            private static final String KEY_VISIT_SDATE = "ServiceDate";
            private static final String KEY_VISIT_ODATE = "OnsetDate";

            private static final String KEY_ISSUES_PNAME = "Name";
            private static final String KEY_ISSUES_TYPE = "IssueType";
            private static final String KEY_ISSUES_SPECIFICS ="Specifics" ;
            private static final String KEY_ISSUES_BDATE = "BeginDate" ;
            private static final String KEY_ISSUES_EDATE = "EndDate" ;
            private static final String KEY_ISSUES_OCCURRENCE = "Occurrence" ;
            private static final String KEY_ISSUES_REFERRAL = "Referrals" ;
            private static final String KEY_ISSUES_OUTCOME = "Outcome" ;



            private static final String KEY_TIMESTAMP_NAME = "TimeStampName";
            private static final String KEY_TIMESTAMP1 = "TimeStamp1";
            private static final String KEY_TIMESTAMP2 = "TimeStamp2";
            private static final String KEY_TIMESTAMP3 = "TimeStamp3";
            private static final String KEY_TIMESTAMP4 = "TimeStamp4";
            private static final String KEY_TIMESTAMP5 = "TimeStamp5";
            private static final String KEY_TIMESTAMP6 = "TimeStamp6";
            private static final String KEY_TIMESTAMP7 = "TimeStamp7";
            private static final String KEY_TIMESTAMP8 = "TimeStamp8";
            private static final String KEY_TIMESTAMP9 = "TimeStamp9";
            private static final String KEY_TIMESTAMP10 = "TimeStamp10";
            private static final String KEY_TIMESTAMP11 = "TimeStamp11";




            private Context context;

            public DataBaseHandler(Context context)//, String name, SQLiteDatabase.CursorFactory factory,int version)
            {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                Log.d("Database Operations", "Database Created Successfully!");
                this.context = context;
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

                try {

                    String queryPatient = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_INFO +
                            " (" + KEY_PI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_PI_NAME
                            + " TEXT, " + KEY_PI_OBJECT + "BLOB);";

                    String queryVisits =  "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_VISIT + " ("
                            + KEY_VISIT_PNAME + " TEXT, " + KEY_VISIT_CATEGORY + " TEXT, "
                            + KEY_VISIT_HFACILITY + " TEXT, " + KEY_VISIT_BFACILITY + " TEXT, "
                            + KEY_VISIT_SENSITIVITY + " TEXT, "+ KEY_VISIT_SDATE + " DATETIME, "
                            + KEY_VISIT_ODATE + " DATETIME);";

                    String queryMedIssues = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_MED_ISSUE
                            + " ("  + KEY_ISSUES_PNAME + " TEXT, "  +  KEY_ISSUES_TYPE + " TEXT, "
                            + KEY_ISSUES_SPECIFICS + " TEXT, " + KEY_ISSUES_BDATE + " DATETIME, "
                            + KEY_ISSUES_EDATE + " DATETIME, " + KEY_ISSUES_OCCURRENCE + " TEXT, "
                            + KEY_ISSUES_REFERRAL + " TEXT, " + KEY_ISSUES_OUTCOME + " TEXT);";

                   /*String queryPI = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_INFO + " (" +
                            KEY_PI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_PI_NAME + " TEXT, " +
                            KEY_PI_AGE + " TEXT, " + KEY_PI_SEX + " TEXT, " +
                            KEY_PI_DOB + " DATETIME, " + KEY_PI_GUARDIAN + " TEXT, " +
                            KEY_PI_WARD + " TEXT, " + KEY_PI_DOCTOR + " TEXT, " +
                            KEY_PI_HEIGHT + " INTEGER, " + KEY_PI_WEIGHT + " INTEGER, " +
                            KEY_PI_DIAGNOSIS + " TEXT, " + KEY_PI_BLOOD + " TEXT, " +
                            KEY_PI_PREVIOUS_CANCER +" TEXT, "+ KEY_PI_PREVIOUS_TREATMENT +" TEXT, "
                            + KEY_PI_CURRENT_MED +" TEXT, "+ KEY_PI_OTHER_MED +" TEXT"+");";*/


                    String queryHS = "CREATE TABLE " + TABLE_STAFF + " (" +
                            KEY_STAFF_NAME + " TEXT, " + KEY_STAFF_ROLE + " TEXT, "
                        + KEY_STAFF_USER_NAME + " VARCHAR(255), "
                            + KEY_STAFF_PASS_WORD + " VARCHAR(255));";

                    String queryLog = "CREATE TABLE IF NOT EXISTS " + TABLE_LOGINS + "(" +
                            KEY_LOGS_SNAME + " TEXT, " + KEY_LOGS_PNAME + " TEXT, " +
                            KEY_LOGS_TIMESTAMP + " TEXT, " + KEY_LOGS_ACTION
                            + " TEXT" + ");";


                    String queryTimeStamp = "CREATE TABLE IF NOT EXISTS " + TABLE_TIMESTAMP + "(" +
                            KEY_TIMESTAMP_NAME + " TEXT, " + KEY_TIMESTAMP1 + " TEXT, "+
                            KEY_TIMESTAMP2 + " TEXT, "+ KEY_TIMESTAMP3 + " TEXT, "+
                            KEY_TIMESTAMP4 + " TEXT, "+ KEY_TIMESTAMP5 + " TEXT, "+
                            KEY_TIMESTAMP6 + " TEXT, "+ KEY_TIMESTAMP7 + " TEXT, "+
                            KEY_TIMESTAMP8 + " TEXT, "+ KEY_TIMESTAMP9 + " TEXT, "+
                            KEY_TIMESTAMP10 + " TEXT, "+ KEY_TIMESTAMP11 + " TEXT"+ ");";

                    db.execSQL(queryPatient);
                    db.execSQL(queryHS);
                    db.execSQL(queryVisits);
                    db.execSQL(queryMedIssues);
                    db.execSQL(queryLog);
                    db.execSQL(queryTimeStamp);

                    addHospitalStaff(db);

                    UIEssentials.message(context, "Tables Created Successfully!");
                    Log.d("Database Operation", "Tables Created Successfully!");

                }catch (SQLiteException e){
                    UIEssentials.message(context, "Database error!");
                    Log.d("Database Operation", ""+e);
                }

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try{

                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_INFO);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF );
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGINS );
                    Log.d("Database Operation", "onUpgrade method called!");
                    onCreate(db);

                }catch (SQLiteException e){
                    UIEssentials.message(context, "Database error!");
                    Log.d("Database Operation", ""+e);
                }
            }

            private void addHospitalStaff(SQLiteDatabase db) {
                try {
                    ContentValues values = new ContentValues();

                    values.put(KEY_STAFF_NAME, "Olawunmi Oduyebo");
                    values.put(KEY_STAFF_ROLE, "Admin");
                    values.put(KEY_STAFF_USER_NAME, "oo7");
                    values.put(KEY_STAFF_PASS_WORD, "oduyebo");

                    long id = db.insert(TABLE_STAFF, null, values);
                    //ToastMessage.message(context, String.valueOf(id));

                    Log.d("Database Operation", "Row " + String.valueOf(id) + "of table Hospital_Staff inserted");
                    values.put(KEY_STAFF_NAME, "Hamish Wallace");
                    values.put(KEY_STAFF_ROLE, "Doctor");
                    values.put(KEY_STAFF_USER_NAME, "hw04");
                    values.put(KEY_STAFF_PASS_WORD, "wallace");
                    db.insert(TABLE_STAFF, null, values);

                    values.put(KEY_STAFF_NAME, "Adebayo Osipitan");
                    values.put(KEY_STAFF_ROLE, "Developer");
                    values.put(KEY_STAFF_USER_NAME, "ao43");
                    values.put(KEY_STAFF_PASS_WORD, "winner");
                    db.insert(TABLE_STAFF, null, values);
                    Log.d("Database Operation", "All rows in table HOSPITAL_STAFF inserted ");
                    // db.close();
                } catch (SQLiteException e) {
                    Log.d("Database Operation", "" + e);
                }
            }



           /* private long addPatientBruce( SQLiteDatabase db){
                try{
                ContentValues values = new ContentValues();

                values.put(KEY_PI_NAME, "BRUCE WAYNE");
                values.put(KEY_PI_AGE, 10);
                values.put(KEY_PI_SEX, "Male");
                values.put(KEY_PI_DOB, "03-10-2005");
                values.put(KEY_PI_GUARDIAN, "Alfred Pennyworth");
                values.put(KEY_PI_WARD, "B-4");
                values.put(KEY_PI_DOCTOR, "Doctor Hugo Strange");
                values.put(KEY_PI_HEIGHT, 150);
                values.put(KEY_PI_WEIGHT,50);
                values.put(KEY_PI_DIAGNOSIS, "Brain Tumor");
                values.put(KEY_PI_BLOOD, "AA");
                values.put(KEY_PI_PREVIOUS_CANCER, "No");
                values.put(KEY_PI_PREVIOUS_TREATMENT, "None");
                values.put(KEY_PI_CURRENT_MED, "Ibuprofen and Paracetamol");
                values.put(KEY_PI_OTHER_MED, "None");

                long id = db.insert(TABLE_PATIENT_INFO, null, values);
                    Log.d("Database Operation", "Bruce Registered Successful");
                return  id;
            }catch(SQLiteException e){
                    Log.d("Database Operation", ""+e);
                return 0;
            }
            }

            public long addTimeStampBruce(SQLiteDatabase db){
                try{

                    ContentValues values = new ContentValues();

                    values.put(KEY_TIMESTAMP_NAME, "BRUCE WAYNE: Brain Tumour Surgery");
                    values.put(KEY_TIMESTAMP1,"2015-02-15 08:45");
                    values.put(KEY_TIMESTAMP2,"2015-02-15 08:46");
                    values.put(KEY_TIMESTAMP3,"2015-02-15 08:47");
                    values.put(KEY_TIMESTAMP4,"2015-02-15 08:48");
                    values.put(KEY_TIMESTAMP5,"2015-02-15 08:49");
                    values.put(KEY_TIMESTAMP6,"2015-02-15 08:50");
                    values.put(KEY_TIMESTAMP7,"2015-02-15 08:51");
                    values.put(KEY_TIMESTAMP8,"2015-02-15 08:52");
                    values.put(KEY_TIMESTAMP9,"2015-02-15 08:53");
                    values.put(KEY_TIMESTAMP10,"2015-02-15 08:54");
                    long id = db.insert(TABLE_TIMESTAMP, null, values);
                    return  id;
                }catch(SQLiteException e){
                    return 0;
                }
            }*/
        }

}
