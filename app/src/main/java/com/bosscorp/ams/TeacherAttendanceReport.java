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

public class TeacherAttendanceReport extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String roll, course,batch;
    Integer rno,j,strength;
    SharedPreferences  rm;
    AlertDialog.Builder builder;
    Float total;
    ProgressDialog prg;
    Float[] attended;
    Float[] percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance_report);
        Bundle bundle = getIntent().getExtras();
        batch = bundle.getString("batch");
        roll = bundle.getString("roll");
        course = bundle.getString("course");
        rno = bundle.getInt("rno");
        builder = new AlertDialog.Builder(TeacherAttendanceReport.this);
        prg = new ProgressDialog(TeacherAttendanceReport.this);
        prg.setMessage("Take a deep breath..!");
        DatabaseReference ref = database.getReference("Strength").child(batch).child("studno");
        prg.show();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                strength = dataSnapshot.getValue(Integer.class);
                attended = new Float[strength];
                for (int i = 0; i < strength; i++) {
                    String name = "t" + i;
                    int id = getResources().getIdentifier(name, "id", getPackageName());
                    if (id != 0) {
                        TextView textView = findViewById(id);
                        textView.setText(roll + (rno + i+1));
                    }
                }
                        final DatabaseReference aref = database.getReference("Students").child(batch);
                        aref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (int i = 0; i < strength; i++) {
                                    attended[i] = Float.parseFloat(String.valueOf(dataSnapshot.child(String.valueOf(roll + (rno + i + 1))).child(course).child("attended").getValue()));
                                    if (i == strength - 1) {
                                        for (int k = 0; k < strength; k++) {
                                            String name = "t" + k;
                                            int id = getResources().getIdentifier(name, "id", getPackageName());
                                            if (id != 0) {
                                                final TextView textView = findViewById(id);
                                                if (total != 0) {
                                                    textView.setText(roll + (rno + (k + 1)) + " : " + ((attended[k] / total) * 100) + "%");
                                                } else {
                                                    textView.setText(roll + (rno + (k + 1)) + " : 0%");
                                                }
                                                textView.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    }
                                }
                                prg.hide();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference abref = database.getReference("Students").child(batch).child(course).child("total");
        abref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total = Float.parseFloat(String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),ChooseBatch.class);
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
                            Intent i = new Intent(getApplicationContext(),FacultyLogin.class);
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
