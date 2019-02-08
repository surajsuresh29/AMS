package com.bosscorp.ams;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentLogin extends AppCompatActivity {

    FirebaseFirestore db;
    EditText textDisplay1, textDisplay2;
    Button login;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        db = FirebaseFirestore.getInstance();
        textDisplay1 = findViewById(R.id.username);
        textDisplay2 = findViewById(R.id.password);
        login = findViewById(R.id.login);
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
                if(TextUtils.isEmpty(textDisplay1.getText()))
                {
                    textDisplay1.setError("Please enter your Register No.");
                }
                if(TextUtils.isEmpty(textDisplay2.getText()))
                {
                    textDisplay2.setError("Please enter your Password.");
                }
                else{
                    Login(v);
                }

            }
        });
    }


    public void Login(View view) {
        DocumentReference docRef = db.collection("courses").document(String.valueOf(course))
                .collection("students").document(String.valueOf(textDisplay1));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String pass = String.valueOf(document.get("password"));
                        if(pass.equals(String.valueOf(textDisplay2.getText())))
                        {
                            Toast.makeText(StudentLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(StudentLogin.this, "No document found.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(StudentLogin.this, "Document fetching failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
