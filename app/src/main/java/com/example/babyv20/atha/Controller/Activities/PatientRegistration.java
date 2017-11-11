package com.example.babyv20.atha.Controller.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.babyv20.atha.Controller.Controller_Interface.TabListner;
import com.example.babyv20.atha.Controller.tabs.TabPagerAdapter;
import com.example.babyv20.atha.Model.DatabaseAdapter;
import com.example.babyv20.atha.Model.Patients.PatientMedIssues;
import com.example.babyv20.atha.Model.Patients.PatientVisits;
import com.example.babyv20.atha.R;

public class PatientRegistration extends AppCompatActivity implements TabListner {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private TabPagerAdapter mSectionsPagerAdapter;
    private TextView pName;
    private TabLayout tabLayout;
    private DatabaseAdapter dbHandler;
    private ImageButton closeButton;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        dbHandler = new DatabaseAdapter(getApplicationContext());
        setupToolBar();
    }

    private String getWhoLoggedIn (){

        Bundle staffMemberLoggedOn = getIntent().getExtras();

        if (staffMemberLoggedOn == null){
            return null;
        }
        String staffName = staffMemberLoggedOn.getString("Staff Name");
        return  staffName;
    }

    private Bundle getPatienName (){

        Bundle name = getIntent().getExtras();
        if (name == null){
            return null;
        }
        //String patientName = name.getString("Patient Name");
        return  name;
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

            case R.id.PatientManagementOptionLink:
                Intent backToOptions = new Intent(PatientRegistration.this, PatientOptionScreen.class);
                startActivity(backToOptions);
                backToOptions.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                return true;
            case R.id.logOutLink:
                Intent logOut = new Intent(PatientRegistration.this, HomeScreen.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logOut);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setupToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        tabLayout = (TabLayout) findViewById(R.id.tabsReg);
        setupWidgets();
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.colorLogoText),
                                    ContextCompat.getColor(this, R.color.colorAccent));
    }

    @Override
    public void setupWidgets() {
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerReg);
        closeButton = (ImageButton)findViewById(R.id.fabClose);
        closeButton.setOnClickListener(closeListener);
        mSectionsPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), getPatienName());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    ImageButton.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public void restartInLocale(String locale) {

    }

    @Override
    public void saveVisitToDatabase(PatientVisits visits) {
        dbHandler.addPatientVisit(visits);
    }

    @Override
    public void saveMedIssueToDatabase(PatientMedIssues issues) {
        dbHandler.addPatientMedIssues(issues);
    }
}
