package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Info extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private EditText nameEditText,ageEditText,contactEditText;
    private Button saveInfoButton;
    private FirebaseAuth mAuth;
    public String sex;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);

        nameEditText = findViewById(R.id.name_edit_text_Id);
        ageEditText = findViewById(R.id.age_edit_text_Id);
        contactEditText = findViewById(R.id.contact_edit_text_Id);
        saveInfoButton = findViewById(R.id.save_info_Id);

        spinner = findViewById(R.id.sex_spinner_Id);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        saveInfoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        saveInfo();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sex = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveInfo() {
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String contact = contactEditText.getText().toString().trim();

        if(name.isEmpty())
        {
            nameEditText.setError("Enter a name");
            nameEditText.requestFocus();
            return;
        }

        if(age.isEmpty())
        {
            ageEditText.setError("Enter age");
            ageEditText.requestFocus();
            return;
        }

        if(contact.isEmpty())
        {
            contactEditText.setError("Enter a contact no.");
            contactEditText.requestFocus();
            return;
        }


        String key = databaseReference.push().getKey();

        DatabaseSavedInfo databaseSavedInfo = new DatabaseSavedInfo(name,age,sex,contact);

        databaseReference.child(key).setValue(databaseSavedInfo);

        Toast.makeText(getApplicationContext(), "Successfully added Info",
                Toast.LENGTH_SHORT).show();


        finish();
        Intent intent = new Intent(User_Info.this,Profile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
