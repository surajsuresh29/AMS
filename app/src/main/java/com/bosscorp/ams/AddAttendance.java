package com.bosscorp.ams;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAttendance extends AppCompatActivity {

    FirebaseFirestore db;
    String course, roll, name,batch,rno;
    Integer i,studno;
    ProgressDialog prg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences tn;
    AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle=getIntent().getExtras();
        batch =bundle.getString("COURSE");
        tn = getApplicationContext().getSharedPreferences("faculty",MODE_PRIVATE);
        builder = new AlertDialog.Builder(this);
        name = tn.getString("name","");
        db = FirebaseFirestore.getInstance();
        prg = new ProgressDialog(AddAttendance.this);
        prg.setMessage("TAKE A DEEP BREATH..!!");
        prg.show();
        final DatabaseReference dbref = database.getReference("Strength");
        final DatabaseReference usref = dbref.child(batch).child("studno");
        final Button submit = new Button(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        switch (batch)
        {
            case "Int MCA 2015":roll = "KH SC I5MCA150";
            switch (name)
            {
                case "mahesh" : course = "Data Mining";
                break;
                case "hari" : course = "Web Designing";
                break;
                case "nima" : course = "Cryptography";
                break;
                default :
                    builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                    startActivity(StartMain);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
            }
            break;
            case "Int MCA 2016":roll = "KH SC I5MCA160";
            switch (name)
            {
                case "leena" : course = "Computer Graphics";
                break;
                case "deepa" : course = "Soft Computing";
                break;
                case "soumya" : course = "Artificial Intelligence";
                break;
                default :
                    builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                    startActivity(StartMain);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
            }
            break;
            case "Int MCA 2017":roll = "KH SC I5MCA170";
                switch (name)
                {
                    case "nandakumar" : course = "Data Structures";
                        break;
                    case "rajalakshmi" : course = "DBMS";
                        break;
                    case "sreekumar" : course = "Network Security";
                        break;
                    default :
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
            break;
            case "Int MCA 2018":roll = "KH SC I5MCA180";
                switch (name)
                {
                    case "prasannakumar" : course = "COSA";
                        break;
                    case "uma" : course = "Software Engineering";
                        break;
                    case "vimina" : course = "Machine Learning";
                        break;
                    default :
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
            break;
            case "MCA LAT 2017":roll = "KH SC LEMCA170";
                switch (name)
                {
                    case "anisha" : course = "JAVA";
                        break;
                    case "ambily" : course = "Networks";
                        break;
                    case "maya" : course = "Discrete Mathematics";
                        break;
                    default :
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
            break;
            case "MCA LAT 2018":roll = "KH SC LEMCA180";
                switch (name)
                {
                    case "ambily" : course = "Computer Graphics";
                        break;
                    case "deepa" : course = "Soft Computing";
                        break;
                    case "soumya" : course = "Artificial Intelligence";
                        break;
                    default :
                        builder.setMessage("PLEASE CHOOSE A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain=new Intent(getApplicationContext(),ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
            break;
        }
        usref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                studno  = Integer.valueOf(String.valueOf(snapshot.getValue()));
                LinearLayout lm = (LinearLayout) findViewById(R.id.parent_linear_layout);

                for(i  =1; i<studno+1; i++ )
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
                        rollno.setPadding(50,70,0,0);
                        rollno.setId(i);
                        rollno.setTextColor(getColor(R.color.colorPrimary));
                        rollno.setTextSize(20);
                        ll.addView(rollno);

                        // Create Switch
                        Switch aSwitch = new Switch(getApplicationContext());
                        aSwitch.setPadding(150,70,0,0);
                        aSwitch.setChecked(true);
                        ll.addView(aSwitch);
                        lm.addView(ll);
                }
                LinearLayout ln = new LinearLayout(getApplicationContext());
                ln.setGravity(50);
                ln.setPadding(0,70,0,0);
                submit.setText(R.string.mark_attendance);
                submit.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                submit.setBackgroundColor(getColor(R.color.colorPrimary));
                submit.setTextColor(getColor(R.color.white));
                ln.addView(submit);
                lm.addView(ln);
                prg.hide();
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddAttendance.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });
        //DocumentReference docRef = db.collection("courses").document()
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(i= 0; i<studno+1;i++)
                {
                    if(i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9)
                    {
                        rno = (roll+0+i);
                        Toast.makeText(AddAttendance.this, rno, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        rno = (roll+i);
                        Toast.makeText(AddAttendance.this, rno, Toast.LENGTH_SHORT).show();
                    }
                    DatabaseReference atref = database.getReference("Attendance");
                    atref.child(batch).child(rno).child("status").setValue(1);
                }
            }
        });
    }
}

