package com.bosscorp.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;;import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParentLogin extends AppCompatActivity {

    FirebaseFirestore db;
    EditText username, password;
    Button login;
    String course;
    CheckBox rem;
    SharedPreferences rm;
    //String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        db = FirebaseFirestore.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rem = findViewById(R.id.rememberme);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        Spinner dropdown = findViewById(R.id.course);
        String[] items = new String[]{"Int MCA 2015", "Int MCA 2016", "Int MCA 2017", "Int MCA 2018", "MCA LAT 2017", "MCA LAT 2018"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                else{
                    if(rem.isChecked())
                    {
                        rm.edit().putString("rem", "yes").apply();
                    }
                    Login(v);
                }
            }
        });
    }

    public void Login(View view) {
        DocumentReference docRef = db.collection("courses").document(String.valueOf(course))
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
                            Toast.makeText(ParentLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_LONG).show();
                            Intent dash = new Intent(getApplicationContext(), StudentDashboard.class);
                            startActivity(dash);
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(ParentLogin.this, "Please check your login credentials.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(ParentLogin.this, "Document fetching failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
