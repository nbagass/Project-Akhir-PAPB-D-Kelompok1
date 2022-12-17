package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeModulActivity extends AppCompatActivity implements View.OnClickListener {
    //Kurang initiate recycler view
    Button modul, note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homemodul_page);

        note = findViewById(R.id.homemod_note);

        note.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.homemod_note:
                Intent toHomeNote = new Intent(HomeModulActivity.this, HomeNoteActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(toHomeNote);
                break;
        }
    }
}

