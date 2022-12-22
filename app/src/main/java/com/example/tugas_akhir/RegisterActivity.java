package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword, etUsername;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        etEmail = findViewById(R.id.signup_email);
        etPassword = findViewById(R.id.signup_password);
        etUsername = findViewById(R.id.signup_username);
        register = findViewById(R.id.signup_submit);
        login = findViewById(R.id.signup_masuk);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.signup_submit:
                //Kurang masukin username ke database
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String username = etUsername.getText().toString();

                if(email.isEmpty()||password.isEmpty()||username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Email, Password, dan Username wajib diisi!", Toast.LENGTH_SHORT).show();
                }else{
                    register(email, password, username);
                }

                break;
            case R.id.signup_masuk:
                Intent toLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLoginPage);
                break;
        }
    }

    public void register(String email, String password, String username){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User has been registered successfully
                            Toast.makeText(RegisterActivity.this, "Registrasi sukes, silahkan login.", Toast.LENGTH_SHORT).show();
                            Intent toLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(toLoginPage);
                        } else {
                            // There was an error registering the user
                            Toast.makeText(RegisterActivity.this, "Inputan anda salah", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }
}