package com.example.a84974.projectsmarttask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a84974.projectsmarttask.Module.thongBao;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.ahbottombaractivity.thongBaoBottom;

import java.util.List;

public class thongBaoAdapter extends RecyclerView.Adapter<thongBaoAdapter.ViewHolder> {
     private List<thongBao> thongBaos;
     private Context context;

    public thongBaoAdapter(List<thongBao> thongBaos, Context context) {
        this.thongBaos = thongBaos;
        this.context = context;
    }

    @Override
    public thongBaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_thongbao,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(thongBaoAdapter.ViewHolder holder, int position) {
             thongBao tb = thongBaos.get(position);
             holder.tvName.setText(tb.getName());
             holder.tvComment.setText(tb.getComment());
        Glide.with(context).load(tb.getAva()).into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return thongBaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvComment;
        ImageView imgAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvComment = itemView.findViewById(R.id.tvComment);
            imgAvatar = itemView.findViewById(R.id.imageView);
        }
    }
}
