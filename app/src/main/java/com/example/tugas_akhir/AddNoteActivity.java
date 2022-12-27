package com.example.tugas_akhir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Model.NoteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etTitle, etDesc;
    FloatingActionButton add;
    ImageView back, imageUpload;
    DatabaseReference databaseUser;
    FirebaseUser user;
    String userId;
    Button btn_upload;
    Uri imageUri;
    StorageReference storageReference;
    NoteModel note;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        etTitle = findViewById(R.id.addpage_isititle);
        etDesc = findViewById(R.id.addpage_isidesc);
        add = findViewById(R.id.btn_submit);
        back = findViewById(R.id.addpage_back);
        btn_upload = findViewById(R.id.btn_upload);
        imageUpload = findViewById(R.id.uploadImage);
        databaseUser = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();


        add.setOnClickListener(this);
        back.setOnClickListener(this);
        btn_upload.setOnClickListener(this);

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
            case R.id.btn_upload:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,100);
                break;

        }
    }

    private void insertData(){
        String title = etTitle.getText().toString();
        String desc = etDesc.getText().toString();
        String id = databaseUser.push().getKey();
        userId = user.getUid();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);

        if(imageUri == null){
            note = new NoteModel(title,desc,id);
        }else{
            storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddNoteActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                    imageUpload.setImageURI(null);
                }
            });
            note = new NoteModel(title,desc,id, fileName);
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            imageUpload.setImageURI(imageUri);

        }
    }
}
