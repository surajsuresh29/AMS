package com.bosscorp.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChooseAccount extends AppCompatActivity {

    RadioButton student;
    RadioButton faculty;
    RadioButton parent;
    Button Continue;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_account);
        student = findViewById(R.id.radioStudent);
        faculty = findViewById(R.id.radioFaculty);
        parent = findViewById(R.id.radioParent);
        Continue = findViewById(R.id.bt_continue);
        sp = getApplicationContext().getSharedPreferences("choice",MODE_PRIVATE);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(student.isChecked())
                {
                    sp.edit().putString("user", "student").apply();
                    Intent StartMain=new Intent(getApplicationContext(),StudentLogin.class);
                    startActivity(StartMain);
                    finish();
                }
                else if(faculty.isChecked())
                {
                    sp.edit().putString("user", "faculty").apply();
                    Intent StartMain=new Intent(getApplicationContext(),FacultyLogin.class);
                    startActivity(StartMain);
                    finish();
                }
                else if(parent.isChecked())
                {
                    sp.edit().putString("user", "parent").apply();
                    Intent StartMain=new Intent(getApplicationContext(),ParentLogin.class);
                    startActivity(StartMain);
                    finish();
                }
                else
                {
                    sp.edit().putString("user", "").apply();
                    Toast.makeText(ChooseAccount.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
