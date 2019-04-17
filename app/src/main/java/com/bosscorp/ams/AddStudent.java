package com.bosscorp.ams;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class AddStudent extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference dbref = database.getReference("Students");
    String  name, batch;
    AlertDialog.Builder builder,alertDialogBuilder;
    SharedPreferences tn,rm,sp;
    boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setTitle("ADD STUDENT");
        final EditText regno = (EditText)findViewById(R.id.regno);
        final EditText contact = (EditText)findViewById(R.id.contact);
        final Button add = (Button)findViewById(R.id.add);
        tn = getApplicationContext().getSharedPreferences("faculty", MODE_PRIVATE);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        sp = getApplicationContext().getSharedPreferences("choice",MODE_PRIVATE);
        name = tn.getString("name", "");
        builder = new AlertDialog.Builder(this);
        alertDialogBuilder = new AlertDialog.Builder(AddStudent.this);
        Spinner dropdown = findViewById(R.id.course);
        String[] items = new String[]{"Int MCA 2015", "Int MCA 2016", "Int MCA 2017", "Int MCA 2018", "MCA LAT 2017", "MCA LAT 2018"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                batch = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (batch) {
                    case "Int MCA 2015":
                        switch (name) {
                            case "mahesh":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case "Int MCA 2016":
                        switch (name) {
                            case "leena":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case "Int MCA 2017":
                        switch (name) {
                            case "anisha":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case "Int MCA 2018":
                        switch (name) {
                            case "deepa":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case "MCA LAT 2017":
                        switch (name) {
                            case "soumya":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case "MCA LAT 2018":
                        switch (name) {
                            case "ambily":
                                break;
                            default:
                                builder.setMessage("YOU ARE NOT THE COORDINATOR OF THIS CLASS.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                }
                    dbref.child(batch).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if(!Pattern.matches("[a-zA-Z]+", contact.getText()))
                            {
                                if(TextUtils.isEmpty(contact.getText()))
                                {
                                    contact.setError("Please enter the Contact No.");
                                }
                                else if(contact.getText().length() != 10)
                                {
                                    contact.setError("Not a Valid Contact Number.");
                                }
                            }
                            if(TextUtils.isEmpty(regno.getText()))
                            {
                                regno.setError("Please enter the Register No.");
                            }
                            else if (regno.getText().toString().contains(" "))
                            {
                                regno.setError("No Spaces Allowed.");
                            }
                            else if(regno.getText().length()!=14)
                            {
                                regno.setError("Not a Valid Register Number.");
                            }
                            else if (snapshot.hasChild(regno.getText().toString()))
                            {
                                regno.setError("Student already exists.");
                            }
                            else
                            {
                                AlertDialog dialog = alertDialogBuilder.create();
                                dialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(AddStudent.this, "Database Error !\nPlease Check your Internet Connection.", Toast.LENGTH_SHORT).show();
                        }
                    });
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Confirm to submit Form");
                alertDialogBuilder.setMessage("\n"+"Reg No : "+regno.getText().toString()+"\n"+"Contact : "+contact.getText().toString()+"\n"+"Batch : "+batch);
                alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        add.setClickable(false);
                        dbref.child(batch).child(regno.getText().toString()).child("Password").setValue("password");
                        dbref.child(batch).child(regno.getText().toString()).child("Ppassword").setValue("password");
                        dbref.child(batch).child(regno.getText().toString()).child("Contact").setValue("+91"+contact.getText().toString());
                        Toast.makeText(AddStudent.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),FacultyDashboard.class);
                        startActivity(i);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Toast.makeText(AddStudent.this, "Registration Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),FacultyDashboard.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
        if (id == R.id.action_exit) {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }
        else if(id == R.id.action_logout)
        {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to Logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            rm.edit().putString("rem", "no").apply();
                            Intent i = new Intent(getApplicationContext(),FacultyLogin.class);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        return super.onOptionsItemSelected(item);
    }
}
