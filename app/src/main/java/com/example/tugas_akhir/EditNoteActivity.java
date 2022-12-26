package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugas_akhir.Model.NoteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTitle, etDesc;
    FloatingActionButton btnSubmit;
    Button btnBack;
    String title, desc, key, userId;
    DatabaseReference database;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);

        title = getIntent().getStringExtra("TITLE");
        desc = getIntent().getStringExtra("DESC");
        key = getIntent().getStringExtra("KEY");

        etTitle = findViewById(R.id.editpage_isititle);
        etDesc = findViewById(R.id.editpage_isidesc);
        btnSubmit = findViewById(R.id.editpage_submit);
        btnBack = findViewById(R.id.editpage_back);
        database = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        etTitle.setText(title);
        etDesc.setText(desc);

        btnSubmit.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent back = new Intent(EditNoteActivity.this, ViewNoteActivity.class);
        switch (view.getId()){
            case R.id.editpage_back:
                back.putExtra("TITLE", title);
                back.putExtra("DESC", desc);
                back.putExtra("KEY", key);
                startActivity(back);
                break;
            case R.id.editpage_submit:
                String title = etTitle.getText().toString();
                String desc = etDesc.getText().toString();
                NoteModel note = new NoteModel(title, desc, this.key);

                database.child("notes").child(userId).child(key).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        back.putExtra("TITLE", note.getTitle());
                        back.putExtra("DESC", note.getDesc());
                        back.putExtra("KEY", note.getKey());

                        Toast.makeText(EditNoteActivity.this, "Note telah diubah", Toast.LENGTH_SHORT).show();
                        startActivity(back);
                    }
                });
                break;
        }
    }


}
