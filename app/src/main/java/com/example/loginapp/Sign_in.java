package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Sign_in extends AppCompatActivity implements View.OnClickListener {

    private EditText passWordSignIn,emailSignIn;
    private TextView forgotPassTextView, signUpTextView;
    private Button signInButton;
    private ImageButton imageButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBarSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        this.setTitle("Sign In");

        passWordSignIn = findViewById(R.id.password_sign_in_edit_text_Id);
        emailSignIn = findViewById(R.id.email_sign_in_edit_text_Id);
        forgotPassTextView = findViewById(R.id.forgot_pass_textview_Id);
        signUpTextView = findViewById(R.id.sign_In_to_sign_Up_textView_Id);
        signInButton = findViewById(R.id.sign_in_button_Id);
        imageButton = findViewById(R.id.show_hide_password_ImageButton);
        progressBarSignUp = findViewById(R.id.Sign_In_progress_Bar_Id);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(Sign_in.this,Profile.class);
            startActivity(intent);
            finish();
        }

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        passWordSignIn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        passWordSignIn.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
    }





    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sign_In_to_sign_Up_textView_Id:
                Intent intent = new Intent(Sign_in.this,Sign_up.class);
                startActivity(intent);
                break;


            case R.id.sign_in_button_Id:
                    userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = emailSignIn.getText().toString().trim();
        String password = passWordSignIn.getText().toString().trim();


        if(email.isEmpty())
        {
            emailSignIn.setError("Enter an email address");
            emailSignIn.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailSignIn.setError("Enter a valid email address");
            emailSignIn.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            passWordSignIn.setError("Enter a password");
            passWordSignIn.requestFocus();
            return;
        }

        if(password.length()<6){
            passWordSignIn.setError("Minimum length of password should be 6");
            passWordSignIn.requestFocus();
            return;
        }

        progressBarSignUp.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sign In Successful.",
                            Toast.LENGTH_SHORT).show();

                    finish();
                    Intent intent = new Intent(Sign_in.this,Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {

                        Toast.makeText(getApplicationContext(), "Authentication failed.\nError : "
                                +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
