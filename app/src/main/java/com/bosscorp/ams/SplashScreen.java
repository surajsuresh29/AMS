package com.bosscorp.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sp;
    Intent StartMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sp = getApplicationContext().getSharedPreferences("choice",MODE_PRIVATE);
                if(sp.getString("user","").equals(""))
                {
                    StartMain =new Intent(getApplicationContext(),ChooseAccount.class);
                }
                else if((sp.getString("user","").equals("student")))
                {
                    StartMain=new Intent(getApplicationContext(),StudentLogin.class);
                }
                else if((sp.getString("user","").equals("faculty")))
                {
                    StartMain=new Intent(getApplicationContext(),FacultyLogin.class);
                }
                else if((sp.getString("user","").equals("parent")))
                {
                    StartMain=new Intent(getApplicationContext(),ParentLogin.class);
                }
                startActivity(StartMain);
                finish();
            }
        },4000);
    }
}
