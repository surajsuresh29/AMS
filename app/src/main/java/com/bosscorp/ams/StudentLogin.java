package com.bosscorp.ams;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText username, password;
    Button login;
    String batch,uname,pword;
    CheckBox rem;
    SharedPreferences rm,sroll,sbatch;
    boolean exit=false;
    ProgressDialog prg;
    //String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rem = findViewById(R.id.rememberme);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        sroll = getApplicationContext().getSharedPreferences("roll",MODE_PRIVATE);
        sbatch = getApplicationContext().getSharedPreferences("batch",MODE_PRIVATE);
        prg = new ProgressDialog(StudentLogin.this);
        prg.setMessage("Take a deep breath..!");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prg.show();
                login.setClickable(false);
                if(TextUtils.isEmpty(username.getText()))
                {
                    username.setError("Please enter your Register No.");
                }
                if(TextUtils.isEmpty(password.getText()))
                {
                    password.setError("Please enter your Password.");
                }
                else
                {
                    if (rem.isChecked()) {
                        rm.edit().putString("rem", "yes").apply();
                        login.setClickable(false);
                        Login(v);
                    }
                    else {
                        login.setClickable(false);
                        Login(v);
                    }
                }

            }
        });
    }
    public void Login(View view) {
        uname = String.valueOf(username.getText());
        pword = String.valueOf(password.getText());
        if(uname.substring(0,11).equals("KHSCI5MCA15"))
        {
            batch = "Int MCA 2015";
        }
        else if(uname.substring(0,11).equals("KHSCI5MCA16"))
        {
            batch = "Int MCA 2016";
        }
        else if(uname.substring(0,11).equals("KHSCI5MCA17"))
        {
            batch = "Int MCA 2017";
        }
        else if(uname.substring(0,11).equals("KHSCI5MCA18"))
        {
            batch = "Int MCA 2018";
        }
        else if(uname.substring(0,11).equals("KHSCL2MCA17"))
        {
            batch = "MCA LAT 2017";
        }
        else if(uname.substring(0,11).equals("KHSCL2MCA18"))
        {
            batch = "MCA LAT 2018";
        }
        DatabaseReference dbref = database.getReference("Students").child(batch).child(uname).child("Password");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pass = dataSnapshot.getValue(String.class);
                if (pass.equals(pword))
                {
                    sroll.edit().putString("ROLL", uname).apply();
                    sbatch.edit().putString("BATCH",batch).apply();
                    Toast.makeText(StudentLogin.this, "Successfully logged in.",
                            Toast.LENGTH_LONG).show();
                    Intent dash = new Intent(getApplicationContext(), StudentDashboard.class);
                    prg.hide();
                    startActivity(dash);
                    finish();
                }
                else {
                    Toast.makeText(StudentLogin.this, "Please check your login credentials.",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
/*      DocumentReference docRef = db.collection("courses").document(String.valueOf(course))
                .collection("students").document(String.valueOf(username.getText()));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String pass = String.valueOf(document.get("password"));
                        if(pass.equals(String.valueOf(password.getText())))
                        {
                            Toast.makeText(StudentLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_LONG).show();
                            Intent dash = new Intent(getApplicationContext(), StudentDashboard.class);
                            startActivity(dash);
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(StudentLogin.this, "Please check your login credentials.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(StudentLogin.this, "Document fetching failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });*/