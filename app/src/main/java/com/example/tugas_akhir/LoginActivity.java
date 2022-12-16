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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword;
    Button login, googleLogin, register;
    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masuk_page);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.pass);
        login = findViewById(R.id.submit);
        googleLogin = findViewById(R.id.google);
        register = findViewById(R.id.btn_daftar);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();
                
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email dan Password wajib diisi!", Toast.LENGTH_SHORT).show();
                }else{
                    login (email,pass);
                }
                break;
            case R.id.btn_daftar:
                Intent toRegisterPage = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity(toRegisterPage);
                break;
        }
    }

    public void login(String email, String password) {
        fireBaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent toHomeModul = new Intent(LoginActivity.this, HomeModulActivity.class);
                    startActivity(toHomeModul);
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
