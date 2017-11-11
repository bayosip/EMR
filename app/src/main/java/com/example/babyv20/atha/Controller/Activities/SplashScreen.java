package com.example.babyv20.atha.Controller.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.babyv20.atha.R;
import com.example.babyv20.atha.Utilities.UIEssentials;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadHomePage();
    }

    private void loadHomePage(){
        UIEssentials.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startApp = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(startApp);
                finish();
            }
        }, 3000);
    }
}