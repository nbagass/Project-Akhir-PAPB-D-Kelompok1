package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Adapter.NoteAdapter;
import com.example.tugas_akhir.Model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeNoteActivity extends AppCompatActivity implements View.OnClickListener{
    //Kurang initiate recycler view
    Button modul;
    FloatingActionButton addNote;
    String userId;

    RecyclerView recyclerView;
    DatabaseReference database;
    FirebaseUser user;
    NoteAdapter noteAdapter;
    ArrayList<NoteModel> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homenote_page);

        modul = findViewById(R.id.homenote_modul);
        addNote = findViewById(R.id.homenote_add);
        recyclerView = findViewById(R.id.rvNote);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeNoteActivity.this));
        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this, noteList);
        recyclerView.setAdapter(noteAdapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        database = FirebaseDatabase.getInstance().getReference("notes").child(userId);

        modul.setOnClickListener(this);
        addNote.setOnClickListener(this);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    NoteModel note = dataSnapshot.getValue(NoteModel.class);
                    noteList.add(note);

                    }
                noteAdapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
