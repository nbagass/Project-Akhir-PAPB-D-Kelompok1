package com.example.tugas_akhir.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Interface.RecyclerViewInterface;
import com.example.tugas_akhir.Model.NoteModel;
import com.example.tugas_akhir.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private ArrayList<NoteModel> noteList;
    private final RecyclerViewInterface recyclerViewInterface;
    StorageReference storageReference;


    public NoteAdapter(Context context, ArrayList<NoteModel> noteList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.noteList = noteList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteModel note = noteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDesc.setText(note.getDesc());

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        LinearLayout noteLayout;
        TextView tvTitle, tvDesc;
        ImageView ivThumbnail;

        public NoteViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            //noteLayout = itemView.findViewById(R.id.note_layout);
            tvTitle = itemView.findViewById(R.id.n_judul);
            tvDesc = itemView.findViewById(R.id.n_desc);
            ivThumbnail = itemView.findViewById(R.id.n_thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }


    }
}
