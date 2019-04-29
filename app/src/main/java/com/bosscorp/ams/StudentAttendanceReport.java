package com.bosscorp.ams;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class StudentAttendanceReport extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String roll,batch;
    String[] course = new String[3];
    Float[] percent = new Float[3];
    Float[] attended = new Float[3];
    Float[] total = new Float[3];
    SharedPreferences sroll,sbatch,rm;
    AlertDialog.Builder builder;
    Integer at,tot;
    ProgressDialog prg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_report);
        sroll = getApplicationContext().getSharedPreferences("roll",MODE_PRIVATE);
        sbatch = getApplicationContext().getSharedPreferences("batch",MODE_PRIVATE);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        roll = sroll.getString("ROLL","");
        batch = sbatch.getString("BATCH","");
        builder = new AlertDialog.Builder(StudentAttendanceReport.this);
        prg = new ProgressDialog(StudentAttendanceReport.this);
        prg.setMessage("Take a deep breath..!");
        prg.show();
        switch (batch)
        {
            case "Int MCA 2015":
                course[0] = "Data Mining";
                course[1] = "Web Designing";
                course[2] = "Cryptography";
                break;
            case "Int MCA 2016":
                course[0] = "Computer Graphics";
                course[1] = "Soft Computing";
                course[2] = "Artificial Intelligence";
                break;
            case "Int MCA 2017":
                course[0] = "Data Structures";
                course[1] = "DBMS";
                course[2] = "Network Security";
                break;
            case "Int MCA 2018":
                course[0] = "COSA";
                course[1] = "Software Engineering";
                course[2] = "Machine Learning";
                break;
            case "MCA LAT 2017":
                course[0] = "JAVA";
                course[1] = "Networks";
                course[2] = "Discrete Mathematics";
                break;
            case "MCA LAT 2018":
                course[0] = "Computer Graphics";
                course[1] = "Soft Computing";
                course[2] = "Artificial Intelligence";
                break;
        }
        final DatabaseReference aref = database.getReference("Students").child(batch).child(roll);
        aref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int i=0;i<3;i++) {
                    attended[i] = Float.parseFloat(String.valueOf(dataSnapshot.child(course[i]).child("attended").getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference dref = database.getReference("Students").child(batch);
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int k=0;k<3;k++)
                {
                    total[k] = Float.parseFloat(String.valueOf(dataSnapshot.child(course[k]).child("total").getValue()));
                }
                for(int j=0;j<3;j++)
                {
                    if(total[j]!=0) {
                        percent[j] = ((attended[j] / total[j]) * 100);
                    }
                    else
                    {
                        percent[j]= Float.valueOf(0);
                    }
                    String name = "t"+j;
                    int id = getResources().getIdentifier(name, "id", getPackageName());
                    if (id != 0) {
                        TextView textView = findViewById(id);
                        textView.setText(course[j]+": "+(percent[j].toString())+" %");
                    }
                }
                prg.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void Detail(View view)
    {
        prg.show();
        TextView clickedTextView = (TextView) view;
        String text = clickedTextView.getText().toString();
        text = text.substring(0, text.indexOf(':'));
        DatabaseReference aref = database.getReference("Students").child(batch).child(roll).child(text).child("attended");
        aref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    at = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference dref = database.getReference("Students").child(batch).child(text).child("total");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tot = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                builder.setTitle("ATTENDANCE SUMMARY");
                builder.setMessage("Attended Lectures : "+at+"\n\nTotal Lectures : "+tot)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                prg.hide();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),StudentDashboard.class);
        startActivity(i);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                            Intent i = new Intent(getApplicationContext(),StudentLogin.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
