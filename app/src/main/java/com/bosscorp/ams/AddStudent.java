package com.bosscorp.ams;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class AddStudent extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference dbref = database.getReference("Students");
    String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        final EditText regno = (EditText)findViewById(R.id.regno);
        final EditText contact = (EditText)findViewById(R.id.contact);
        final Button add = (Button)findViewById(R.id.add);
        Spinner dropdown = findViewById(R.id.course);
        String[] items = new String[]{"Int MCA 2015", "Int MCA 2016", "Int MCA 2017", "Int MCA 2018", "MCA LAT 2017", "MCA LAT 2018"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(regno.getText()))
                {
                    regno.setError("Please enter the Register No.");
                }
                if (regno.getText().toString().contains(" "))
                {
                    regno.setError("No Spaces Allowed");
                }
                if(regno.getText().length()!=14)
                {
                    regno.setError("Not a Valid Register Number");
                }
                if(!Pattern.matches("[a-zA-Z]+", contact.getText()))
                {
                    if(contact.getText().length() != 10)
                    {
                        contact.setError("Not a Valid Contact Number");
                    }
                }
                if(TextUtils.isEmpty(contact.getText()))
                {
                    contact.setError("Please enter the Contact No.");
                }

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddStudent.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Confirm to submit Form");
                alertDialogBuilder.setMessage("\n"+"Reg No : "+regno.getText().toString()+"\n"+"Contact : "+contact.getText().toString()+"\n"+"Course : "+course);
                alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        add.setClickable(false);
                        dbref.child(course).child(regno.getText().toString()).child("Contact").setValue("+91"+contact.getText().toString());
                        Toast.makeText(AddStudent.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Toast.makeText(AddStudent.this, "Registration Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
                dbref.child(course).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(regno.getText().toString())) {
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
            }
        });
    }
}
