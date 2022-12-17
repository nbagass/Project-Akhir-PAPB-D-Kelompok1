package com.example.tugas_akhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button masuk, daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_page);
        masuk = findViewById(R.id.login_masuk);
        daftar = findViewById(R.id.signup_daftar);

        masuk.setOnClickListener(this);
        daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_masuk:
                Intent toLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLoginPage);
                break;
            case R.id.signup_daftar:
                Intent toRegisterPage = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toRegisterPage);
                break;
        }
    }
}
