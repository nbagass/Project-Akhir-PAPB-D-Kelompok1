package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etTitle, etDesc;
    Button add, back;
    ImageView back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        etTitle = findViewById(R.id.addpage_isititle);
        etDesc = findViewById(R.id.addpage_isidesc);
        add = findViewById(R.id.btn_submit);
        back = findViewById(R.id.btn_kembali);
        back2 = findViewById(R.id.addpage_back);

        add.setOnClickListener(this);
        back.setOnClickListener(this);
        back2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent toHomeNote = new Intent(AddNoteActivity.this, HomeNoteActivity.class);

        switch (view.getId()){
            case R.id.btn_kembali:
                startActivity(toHomeNote);
                break;
            case R.id.addpage_back:
                startActivity(toHomeNote);
                break;
            case R.id.btn_submit:
                //Tambahin ambil isi + upload database
                startActivity(toHomeNote);
                break;
        }
    }
}
