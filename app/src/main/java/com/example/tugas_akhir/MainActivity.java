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
        setContentView(R.layout.landing_page);
        masuk = findViewById(R.id.masuk);
        daftar = findViewById(R.id.daftar);

        masuk.setOnClickListener(this);
        daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.masuk:
                Intent toLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLoginPage);
                break;
            case R.id.daftar:
                Intent toRegisterPage = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toRegisterPage);
                break;
        }
    }
}
