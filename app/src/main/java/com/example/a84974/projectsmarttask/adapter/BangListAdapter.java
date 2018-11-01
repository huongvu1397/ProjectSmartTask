package com.example.a84974.projectsmarttask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.Module.BangList;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.UsingCardScroll;

import java.util.List;

public class BangListAdapter extends RecyclerView.Adapter<BangListAdapter.ViewHolder> {
    private List<BangList> BangLists;
    private Context context;
    private RecyclerView mRecyclerV;
    private String tenbang,tenlist,idbang;

    public BangListAdapter(List<BangList> BangLists, Context context,RecyclerView rcView,String tenbang,String tenlist,String idbang) {
        this.context = context;
        this.BangLists = BangLists;
        this.mRecyclerV = rcView;
        this.tenbang = tenbang;
        this.tenlist = tenlist;
        this.idbang = idbang;
    }

    public BangListAdapter(List<BangList> bangLists, Context context, RecyclerView mRecyclerV) {
        BangLists = bangLists;
        this.context = context;
        this.mRecyclerV = mRecyclerV;
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

                 BangList bg = BangLists.get(position);
                 holder.tvTieude.setText(bg.getTieude());

                 holder.cardView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         Intent intent = new Intent(view.getContext(),UsingCardScroll.class);
                                 intent.putExtra("titleBLA",bg.getTieude());
                                 intent.putExtra("tenbangBLA",tenbang);
                                 intent.putExtra("tenlistBLA",tenlist);
                                 intent.putExtra("idtheBLA",bg.getIdthe().toString());
                                 intent.putExtra("idbangBLA",idbang);
                                 view.getContext().startActivity(intent);
                     }
                 });

    }

    @Override
    public int getItemCount() {
        return BangLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTieude;
        public ImageView imgIcon;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTieude = itemView.findViewById(R.id.cardTitle);
            imgIcon = itemView.findViewById(R.id.cardIcon);
            cardView = itemView.findViewById(R.id.cardViewItem);
        }
    }


}
