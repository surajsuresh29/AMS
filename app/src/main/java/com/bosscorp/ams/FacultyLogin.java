package com.bosscorp.ams;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FacultyLogin extends AppCompatActivity {

    FirebaseFirestore db;
    EditText textDisplay1, textDisplay2;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        db = FirebaseFirestore.getInstance();
        textDisplay1 = findViewById(R.id.username);
        textDisplay2 = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(textDisplay1.getText()))
                {
                    textDisplay1.setError("Please enter your User ID.");
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
        DocumentReference docRef = db.collection("courses").document(String.valueOf(textDisplay1));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String pass = String.valueOf(document.get("password"));
                        if(pass.equals(String.valueOf(textDisplay2.getText())))
                        {
                            Toast.makeText(FacultyLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(FacultyLogin.this, "No document found.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(FacultyLogin.this, "Document fetching failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
