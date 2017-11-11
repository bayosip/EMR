package com.example.babyv20.atha.Controller.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babyv20.atha.Model.DatabaseAdapter;
import com.example.babyv20.atha.*;
import com.example.babyv20.atha.Model.Patients.Patient;

public class SearchPatient extends ActionBarActivity {

    EditText patientName;
    Button searchButton;
    DatabaseAdapter dbHandler;
    Context context = this;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        dbHandler = new DatabaseAdapter(context);
        extras= new Bundle();
        setupWidgets();


}
    private String getWhoLoggedIn (){

        Bundle staffMemberLoggedOn = getIntent().getExtras();

        if (staffMemberLoggedOn == null){
            return null;
        }
        String staffName = staffMemberLoggedOn.getString("Staff Name");
        return  staffName;
    }

    public void setupWidgets(){
        searchButton =(Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                patientName = (EditText) findViewById(R.id.patientINameSearch);

                String search = patientName.getText().toString().toUpperCase();
                Patient info = dbHandler.getPatient(search);

                     if (info != null){
                         extras.putSerializable("Patient", info);
                         extras.putString("Staff Name", getWhoLoggedIn());

                        //startActivity(new Intent(SearchPatient.this, PatientScreen.class).putExtras(extras));
                        Toast.makeText(getApplicationContext(),
                                "PatientWho found!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Sorry, PatientWho not found!", Toast.LENGTH_SHORT).show();
                    };
            }
        });
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
                startActivity(new Intent(SearchPatient.this, PatientOptionScreen.class));
                return true;
            case R.id.logOutLink:
                Intent logOut = new Intent(SearchPatient.this, HomeScreen.class);
                logOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logOut);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
