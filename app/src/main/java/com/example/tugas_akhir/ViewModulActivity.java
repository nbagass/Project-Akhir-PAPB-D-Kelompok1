package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewModulActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle, tvDesc;
    Button btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmodul_page);

        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("DESC");

        tvTitle = findViewById(R.id.viewmodulpage_judulmodul);
        tvDesc = findViewById(R.id.viewmodulpage_isimodul);
        btn_back = findViewById(R.id.viewmodulpage_back);

        tvTitle.setText(title);
        tvDesc.setText(desc);

        btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.viewmodulpage_back:
                Intent back = new Intent (ViewModulActivity.this, HomeModulActivity.class);
        }
    }
}
