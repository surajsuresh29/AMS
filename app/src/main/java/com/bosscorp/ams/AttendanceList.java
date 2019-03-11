package com.bosscorp.ams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class AttendanceList extends AppCompatActivity {

    private RecyclerView studList;
    private FirebaseFirestore fs;
    private List <StudList> slist;
    String Tag = "firelog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        slist = new ArrayList<>();
        studList = findViewById(R.id.studentlist);
        fs = FirebaseFirestore.getInstance();

        fs.collection("courses").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null)
                {
                    Log.d(Tag, "Error "+e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        StudList studList = doc.getDocument().toObject(StudList.class);
                        slist.add(studList);
                    }
                }
            }
        });
    }
}
