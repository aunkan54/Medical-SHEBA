package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity implements View.OnClickListener {

    private EditText passWordSignUp,emailSignUp;
    private TextView signUptosignInText;
    private CheckBox rememberPassCheckBox;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBarSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");

        passWordSignUp = findViewById(R.id.password_sign_up_edit_text_Id);
        emailSignUp = findViewById(R.id.email_sign_up_edit_text_Id);
        rememberPassCheckBox = findViewById(R.id.remember_pass_checkbox_Id);
        signUpButton = findViewById(R.id.sign_up_button_Id);
        signUptosignInText = findViewById(R.id.sign_up_to_sign_in_textView_Id);
        progressBarSignUp = findViewById(R.id.Sign_Up_progress_Bar_Id);
        rememberPassCheckBox = findViewById(R.id.remember_pass_checkbox_Id);


        mAuth = FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(this);
        signUptosignInText.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){


            case R.id.sign_up_to_sign_in_textView_Id:
                Intent intent = new Intent(Sign_up.this,Sign_in.class);
                startActivity(intent);
                break;


            case R.id.sign_up_button_Id:
                userRegister();
                break;

            case R.id.remember_pass_checkbox_Id:
                break;



        }
    }


    public void userRegister(){
        String email = emailSignUp.getText().toString().trim();
        String password = passWordSignUp.getText().toString().trim();


        if(email.isEmpty())
        {
            emailSignUp.setError("Enter an email address");
            emailSignUp.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailSignUp.setError("Enter a valid email address");
            emailSignUp.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            passWordSignUp.setError("Enter a password");
            passWordSignUp.requestFocus();
            return;
        }

        if(password.length()<6){
            passWordSignUp.setError("Minimum length of password should be 6");
            passWordSignUp.requestFocus();
            return;
        }

        progressBarSignUp.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Authentication Successful.",
                            Toast.LENGTH_SHORT).show();

                    String UserID = FirebaseAuth.getInstance().getUid().toString();

                    final DatabaseReference mUserDB = FirebaseDatabase.getInstance().getReference().child("user").child(UserID);
                        mUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (!dataSnapshot.exists()) {
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("name", "halar vai");
                                    userMap.put("age", "halar put");
                                    userMap.put("sex", "halar put");
                                    userMap.put("contact", "halar put");

                                    mUserDB.updateChildren(userMap);
                                }
                                finish();
                                Intent intent = new Intent(Sign_up.this, Get_user_Info.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "User is already registered",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Authentication failed.\nError : "
                                        +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
