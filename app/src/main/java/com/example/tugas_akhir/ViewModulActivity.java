package com.example.tugas_akhir;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewModulActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle, tvDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmodul_page);

        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("DESC");

        tvTitle = findViewById(R.id.viewmodulpage_judulmodul);
        tvDesc = findViewById(R.id.viewmodulpage_isimodul);

        tvTitle.setText(title);
        tvDesc.setText(desc);

    }

    @Override
    public void onClick(View view) {

    }
}
