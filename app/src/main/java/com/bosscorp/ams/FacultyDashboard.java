package com.bosscorp.ams;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FacultyDashboard extends AppCompatActivity {

    boolean exit = false;
    Button addattendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        addattendance = findViewById(R.id.attendance);

        addattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddAttendance.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {


        if (exit) {
            System.exit(0);
        } else {
            Toast.makeText(this, "PRESS AGAIN TO EXIT",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
