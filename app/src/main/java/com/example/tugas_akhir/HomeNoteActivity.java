package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeNoteActivity extends AppCompatActivity implements View.OnClickListener{
    //Kurang initiate recycler view
    Button modul;
    FloatingActionButton addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homenote_page);

        modul = findViewById(R.id.homenote_modul);
        addNote = findViewById(R.id.homenote_add);

        modul.setOnClickListener(this);
        addNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.homenote_modul:
                Intent toModulHome = new Intent(HomeNoteActivity.this, HomeModulActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(toModulHome);
                break;
            case R.id.homenote_add:
                Intent toAddNote = new Intent(HomeNoteActivity.this, AddNoteActivity.class);
                startActivity(toAddNote);
                break;
        }
    }
}
