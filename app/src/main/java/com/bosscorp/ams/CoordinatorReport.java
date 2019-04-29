package com.bosscorp.ams;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CoordinatorReport extends AppCompatActivity {
    String batch,roll;
    Integer rno;
    SharedPreferences rm;
    Bundle bn;
    Intent i;
    TextView t1,t2,t0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_report);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        batch = bundle.getString("batch");
        roll = bundle.getString("roll");
        rno = bundle.getInt("rno");
        t0=findViewById(R.id.t0);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        switch (batch)
        {
            case "Int MCA 2015":
                t0.setText("Data Mining");
                t1.setText("Web Designing");
                t2.setText("Cryptography");
                break;
            case "Int MCA 2016":
                t0.setText("Computer Graphics");
                t1.setText("Soft Computing");
                t2.setText("Artificial Intelligence");
                break;
            case "Int MCA 2017":
                t0.setText("Data Structures");
                t1.setText("DBMS");
                t2.setText("Network Security");
                break;
            case "Int MCA 2018":
                t0.setText("COSA");
                t1.setText("Software Engineering");
                t2.setText("Machine Learning");
                break;
            case "MCA LAT 2017":
                t0.setText("JAVA");
                t1.setText("Networks");
                t2.setText("Discrete Mathematics");
                break;
            case "MCA LAT 2018":
                t0.setText("Computer Graphics");
                t1.setText("Soft Computing");
                t2.setText("Artificial Intelligence");
                break;
        }
    }
    public void Detail(View view)
    {
        TextView clickedTextView = (TextView) view;
        String course = clickedTextView.getText().toString();
        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
        bn = new Bundle();
        bn.putString("course",course);
        bn.putString("roll",roll);
        bn.putString("batch",batch);
        bn.putInt("rno",rno);
        i.putExtras(bn);
        startActivity(i);
        finish();
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
