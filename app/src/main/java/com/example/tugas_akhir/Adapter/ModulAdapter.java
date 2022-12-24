package com.example.tugas_akhir.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.Model.ModulModel;
import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class ModulAdapter extends RecyclerView.Adapter<ModulAdapter.ModulViewHolder> {
    private Context context;
    private ArrayList<ModulModel> ModulList;

    private static ClickListener clickListener;

    public ModulAdapter(Context context, ArrayList<ModulModel> ModulList) {
        this.context = context;
        this.ModulList = ModulList;
    }


    @NonNull
    @Override
    public ModulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modul, parent, false);
        return new ModulViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ModulViewHolder holder, int position) {
        ModulModel Modul = ModulList.get(position);
        holder.tvTitle.setText(Modul.getTitle());
        holder.tvDesc.setText(Modul.getDesc());
    }

    @Override
    public int getItemCount() {
        return ModulList.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public class ModulViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout ModulLayout;
        TextView tvTitle, tvDesc;

        public ModulViewHolder(@NonNull View itemView) {
            super(itemView);
            //ModulLayout = itemView.findViewById(R.id.Modul_layout);
            tvTitle = itemView.findViewById(R.id.m_judul);
            tvDesc = itemView.findViewById(R.id.m_desc);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),
                    itemView);
        }

        public void
        setOnItemClickListener(ModulAdapter.ClickListener clickListener) {
            ModulAdapter.clickListener = clickListener;
        }

    }
}

