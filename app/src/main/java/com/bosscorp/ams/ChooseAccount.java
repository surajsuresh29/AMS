package com.bosscorp.ams;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChooseAccount extends AppCompatActivity {

    boolean exit = false;
    RadioButton student;
    RadioButton faculty;
    RadioButton parent;
    Button Continue;
    SharedPreferences sp;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_account);
        student = findViewById(R.id.radioStudent);
        faculty = findViewById(R.id.radioFaculty);
        parent = findViewById(R.id.radioParent);
        Continue = findViewById(R.id.bt_continue);
        builder = new AlertDialog.Builder(this);
        sp = getApplicationContext().getSharedPreferences("choice",MODE_PRIVATE);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(student.isChecked())
                {

                    builder.setMessage("This action cannot be reverted.\nPlease confirm your selection!\n\"STUDENT\"")
                            .setCancelable(false)
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sp.edit().putString("user", "student").apply();
                                    Intent StartMain=new Intent(getApplicationContext(),StudentLogin.class);
                                    startActivity(StartMain);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(faculty.isChecked())
                {

                    builder.setMessage("This action cannot be reverted.\nPlease confirm your selection!\n\"FACULTY\"")
                            .setCancelable(false)
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sp.edit().putString("user", "faculty").apply();
                                    Intent StartMain=new Intent(getApplicationContext(),FacultyLogin.class);
                                    startActivity(StartMain);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(parent.isChecked())
                {

                    builder.setMessage("This action cannot be reverted. \nPlease confirm your selection!\n\n\"PARENT\"")
                            .setIcon(R.drawable.confirm_alert)
                            .setCancelable(false)
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sp.edit().putString("user", "parent").apply();
                                    Intent StartMain=new Intent(getApplicationContext(),ParentLogin.class);
                                    startActivity(StartMain);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else
                {
                    builder.setMessage("Please choose an account to proceed.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sp.edit().putString("user", "").apply();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
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
