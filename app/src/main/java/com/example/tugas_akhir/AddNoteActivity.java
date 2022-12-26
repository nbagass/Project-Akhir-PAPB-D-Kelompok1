package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Model.NoteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etTitle, etDesc;
    FloatingActionButton add;
    ImageView back;
    DatabaseReference databaseUser;
    FirebaseUser user;
    String userId;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        etTitle = findViewById(R.id.addpage_isititle);
        etDesc = findViewById(R.id.addpage_isidesc);
        add = findViewById(R.id.btn_submit);
        back = findViewById(R.id.addpage_back);
        databaseUser = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();


        add.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent toHomeNote = new Intent(AddNoteActivity.this, HomeNoteActivity.class);

        switch (view.getId()){
            case R.id.addpage_back:
                startActivity(toHomeNote);
                break;
            case R.id.btn_submit:
                //Tambahin ambil isi + upload database
                insertData();
                break;
        }
    }

    private void insertData(){
        String title = etTitle.getText().toString();
        String desc = etDesc.getText().toString();
        String id = databaseUser.push().getKey();
        userId = user.getUid();

        NoteModel note = new NoteModel(title,desc,id);

        databaseUser.child("notes").child(userId).child(id).setValue(note)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddNoteActivity.this, HomeNoteActivity.class));
                        }
                    }
                });
    }
}
