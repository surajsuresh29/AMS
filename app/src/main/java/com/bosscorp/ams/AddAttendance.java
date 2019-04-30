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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddAttendance extends AppCompatActivity {

    String course, roll, name, batch, date, Contact, hour;
    Integer rno,start,end,strength,attended,total,absent=0;
    ProgressDialog prg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences tn,rm;
    AlertDialog.Builder builder;
    private OkHttpClient mClient = new OkHttpClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        batch = bundle.getString("COURSE");
        date = bundle.getString("DATE");
        start = bundle.getInt("Start");
        end = bundle.getInt("End");
        prg = new ProgressDialog(AddAttendance.this);
        prg.setMessage("TAKE A DEEP BREATH..!!");
        builder = new AlertDialog.Builder(this);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        tn = getApplicationContext().getSharedPreferences("faculty", MODE_PRIVATE);
        name = tn.getString("name", "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        date = date.replaceAll("/","-");
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        prg.show();
        switch (batch) {
            case "Int MCA 2015":
                roll = "KHSCI5MCA";
                rno = 15000;
                switch (name) {
                    case "mahesh":
                        course = "Data Mining";
                        break;
                    case "hari":
                        course = "Web Designing";
                        break;
                    case "nima":
                        course = "Cryptography";
                        break;
                    default:
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2016":
                roll = "KHSCI5MCA";
                rno = 16000;
                switch (name) {
                    case "leena":
                        course = "Computer Graphics";
                        break;
                    case "prasannakumar":
                        course = "Soft Computing";
                        break;
                    case "soumya":
                        course = "Artificial Intelligence";
                        break;
                    default:
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2017":
                roll = "KHSCI5MCA";
                rno = 17000;
                switch (name) {
                    case "anisha":
                        course = "Data Structures";
                        break;
                    case "rajalakshmi":
                        course = "DBMS";
                        break;
                    case "sreekumar":
                        course = "Network Security";
                        break;
                    default:
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2018":
                roll = "KHSCI5MCA";
                rno = 18000;
                switch (name) {
                    case "deepa":
                        course = "COSA";
                        break;
                    case "uma":
                        course = "Software Engineering";
                        break;
                    case "vimina":
                        course = "Machine Learning";
                        break;
                    default:
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "MCA LAT 2017":
                roll = "KHSCLEMCA";
                rno = 17000;
                switch (name) {
                    case "soumya":
                        course = "JAVA";
                        break;
                    case "ambily":
                        course = "Networks";
                        break;
                    case "maya":
                        course = "Discrete Mathematics";
                        break;
                    default:
                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "MCA LAT 2018":
                roll = "KHSCLEMCA";
                rno = 18000;
                switch (name) {
                    case "ambily":
                        course = "Computer Graphics";
                        break;
                    case "deepa":
                        course = "Soft Computing";
                        break;
                    case "nandakumar":
                        course = "Artificial Intelligence";
                        break;
                    default:
                        builder.setMessage("PLEASE CHOOSE A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent StartMain = new Intent(getApplicationContext(), ChooseAttendanceDetails.class);
                                        startActivity(StartMain);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
        }

        DatabaseReference ref = database.getReference("Strength").child(batch).child("studno");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                strength = dataSnapshot.getValue(Integer.class);
                for (int i = 1; i < strength+1; i++) {
                    String name = "t" + i;
                    String sname = "s" + i;
                    int sid = getResources().getIdentifier(sname, "id", getPackageName());
                    int id = getResources().getIdentifier(name, "id", getPackageName());
                    if (id != 0) {
                        TextView textView = findViewById(id);
                        Switch aswitch = findViewById(sid);
                        aswitch.setVisibility(View.VISIBLE);
                        textView.setText(roll + (rno + i));
                        textView.setVisibility(View.VISIBLE);
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
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),FacultyDashboard.class);
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
    public void onSubmit(View view)
    {

        for (int j=start; j<end+1 ;j++)
        {
            hour = String.valueOf(j);
            for (int i = 1; i < strength+1; i++)
            {
                String name = "t" + i;
                String sname = "s" + i;
                int sid = getResources().getIdentifier(sname, "id", getPackageName());
                int id = getResources().getIdentifier(name, "id", getPackageName());
                if (id != 0)
                {
                    final  TextView textView = findViewById(id);
                    Switch aswitch = findViewById(sid);
                    if (aswitch.isChecked())
                    {
                        final DatabaseReference dbref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child(date).child(hour).child(course);
                        dbref.setValue(1);
                        final DatabaseReference aref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child(course).child("attended");
                        aref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                attended = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                                aref.setValue(attended+1);

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else if(!aswitch.isChecked())
                    {
                        absent+=1;
                        DatabaseReference dref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child("Contact");
                        dref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Contact = dataSnapshot.getValue(String.class);
                                //Toast.makeText(AddAttendance.this, Contact, Toast.LENGTH_SHORT).show();
                                try {
                                    post("https://ams-asas.herokuapp.com/sms", new  Callback(){

                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            e.printStackTrace();
                                            Toast.makeText(AddAttendance.this, "failed", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(),"SMS Sent!",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        DatabaseReference dbref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child(date).child(hour).child(course);
                        dbref.setValue(0);
                    }
                }
            }
        }
        final DatabaseReference abref = database.getReference("Students").child(batch).child(course).child("total");
        abref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                abref.setValue(total+1);
                builder.setTitle("Report");
                builder.setMessage("Attendance marked successfully."+"\n\nTOTAL STRENGTH : "+strength+"\n\nABSENTEES : "+absent)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),FacultyDashboard.class);
                                startActivity(i);
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    Call post(String url, Callback callback) throws IOException{
        RequestBody formBody = new FormBody.Builder()
                .add("To", Contact)
                .add("Body", "Your ward is absent from the "+course+" lecture on "+date+".")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;
    }
}
