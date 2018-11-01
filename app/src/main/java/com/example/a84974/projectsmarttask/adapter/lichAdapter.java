package com.example.a84974.projectsmarttask.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.Module.lich;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.List;

public class lichAdapter extends RecyclerView.Adapter<lichAdapter.ViewHolder> {
    private List<lich> lichs;
    private Context context;
    private RecyclerView mRecyclerV;
    private Cursor cursor;
    private DatabaseManager manager;

    public lichAdapter(List<lich> lichs, Context context, RecyclerView mRecyclerV) {
        this.lichs = lichs;
        this.context = context;
        this.mRecyclerV = mRecyclerV;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_lich, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        lich ls = lichs.get(position);
        holder.tvBang.setText(ls.getTenbang());
        holder.tvTenThe.setText(ls.getTenthe());
        holder.tvGio.setText(ls.getGio()+" : "+ls.getPhut());
    }

    @Override
    public int getItemCount() {
        return lichs.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTenThe, tvBang,tvGio;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTenThe = itemView.findViewById(R.id.tvLichThe);
            tvBang = itemView.findViewById(R.id.tvLichBang);
            tvGio = itemView.findViewById(R.id.tvLichGio);
            cardView = itemView.findViewById(R.id.cardViewLich);
        }
    }
}
