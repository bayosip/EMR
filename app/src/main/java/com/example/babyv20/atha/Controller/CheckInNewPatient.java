package com.example.babyv20.atha.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.babyv20.atha.Controller.Activities.HomeScreen;
import com.example.babyv20.atha.Controller.Activities.PatientOptionScreen;
import com.example.babyv20.atha.Controller.tabs.TabPagerAdapter;
import com.example.babyv20.atha.Model.DatabaseAdapter;
import com.example.babyv20.atha.Model.LogonData;
import com.example.babyv20.atha.R;

import java.util.Calendar;
import java.util.TimeZone;



public class CheckInNewPatient extends AppCompatActivity {
    private DatabaseAdapter dbHandler;
    private TabPagerAdapter tabAdapter;
    private ViewPager pages;
    private Context context = this;
    private LogonData staffLog;
    private Calendar DOBdate = Calendar.getInstance(TimeZone.getDefault());
    private Toolbar appbar;
    private Spinner language;

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_new_patient);
        dbHandler = new DatabaseAdapter(this);

        setupWidgets();
    }


    public void setupToolBar() {

        appbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
    }

    public void setupWidgets(){

        tabAdapter = new TabPagerAdapter(getSupportFragmentManager());
    }
    EditText firstName, surname, Initial, dateOfBirth, socialID, licenseID, billingNote, weight,
            doctor, ward, guardian, previousCancer, previousTreatment,
            currentMedication, otherMedication;
    String sex, mStatus, loggedOn;

    private Button regPatientButton;
    private Spinner sexSpinner, martialStatusSpinner;
    PatientWho patient;
    Treatment treat;

    private DatabaseAdapter dbHandler;
    private TabLayout tabPatient;

    Time today = new Time(Time.getCurrentTimezone());
     patient = new PatientWho();
        treat = new Treatment();
        staffLog = new LogonData();
        tabSetup();

      private String setTimeStamp(){
      today.setToNow();
        String  timestamp = today.format("%Y-%m-%d %H:%M");
        return  timestamp;
    }

    private String getWhoLoggedIn (){

        Bundle extra = getIntent().getExtras();

        if (extra == null){
            return null;
        }

        String staffName = extra.getString("Staff Name");
        return  staffName;
    }

    //Create Tabs for this activity
    private void tabSetup(){
        tabPatient = (TabLayout) findViewById(R.id.tabs);

        /*tabPatientHistory = (TabHost) findViewById(R.id.tabHostNewPatient);
        tabPatientHistory.setup();

        TabHost.TabSpec tabSpec = tabPatientHistory.newTabSpec("Personal Info");
        tabSpec.setContent(R.id.tabNewPersonalInfo);
        tabSpec.setIndicator("Personal Info");
        tabPatientHistory.addTab(tabSpec);

        tabSpec = tabPatientHistory.newTabSpec("Past Medical History");
        tabSpec.setContent(R.id.tabNewMedHistory);
        tabSpec.setIndicator("Past Medical History");
        tabPatientHistory.addTab(tabSpec);
    }*/

    //Implements widget functions like button click etc.
   /* private void setupWidgets(){

        declareWidgets();

        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(CheckInNewPatient.this,listener, DOBdate.get(Calendar.YEAR),
                        DOBdate.get(Calendar.MONTH),DOBdate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        regPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPatientInfoData();
                long id = dbHandler.addPatient(patient);
                if (id<=0){

                    ToastMessage.message(context, "Registration Unsuccessful!");
                    setEditTextToBlank("");
                }
                else{
                    staffLog.setStaffName(getWhoLoggedIn());
                    staffLog.setTimeStamp(setTimeStamp());
                    staffLog.setPatientName(firstName.getText().toString().toUpperCase() + " " +
                            surname.getText().toString().toUpperCase());
                    staffLog.setActionTaken("Registered patient in system!");

                    dbHandler.addLogins(staffLog);
                    ToastMessage.message(context, "Registration successful!");
                    setEditTextToBlank("");
                    finish();
                }
            }
        });
    }

    //Initialise all widgets in this activity
    private void declareWidgets(){

        firstName = (EditText) findViewById(R.id.editTextFn);

        surname = (EditText) findViewById(R.id.editTextSn);
        Initial = (EditText) findViewById(R.id.editTextAge);
        dateOfBirth = (EditText) findViewById(R.id.editTextDOB);
        socialID = (EditText) findViewById(R.id.editTextBloodType);
        licenseID =  (EditText) findViewById(R.id.editTextHeight);
        weight =  (EditText) findViewById(R.id.editTextWeight);
        billingNote = (EditText) findViewById(R.id.editTextDiagnosis);
        doctor =  (EditText) findViewById(R.id.editTextDoctor);
        ward =  (EditText) findViewById(R.id.editTextWard);
        guardian =  (EditText) findViewById(R.id.editTextGuardian);
        genderGroup = (RadioGroup) findViewById(R.id.sexOrientationRG);
        previousCancer = (EditText) findViewById(R.id.editTextCancerHistComment);
        previousTreatment = (EditText) findViewById(R.id.editTextPastTreatment);
        currentMedication = (EditText) findViewById(R.id.editTextCurrentMedication);
        otherMedication = (EditText) findViewById(R.id.editTextOtherMedication);
        cancerYesNo = (RadioGroup) findViewById(R.id.previousCancerRG);
        regPatientButton = (Button) findViewById(R.id.buttonSaveNewPatient);

        firstName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    regPatientButton.setEnabled(!firstName.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //Store data collected from activity into patient object
    private  void  setPatientInfoData(){
        String name  = firstName.getText().toString()+ " " +
                surname.getText().toString();
        String fullName = name.toUpperCase();
        patient.setPatientName(fullName);
        ToastMessage.message(context,patient.getPatientName());

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton female = (RadioButton) findViewById(R.id.radioButtonFemale);
                RadioButton male = (RadioButton) findViewById(R.id.radioButtonMale);

                if (female.isChecked()) {
                    sex = "Female";
                } else if (male.isChecked()) {
                    sex = "Male";
                }
            }
        });

        cancerYesNo.setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton yes = (RadioButton) findViewById(R.id.radioButtonCancerYes);
                RadioButton no = (RadioButton) findViewById(R.id.radioButtonCancerNo);

                if (yes.isChecked()){
                    mStatus ="Yes";
                } else if (no.isChecked()){

                    mStatus = "No";
                }
            }
        });

        patient.setPatientSex(sex);
        patient.setPatientAge (integerParser(Initial.getText().toString()));
        patient.setPatientDOB(dateOfBirth.getText().toString());
        patient.setPatientBloodType(socialID.getText().toString());
        patient.setPatientHeight(integerParser(licenseID.getText().toString()));
        patient.setPatientWeight(integerParser(weight.getText().toString()));
        patient.setPatientDiagnosis(billingNote.getText().toString());
        patient.setPatientDoctor(doctor.getText().toString());
        patient.setPatientWard(ward.getText().toString());
        patient.setPatientGuardian(guardian.getText().toString());
        patient.setPreviousCancer(mStatus + ", " + previousCancer.getText().toString());
        patient.setPreviousTreatment(previousTreatment.getText().toString());
        patient.setCurrentMedication(currentMedication.getText().toString());
        patient.setOtherMedication(otherMedication.getText().toString());
    }


    //Sets the date of birth and Age by calling a DatePickerDialog
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
           /* String year1 = String.valueOf(year);
            String month1 = String.valueOf(monthOfYear + 1);
            String day1 = String.valueOf(dayOfMonth);

            int yearCurrent = DOBdate.get(Calendar.YEAR);
            int monthCurrent = DOBdate.get(Calendar.MONTH);

            int patientAge = yearCurrent - year;

            if (patientAge < 0) {
                ToastMessage.message(context, "Wrong Year! maximum year: " + String.valueOf(yearCurrent));
            }
            else {

                if (patientAge == 0){
                    int monthDiff = monthCurrent - (monthOfYear);

                    if (monthDiff<0){

                        ToastMessage.message(context, "Wrong Month! maximum Month: " + String.valueOf(monthCurrent));
                    } else{
                        dateOfBirth.setText(new StringBuilder().append(String.valueOf(year))
                                .append("-")
                                .append(pad(monthOfYear+1))
                                .append("-")
                                .append(pad(dayOfMonth)));
                        Initial.setText(String.valueOf(monthDiff) + " months");
                    }
                }
                else {
                    dateOfBirth.setText(new StringBuilder().append(String.valueOf(year))
                            .append("-")
                            .append(pad(monthOfYear+1))
                            .append("-")
                            .append(pad(dayOfMonth)));
                    if (monthCurrent < (monthOfYear)) {
                        Initial.setText(String.valueOf(patientAge - 1) + " Years");
                    } else {
                        Initial.setText(String.valueOf(patientAge)+ " Years");
                    }
                }
            }
        }

    };

    //Adds 0 to dates less than 10 e.g 1 -> 01
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    //Method is called to set all Edit texts in the activity back to being blank for a new entry
    private void setEditTextToBlank(String str){
        firstName.setText(str);
        surname.setText(str);
        dateOfBirth.setText(str);
        Initial.setText(str);
        socialID.setText(str);
        licenseID.setText(str);
        weight.setText(str);
        billingNote.setText(str);
        doctor.setText(str);
        ward.setText(str);
        guardian.setText(str);
        previousCancer.setText(str);
        previousTreatment.setText(str);
        currentMedication.setText(str);
        otherMedication.setText(str);
    }

    //Change String values to numbers
    private int integerParser (String str){
        int parser = 0;
        try {

            parser = Integer.parseInt(str);

        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return  parser;
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.PatientManagementOptionLink:
                Intent backToOptions = new Intent(CheckInNewPatient.this, PatientOptionScreen.class);
                startActivity(backToOptions);
                backToOptions.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                return true;
            case R.id.logOutLink:
                Intent logOut = new Intent(CheckInNewPatient.this, HomeScreen.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logOut);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
