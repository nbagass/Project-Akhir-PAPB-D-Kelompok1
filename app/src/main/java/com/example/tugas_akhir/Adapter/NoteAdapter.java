package com.example.tugas_akhir.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Model.NoteModel;
import com.example.tugas_akhir.R;


import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private ArrayList<NoteModel> noteList;

    private static ClickListener clickListener;

    public NoteAdapter(Context context, ArrayList<NoteModel> noteList) {
        this.context = context;
        this.noteList = noteList;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);

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

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout noteLayout;
        TextView tvTitle, tvDesc;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            //noteLayout = itemView.findViewById(R.id.note_layout);
            tvTitle = itemView.findViewById(R.id.n_judul);
            tvDesc = itemView.findViewById(R.id.n_desc);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),
                    itemView);
        }

        public void
        setOnItemClickListener(NoteAdapter.ClickListener clickListener) {
            NoteAdapter.clickListener = clickListener;
        }

    }
}
