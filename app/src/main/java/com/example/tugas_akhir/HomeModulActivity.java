package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Adapter.ModulAdapter;
import com.example.tugas_akhir.Interface.RecyclerViewInterface;
import com.example.tugas_akhir.Model.ModulModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeModulActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface {
    //Kurang initiate recycler view
    Button modul, note;

    RecyclerView recyclerView;
    DatabaseReference database;
    FirebaseUser user;
    ModulAdapter modulAdapter;
    ArrayList<ModulModel> modulList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homemodul_page);

        note = findViewById(R.id.homemod_note);

        note.setOnClickListener(this);

        recyclerView = findViewById(R.id.rvModul);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeModulActivity.this));
        modulList = new ArrayList<>();
        modulAdapter = new ModulAdapter(this, modulList, this);
        recyclerView.setAdapter(modulAdapter);
        database = FirebaseDatabase.getInstance().getReference("modul");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModulModel modul = dataSnapshot.getValue(ModulModel.class);
                    modulList.add(modul);

                }
                modulAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    @Override
    public void onItemClick(int position) {
        Intent toView = new Intent(HomeModulActivity.this, ViewModulActivity.class);
        toView.putExtra("TITLE",modulList.get(position).getTitle());
        toView.putExtra("DESC",modulList.get(position).getDesc());
        startActivity(toView );
    }
}

