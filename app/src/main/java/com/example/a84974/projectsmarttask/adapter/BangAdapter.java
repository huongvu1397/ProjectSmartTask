package com.example.a84974.projectsmarttask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.Module.bAng;
import com.example.a84974.projectsmarttask.R;

import java.util.List;

public class BangAdapter extends RecyclerView.Adapter<BangAdapter.ViewHolder> {
    private List<bAng> bAngs;

    public BangAdapter(List<bAng> bAngs) {
        this.bAngs = bAngs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_bang_the,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                 bAng bg = bAngs.get(position);
                 holder.tvTieude.setText(bg.getTieude());
    }

    @Override
    public int getItemCount() {
        return bAngs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTieude;
        ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTieude = itemView.findViewById(R.id.cardTitle);
            imgIcon = itemView.findViewById(R.id.cardIcon);
        }
    }
}
