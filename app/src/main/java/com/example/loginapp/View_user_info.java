package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class View_user_info extends AppCompatActivity  {


    private TextView viewName,viewAge,viewSex,viewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_info);


        viewName = findViewById(R.id.view_name_text_view_Id);
        viewAge = findViewById(R.id.view_age_text_view_Id);
        viewSex = findViewById(R.id.view_sex_text_view_Id);
        viewContact = findViewById(R.id.view_contact_text_view_Id);


        String UserID = FirebaseAuth.getInstance().getUid().toString();
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("user").child(UserID);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                viewName.setText(name);

                String age = dataSnapshot.child("age").getValue().toString();
                viewAge.setText(age);

                String sex = dataSnapshot.child("sex").getValue().toString();
                viewSex.setText(sex);

                String contact = dataSnapshot.child("contact").getValue().toString();
                viewContact.setText(contact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(View_user_info.this, Profile.class);
        startActivity(intent);
    }
}
