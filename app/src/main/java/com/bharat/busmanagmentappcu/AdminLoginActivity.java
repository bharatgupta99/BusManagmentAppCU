package com.bharat.busmanagmentappcu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wang.avi.AVLoadingIndicatorView;

public class AdminLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailView;
    private EditText passwordView;
    private Button loginButton;
    private String email;
    private String pass;
    private AVLoadingIndicatorView loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);



        mAuth = FirebaseAuth.getInstance();
        emailView = findViewById(R.id.email_text_login);
        passwordView = findViewById(R.id.password_text_login);
        loginButton = findViewById(R.id.login_btn);
        loadingAnim = findViewById(R.id.avi);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass = passwordView.getText().toString();
                email = emailView.getText().toString();
                if(!pass.isEmpty() && !email.isEmpty()){
                    loadingAnim.show();
                    loadingAnim.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent chooseActivity = new Intent(AdminLoginActivity.this, AdminActivity.class);
                                startActivity(chooseActivity);
                                finish();
                            }
                            else {
                                Toast.makeText(AdminLoginActivity.this,"Login Error Try Again!",Toast.LENGTH_SHORT).show();
                            }
                            loadingAnim.hide();
                        }
                    });
                }else {
                    Toast.makeText(AdminLoginActivity.this,"Please fill both",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent chooseActivity = new Intent(AdminLoginActivity.this, AdminActivity.class);
            startActivity(chooseActivity);
            finish();
        }
    }
}
