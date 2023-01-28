package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Retrieve_Info extends AppCompatActivity {

    private ListView listViewId;
    private TextView retrieveName,retrieveAge,retrieveSex,retrieveContact;
    DatabaseReference databaseReference;
    private CustomAdapter customAdapter;
    private List<DatabaseSavedInfo> databaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve__info);

        listViewId = findViewById(R.id.retrieveListViewId);

        retrieveName = findViewById(R.id.retrieve_name_text_view_Id);
        retrieveAge = findViewById(R.id.retrieve_age_text_view_Id);
        retrieveSex = findViewById(R.id.retrieve_sex_text_view_Id);
        retrieveContact = findViewById(R.id.retrieve_contact_text_view_Id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseList = new ArrayList<>();

        customAdapter = new CustomAdapter(Retrieve_Info.this,databaseList);

    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(Retrieve_Info.this, Profile.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                databaseList.clear();

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    DatabaseSavedInfo databaseSavedInfo = dataSnapshot1.getValue(DatabaseSavedInfo.class);
                    databaseList.add(databaseSavedInfo);
                }

                listViewId.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
