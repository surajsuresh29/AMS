package com.bosscorp.ams;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
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
        if(course.equals("Int MCA 2015"))
        {
            roll = "KH.SC.I5MCA1500";

        }
        else if(course.equals("Int MCA 2016"))
        {
            roll = "KH.SC.I5MCA1600";
        }
        else if(course.equals("Int MCA 2017"))
        {
            roll = "KH.SC.I5MCA1700";
        }
        else if(course.equals("Int MCA 2018"))
        {
            roll = "KH.SC.I5MCA1800";
        }
        else if(course.equals("MCA LAT 2017"))
        {
            roll = "KH.SC.LEMCA1700";
        }
        else if(course.equals("MCA LAT 2018"))
        {
            roll = "KH.SC.LEMCA1800";
        }

        //Toast.makeText(this, roll, Toast.LENGTH_SHORT).show();
        usref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer studno  = Integer.valueOf(String.valueOf(snapshot.getValue()));
                for(int i =1; i<studno+1; i++ )
                {
                    final LinearLayout lm = (LinearLayout) findViewById(R.id.parent_linear_layout);
                    // create the layout params that will be used to define how your
                    // button will be displayed
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    //Create four

                        // Create LinearLayout
                        LinearLayout ll = new LinearLayout(getApplicationContext());
                        ll.setOrientation(LinearLayout.HORIZONTAL);

                        // Create TextView
                        TextView rollno = new TextView(getApplicationContext());
                        rollno.setText(roll+i);
                        rollno.setPadding(200,100,0,0);
                        rollno.setTextSize(20);
                        ll.addView(rollno);

                        // Create Switch
                        Switch aSwitch = new Switch(getApplicationContext());
                        aSwitch.setPadding(225,100,0,0);
                        aSwitch.setChecked(true);
                        ll.addView(aSwitch);

                        //Add button to LinearLayout defined in XML
                        lm.addView(ll);
                    }
                    /*LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View rowView = inflater.inflate(R.layout.field, null);
                    parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
                */
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddAttendance.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });
        //DocumentReference docRef = db.collection("courses").document();
    }
}

