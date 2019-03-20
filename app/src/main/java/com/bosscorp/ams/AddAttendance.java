package com.bosscorp.ams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddAttendance extends AppCompatActivity {


    private LinearLayout parentLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        parentLinearLayout = (RelativeLayout)findViewById(R.id.parent_linear_layout);
    }
}
