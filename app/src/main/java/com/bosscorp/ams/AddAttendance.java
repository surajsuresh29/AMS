package com.bosscorp.ams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAttendance extends AppCompatActivity {


    private LinearLayout parentLinearLayout;
    FirebaseFirestore db;
    String course, roll;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle=getIntent().getExtras();
        course =bundle.getString("COURSE");
        db = FirebaseFirestore.getInstance();
        final DatabaseReference dbref = database.getReference(course);
        final DatabaseReference usref = dbref.child("studno");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        parentLinearLayout = (LinearLayout)findViewById(R.id.parent_linear_layout);
        switch (course)
        {
            case "Int MCA 2015":roll = "KH.SC.I5MCA150";
            break;
            case "Int MCA 2016":roll = "KH.SC.I5MCA160";
            break;
            case "Int MCA 2017":roll = "KH.SC.I5MCA170";
            break;
            case "Int MCA 2018":roll = "KH.SC.I5MCA180";
            break;
            case "MCA LAT 2017":roll = "KH.SC.LEMCA170";
            break;
            case "MCA LAT 2018":roll = "KH.SC.LEMCA180";
            break;
        }

        //Toast.makeText(this, roll, Toast.LENGTH_SHORT).show();
        usref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer studno  = Integer.valueOf(String.valueOf(snapshot.getValue()));
                LinearLayout lm = (LinearLayout) findViewById(R.id.parent_linear_layout);

                for(int i =1; i<studno+1; i++ )
                {
                        LinearLayout ll = new LinearLayout(getApplicationContext());
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        // Create TextView
                        TextView rollno = new TextView(getApplicationContext());
                        if(i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9)
                        {
                            rollno.setText(roll+0+i);
                        }
                        else {
                            rollno.setText(roll+i);
                        }
                        rollno.setPadding(200,75,0,0);
                        rollno.setTextSize(20);
                        ll.addView(rollno);

                        // Create Switch
                        Switch aSwitch = new Switch(getApplicationContext());
                        aSwitch.setPadding(225,75,0,0);
                        aSwitch.setChecked(true);
                        ll.addView(aSwitch);
                        lm.addView(ll);
                }
                LinearLayout ln = new LinearLayout(getApplicationContext());
                ln.setGravity(50);
                ln.setPadding(0,75,0,0);
                Button submit = new Button(getApplicationContext());
                submit.setText(R.string.mark_attendance);
                submit.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                submit.setBackgroundColor(getColor(R.color.colorPrimary));
                submit.setTextColor(getColor(R.color.white));
                ln.addView(submit);
                lm.addView(ln);
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddAttendance.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });
        //DocumentReference docRef = db.collection("courses").document();
    }
}

