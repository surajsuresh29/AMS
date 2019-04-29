package com.bosscorp.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class ParentLogin extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText username, password;
    Button login;
    String batch,uname,pword;
    CheckBox rem;
    SharedPreferences rm,sroll,sbatch;
    //String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rem = findViewById(R.id.rememberme);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        sroll = getApplicationContext().getSharedPreferences("roll",MODE_PRIVATE);
        sbatch = getApplicationContext().getSharedPreferences("batch",MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        DatabaseReference dbref = database.getReference("Students").child(batch).child(uname).child("Ppassword");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pass = dataSnapshot.getValue(String.class);
                if (pass.equals(pword))
                {
                    sroll.edit().putString("ROLL", uname).apply();
                    sbatch.edit().putString("BATCH",batch).apply();
                    Toast.makeText(ParentLogin.this, "Successfully logged in.",
                            Toast.LENGTH_LONG).show();
                    Intent dash = new Intent(getApplicationContext(), ParentDashboard.class);
                    startActivity(dash);
                    finish();
                }
                else {
                    Toast.makeText(ParentLogin.this, "Please check your login credentials.",
                            Toast.LENGTH_LONG).show();
                    login.setClickable(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
