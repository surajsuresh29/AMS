package com.bosscorp.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class FacultyLogin extends AppCompatActivity {

    boolean exit = false;
    FirebaseFirestore db;
    EditText username, password;
    Button login;
    CheckBox rem;
    SharedPreferences rm,tn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        db = FirebaseFirestore.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rem = findViewById(R.id.rememberme);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        tn = getApplicationContext().getSharedPreferences("faculty",MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText()))
                {
                    username.setError("Please enter your User ID.");
                }
                if(TextUtils.isEmpty(password.getText()))
                {
                    password.setError("Please enter your Password.");
                }
                else {
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
        DocumentReference docRef = db.collection("teachers").document(String.valueOf(username.getText()));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String pass = String.valueOf(document.get("password"));
                        if(pass.equals(String.valueOf(password.getText())))
                        {
                            tn.edit().putString("name", username.getText().toString()).apply();
                            Intent startdash = new Intent(getApplicationContext(),FacultyDashboard.class);
                            startActivity(startdash);
                            Toast.makeText(FacultyLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(FacultyLogin.this, "Please check your login credentials.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(FacultyLogin.this, "Document fetching failed.",
                            Toast.LENGTH_SHORT).show();
                }
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