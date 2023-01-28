package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ambulance_driver_view extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> list,list1;
    ArrayList<String> driverName;
    ArrayList<String> driverContact;
    ArrayList<Ambulance> driverInfoList;
    ArrayAdapter<String> adapter,adapter1;

    AmbulanceAdapter ambulanceAdapter;
    Ambulance ambulance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_info);


        ambulance = new Ambulance();
        listView = findViewById(R.id.driver_list_view_Id);
        driverInfoList = new ArrayList<>();

        driverName = new ArrayList<>();
        driverContact = new ArrayList<>();
        ambulanceAdapter = new AmbulanceAdapter(Ambulance_driver_view.this,driverInfoList);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.ambulance_driver_view,R.id.view_driver_name_text_view_Id,list);


        /*CustomAmbulanceDriver adapter = new CustomAmbulanceDriver(this,driverName,driverContact);
        listView.setAdapter(adapter);*/

    }

    @Override
    protected void onStart() {

        databaseReference = FirebaseDatabase.getInstance().getReference("ambulance");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot driver : dataSnapshot.getChildren()) {

                    /*String names = driver.child("name").getValue(String.class);
                    String contact = driver.child("contact").getValue(String.class);

                    Ambulance ambulance = new Ambulance(names,contact);
                    driverInfoList.add(ambulance);
                    Toast.makeText(Ambulance_driver_view.this,"hello there",Toast.LENGTH_SHORT).show();

                    String parent = driver.getKey();
                    Log.i("test",parent);*/

                    String names = driver.child("name").getValue().toString();
                    driverName.add(names);
                    String contact = driver.child("contact").getValue().toString();
                    driverContact.add(contact);


                    list.add(names+" "+contact);
                    /*list1.add(contact);*/
                }

                listView.setAdapter(adapter);
                /*listView.setAdapter(adapter1);*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setAdapter(ambulanceAdapter);

        super.onStart();
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(Ambulance_driver_view.this, Profile.class);
        startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.settingsId){
            Toast.makeText(this,"Settings is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.shareId){
            Toast.makeText(this,"Share is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.feedbackId){
            Toast.makeText(this,"Feedback is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.aboutusId){
            Toast.makeText(this,"About us is selected",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}