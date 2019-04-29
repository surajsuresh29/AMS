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

public class ChooseBatch extends AppCompatActivity {
    String name,roll;
    Integer rno;
    Intent i;
    Bundle bn;
    AlertDialog.Builder builder;
    SharedPreferences rm,tn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_batch);
        builder = new AlertDialog.Builder(this);
        rm = getApplicationContext().getSharedPreferences("remember",MODE_PRIVATE);
        tn = getApplicationContext().getSharedPreferences("faculty",MODE_PRIVATE);
        name = tn.getString("name", "");
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

    public void Detail(View view) {

        TextView clickedTextView = (TextView) view;
        String batch = clickedTextView.getText().toString();
        switch (batch) {
            case "Int MCA 2015":
                roll = "KHSCI5MCA";
                rno = 15000;
                switch (name) {
                    case "mahesh":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","Data Mining");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "hari":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Web Designing");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "nima":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Cryptography");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2016" :
                roll = "KHSCI5MCA";
                rno = 16000;
                switch (name) {
                    case "leena":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","Computer Graphics");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "prasannakumar":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Soft Computing");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "soumya":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Artificial Intelligence");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2017" :
                roll = "KHSCI5MCA";
                rno = 17000;
                switch (name) {
                    case "anisha":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","Data Structures");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "rajalakshmi":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","DBMS");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "sreekumar":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Network Security");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "Int MCA 2018" :
                roll = "KHSCI5MCA";
                rno = 18000;
                switch (name) {
                    case "deepa":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","COSA");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "uma":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Software Engineering");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "vimina":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Machine Learning");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
            case "MCA LAT 2017" :
                roll = "KHSCLEMCA";
                rno = 17000;
                switch (name) {
                    case "soumya":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","JAVA");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "ambily":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Networks");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "maya":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Discrete Mathematics");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;

            case "MCA LAT 2018" :
                roll = "KHSCLEMCA";
                rno = 18000;
                switch (name) {
                    case "ambily":
                        i = new Intent(getApplicationContext(), CoordinatorReport.class);
                        bn = new Bundle();
                        bn.putString("course","Computer Graphics");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "deepa":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Soft Computing");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    case "nandakumar":
                        i = new Intent(getApplicationContext(), TeacherAttendanceReport.class);
                        bn = new Bundle();
                        bn.putString("course","Artificial Intelligence");
                        bn.putString("roll",roll);
                        bn.putString("batch",batch);
                        bn.putInt("rno",rno);
                        i.putExtras(bn);
                        startActivity(i);
                        finish();
                        break;
                    default:

                        builder.setMessage("PLEASE SELECT A BATCH WHERE YOU TEACH.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                }
                break;
        }

    }
}
