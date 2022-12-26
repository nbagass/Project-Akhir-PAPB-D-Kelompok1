package com.example.tugas_akhir.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.HomeModulActivity;
import com.example.tugas_akhir.Interface.RecyclerViewInterface;
import com.example.tugas_akhir.Model.ModulModel;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.ViewModulActivity;

import java.util.ArrayList;

public class ModulAdapter extends RecyclerView.Adapter<ModulAdapter.ModulViewHolder> {
    private Context context;
    private ArrayList<ModulModel> ModulList;
    private final RecyclerViewInterface recyclerViewInterface;

    public ModulAdapter(Context context, ArrayList<ModulModel> ModulList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.ModulList = ModulList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public ModulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modul, parent, false);
        return new ModulViewHolder(view, recyclerViewInterface);

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

    public class ModulViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ModulLayout;
        TextView tvTitle, tvDesc;

        public ModulViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            //ModulLayout = itemView.findViewById(R.id.Modul_layout);
            tvTitle = itemView.findViewById(R.id.m_judul);
            tvDesc = itemView.findViewById(R.id.m_desc);

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

