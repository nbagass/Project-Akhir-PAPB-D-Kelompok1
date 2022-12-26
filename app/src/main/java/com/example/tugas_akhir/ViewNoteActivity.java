package com.example.tugas_akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugas_akhir.Model.NoteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewNoteActivity extends AppCompatActivity implements View.OnClickListener {
    TextView etTitle, etDesc;
    Button btnBack, btnEdit, btnDelete;
    String key, userId;
    DatabaseReference database;
    FirebaseUser user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewnote_page);
        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("DESC");
        key = getIntent().getStringExtra("KEY");

        etTitle = findViewById(R.id.viewnotepage_judulnote);
        etDesc = findViewById(R.id.viewnotepage_isinote);
        btnBack = findViewById(R.id.viewnotepage_back);
        btnEdit = findViewById(R.id.viewnotepage_edit);
        btnDelete = findViewById(R.id.viewnotepage_delete);
        database = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        etTitle.setText(title);
        etDesc.setText(desc);

    }

    @Override
    public void onClick(View view) {
        Intent back = new Intent(ViewNoteActivity.this, HomeNoteActivity.class);
        switch (view.getId()){
            case R.id.viewnotepage_back:
                startActivity(back);
                break;
            case R.id.viewnotepage_edit:
                Intent toEdit = new Intent(ViewNoteActivity.this, EditNoteActivity.class);
                toEdit.putExtra("TITLE", etTitle.getText().toString());
                toEdit.putExtra("DESC", etDesc.getText().toString());
                toEdit.putExtra("KEY", key);
                startActivity(toEdit);
                break;
            case R.id.viewnotepage_delete:
                database.child("notes").child(userId).child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ViewNoteActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        startActivity(back);
                    }
                });
                break;
        }
    }
}
