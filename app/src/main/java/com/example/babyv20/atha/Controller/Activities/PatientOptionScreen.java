package com.example.babyv20.atha.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.babyv20.atha.Controller.CheckInNewPatient;
import com.example.babyv20.atha.Controller.Controller_Interface.App_activity;
import com.example.babyv20.atha.R;


public class PatientOptionScreen extends AppCompatActivity implements App_activity {

    private Button newPatient, registeredPatient, viewManagement;
    private Spinner langLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_option_screen);
        setupToolBar();
    }

    private String getWhoLoggedIn (){

        Bundle extra = getIntent().getExtras();

        if (extra == null){
            return null;
        }

        String staffName = extra.getString("Staff Name");
        return  staffName;
    }

    //create the functions of the two buttons
    private void createbuttons(){
        newPatient = (Button) findViewById(R.id.newButton);
        newPatient.setOnClickListener(buttonClick);

        registeredPatient = (Button) findViewById(R.id.registeredButton);
        registeredPatient.setOnClickListener(buttonClick);

        viewManagement = (Button) findViewById(R.id.viewStaffActivitiesButton);
        viewManagement.setOnClickListener(buttonClick);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.logOutLink:
                Intent logOut = new Intent(PatientOptionScreen.this, HomeScreen.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logOut);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Button.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.newButton:
                    startActivity(new Intent(PatientOptionScreen.this,
                            PatientRegistration.class).putExtra("Staff Name", getWhoLoggedIn()));
                    Toast.makeText(getApplicationContext(),
                            "New PatientWho", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.registeredButton:
                    startActivity(new Intent(PatientOptionScreen.this, SearchPatient.class)
                        .putExtra("Staff Name", getWhoLoggedIn()));
                    Toast.makeText(getApplicationContext(),
                        "Registered PatientWho", Toast.LENGTH_SHORT).show();
                break;
                case R.id.viewStaffActivitiesButton:
                    Toast.makeText(getApplicationContext(),
                        "Management List", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };



    @Override
    public void setupToolBar() {
        setupWidgets();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setupWidgets() {
        createbuttons();
        langLocale = (Spinner) findViewById(R.id.spinnerToolLng);
    }

    @Override
    public void restartInLocale(String locale) {

    }
}
