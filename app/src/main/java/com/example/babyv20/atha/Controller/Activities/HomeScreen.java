package com.example.babyv20.atha.Controller.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.babyv20.atha.Controller.Controller_Interface.App_activity;
import com.example.babyv20.atha.Model.DatabaseAdapter;
import com.example.babyv20.atha.R;
import com.example.babyv20.atha.Utilities.UIEssentials;


public class HomeScreen extends AppCompatActivity implements App_activity {

    //private static final String logMessageTag;
    private String _username, _password;
    private Context context = this;
    private Time today = new Time(Time.getCurrentTimezone());
    private EditText userName, passWord;
    private Button loginButton;
    private DatabaseAdapter dbHandler;
    private Spinner language;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        dbHandler = new DatabaseAdapter(context);
        setupWidgets();
    }

    //Create button and its functions

    private void createButton(){

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _username = userName.getText().toString();
                _password = passWord.getText().toString();

                String[] staffInfo = dbHandler.getStaffInfo(_username);

                    if (_password.equals(staffInfo[3]))
                    {
                        startActivity(new Intent(HomeScreen.this,
                                PatientOptionScreen.class).putExtra("Staff Name", staffInfo[0]));
                        setBlank();
                        UIEssentials.message(context, "Login Successful, Welcome");
                        finish();
                    }
                    else {
                        passWord.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Login unsuccessful, Try again", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    /*private String setTimeStamp(){
        today.setToNow();
        String  timestamp = today.format("%Y-%m-%d %H:%M:%S");
        return  timestamp;
    }*/

    private void setBlank(){
        userName.setText("");
        passWord.setText("");
    }


    @Override
    public void onBackPressed() {
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()){

            case R.id.exitLink:
                finish();
                finishAndRemoveTask ();
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.appbar_login);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setupWidgets() {
        userName = (EditText) findViewById(R.id.editTextUser);
        passWord = (EditText) findViewById(R.id.editTextPass);
        loginButton = (Button) findViewById(R.id.loginButton);
        createButton();
    }

    @Override
    public void restartInLocale(String locale) {

    }
}
