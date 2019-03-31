package com.bosscorp.ams;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FacultyDashboard extends AppCompatActivity {

    boolean exit = false;
    LinearLayout add_attendance,add_student,view_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        add_attendance = findViewById(R.id.add_attendance);
        add_student = findViewById(R.id.add_student);
        view_report = findViewById(R.id.view_report);

        add_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                startActivity(i);
            }
        });
        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddStudent.class);
                startActivity(i);
            }
        });
        view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
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
