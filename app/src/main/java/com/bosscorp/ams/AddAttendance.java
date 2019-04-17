package com.bosscorp.ams;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    Integer rno,start,end,strength;
    ProgressDialog prg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences tn;
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
        tn = getApplicationContext().getSharedPreferences("faculty", MODE_PRIVATE);
        name = tn.getString("name", "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        date = date.replaceAll("/","-");
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
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
                    case "deepa":
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
                    case "nandakumar":
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
                    case "prasannakumar":
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
                    case "anisha":
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
                    case "soumya":
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
        prg.show();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                strength = dataSnapshot.getValue(Integer.class);
                for (int i = 1; i < strength; i++) {
                    String name = "t" + i;
                    String sname = "s" + i;
                    int sid = getResources().getIdentifier(sname, "id", getPackageName());
                    int id = getResources().getIdentifier(name, "id", getPackageName());
                    if (id != 0) {
                        TextView textView = (TextView) findViewById(id);
                        Switch aswitch = (Switch) findViewById(sid);
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

    public void onSubmit(View view)
    {
        for (int j=start; j<end+1 ;j++)
        {
            hour = String.valueOf(j);
            for (int i = 1; i < strength; i++)
            {
                String name = "t" + i;
                String sname = "s" + i;
                int sid = getResources().getIdentifier(sname, "id", getPackageName());
                int id = getResources().getIdentifier(name, "id", getPackageName());
                if (id != 0)
                {
                    final  TextView textView = (TextView) findViewById(id);
                    Switch aswitch = (Switch) findViewById(sid);
                    if (aswitch.isChecked())
                    {
                        DatabaseReference dbref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child(date).child(hour).child(course);
                        dbref.setValue(1);
                    }
                    else if(!aswitch.isChecked())
                    {
                        DatabaseReference dbref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child(date).child(hour).child(course);
                        dbref.setValue(0);
                        DatabaseReference dref = database.getReference("Students").child(batch).child(String.valueOf(textView.getText())).child("Contact");
                        dref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Contact = dataSnapshot.getValue(String.class);
                                try {
                                    post("https://ams-asas.herokuapp.com/sms", new  Callback(){

                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            e.printStackTrace();
                                            Toast.makeText(AddAttendance.this, "failed", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
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
                    }
                }
            }
        }
    }
    Call post(String url, Callback callback) throws IOException{
        RequestBody formBody = new FormBody.Builder()
                .add("To", Contact)
                .add("Body", "Your ward is absent today.")
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
