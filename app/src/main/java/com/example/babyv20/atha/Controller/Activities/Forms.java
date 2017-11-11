package com.example.babyv20.atha.Controller.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import com.example.babyv20.atha.Controller.Controller_Interface.FragmentListner;
import com.example.babyv20.atha.Controller.Fragments.PatientFormPageAdapter;
import com.example.babyv20.atha.Model.DatabaseAdapter;
import com.example.babyv20.atha.Model.Patients.Patient;
import com.example.babyv20.atha.Model.Patients.PatientInsurance;
import com.example.babyv20.atha.Model.Patients.PatientMisc;
import com.example.babyv20.atha.Model.Patients.PatientStats;
import com.example.babyv20.atha.Model.Patients.PatientWho;
import com.example.babyv20.atha.Model.Patients.PatientChoices;
import com.example.babyv20.atha.Model.Patients.PatientContact;
import com.example.babyv20.atha.Model.Patients.PatientEmployer;
import com.example.babyv20.atha.Model.Patients.PatientGuardian;
import com.example.babyv20.atha.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by BABY v2.0 on 3/1/2017.
 */

public class Forms extends AppCompatActivity implements FragmentListner {
    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private boolean mBound = false;
    private ImageButton register;
    private Fragment contactFrag, whoFrag, guardianFrag, employerFrag, insuranceFrag,
                    miscFrag, choiceFrag, statFrag;
    private List<Fragment> fragList;
    private Spinner langSelctor;
    private static Patient patient;
    private int fragCounter;
    private DatabaseAdapter dbHandler;
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private  String[] headers ;

    static {
        patient = new Patient();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
        fragList = new LinkedList<>();
        dbHandler = new DatabaseAdapter(this);
        headers = getResources().getStringArray(R.array.demo_list);
        mPagerAdapter = new PatientFormPageAdapter(getSupportFragmentManager(), headers);
        setupToolBar();
        enableSaveButton ();
        mPager.setCurrentItem(getExtra());
    }

    private Locale locale = null;

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (locale != null)
        {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getApplicationContext().createConfigurationContext(newConfig);
            getApplicationContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    private int getExtra (){
        return getIntent().getIntExtra("Fragment Number", 0);
    }


    @Override
    public void restartInLocale(String sLocale) {
        {
            Resources res = getApplicationContext().getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale(sLocale.toLowerCase());
            res.updateConfiguration(conf, dm);
            recreate();
        }
    }

    @Override
    public void setupToolBar() {
        setupWidgets();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setupWidgets() {
        toolbar = (Toolbar) findViewById(R.id.appbar_form);
        register = (ImageButton) findViewById(R.id.fabRegister);
        register.setEnabled(false);
        mPager = (ViewPager)findViewById(R.id.containerForms);
        mPager.setAdapter(mPagerAdapter);
        register.setOnClickListener(buttoClick);
        langSelctor = (Spinner) findViewById(R.id.spinnerToolLng);
        langSelctor.setOnItemSelectedListener(changAppLang);
    }

    private void enableSaveButton (){
        if (mPager.getCurrentItem()== fragList.size()-1)
            register.setEnabled(true);
    }

    Spinner.OnItemSelectedListener changAppLang = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String lang = String.valueOf(parent.getSelectedItem());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void getFormInfoFromFragment() {

    }

    @Override
    public void getPatientChoices(PatientChoices choices) {

        patient.setChoices(choices);
    }

    @Override
    public void getPatientContact(PatientContact contact) {
        patient.setContact(contact);
    }

    @Override
    public void getPatientEmployer(PatientEmployer employer) {
        patient.setEmployer(employer);
    }

    @Override
    public void getPatientGuardian(PatientGuardian guardian) {
        patient.setGuardian(guardian);
    }

    @Override
    public void getPatientInsurance(PatientInsurance insurance) {
            patient.setInsurance(insurance);
    }

    @Override
    public void getPatientMisc(PatientMisc misc) {
        patient.setMisc(misc);
    }

    @Override
    public void getStat(PatientStats stats) {
        patient.setStats(stats);
    }

    @Override
    public void getWho(PatientWho who) {
        patient.setWho(who);
    }

    Button.OnClickListener buttoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fabRegister:
                    saveToDatabase();
                    startActivity(new Intent(Forms.this, PatientRegistration.class)
                    .putExtra("Patient Name", patient.getWho().getPatientName()));
                    finish();
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.logOutLink:
                Intent logOut = new Intent(Forms.this, HomeScreen.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logOut);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveToDatabase(){
        dbHandler.addPatient(patient);
    }
}
