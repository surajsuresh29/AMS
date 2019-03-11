package com.bosscorp.ams;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
;import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentLogin extends AppCompatActivity {
    FirebaseFirestore db;
    EditText username, password;
    Button login;
    String course;
    SharedPreferences pswd;
    AlertDialog.Builder builder;

    //String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        pswd=getApplicationContext().getSharedPreferences("password",MODE_PRIVATE);
        builder = new AlertDialog.Builder(this);
        db = FirebaseFirestore.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
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

                if(TextUtils.isEmpty(username.getText()))
                {
                    username.setError("Please enter your Register No.");
                }
                if(TextUtils.isEmpty(password.getText()))
                {
                    password.setError("Please enter your Password.");
                }
                else if(pswd.getString("pswd","notchanged").equals("notchanged"))
                {
                   setPswd(v);
                }

                else if(pswd.getString("pswd","notchanged").equals("changed")){
                    Login(v);
                }

            }
        });
    }
    public void setPswd(View view)
    {
        builder.setMessage("Enter a new password");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.password_popup, (ViewGroup) findViewById(R.id.content), false);

        final EditText npswd=new EditText(this);

        npswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(npswd);
        final EditText cpswd=new EditText(this);
        cpswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(cpswd);
        builder.setCancelable(false)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pswd.edit().putString("pswd", "changed").apply();
                        Intent StartMain=new Intent(getApplicationContext(),StudentDashboard.class);
                        startActivity(StartMain);
                        finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
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
                            Toast.makeText(StudentLogin.this, "Successfully logged in.",
                                    Toast.LENGTH_LONG).show();
                            Intent dash = new Intent(getApplicationContext(), StudentDashboard.class);
                            startActivity(dash);
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
        });
    }

}
