package com.example.a84974.projectsmarttask.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.UsingCard;
import com.example.a84974.projectsmarttask.Module.BangList;
import com.example.a84974.projectsmarttask.R;

import java.util.List;

public class BangListAdapter extends RecyclerView.Adapter<BangListAdapter.ViewHolder> {
    private List<BangList> BangLists;
    private Context context;
    private RecyclerView mRecyclerV;

    public BangListAdapter(List<BangList> BangLists, Context context,RecyclerView rcView) {
        this.context = context;
        this.BangLists = BangLists;
        this.mRecyclerV = rcView;
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
                 //
                 BangList bg = BangLists.get(position);

                 holder.tvTieude.setText(bg.getTieude());

                 holder.cardView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                                 Intent intent = new Intent(view.getContext(),UsingCard.class);
                                 intent.putExtra("title",bg.getTieude());
                                 Toast.makeText(view.getContext(), "Hello" + bg.getTieude(), Toast.LENGTH_SHORT).show();
                                 view.getContext().startActivity(intent);
                     }
                 });

    }

    @Override
    public int getItemCount() {
        return BangLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTieude;
        ImageView imgIcon;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTieude = itemView.findViewById(R.id.cardTitle);
            imgIcon = itemView.findViewById(R.id.cardIcon);
            cardView = itemView.findViewById(R.id.cardViewItem);
        }
    }


}
