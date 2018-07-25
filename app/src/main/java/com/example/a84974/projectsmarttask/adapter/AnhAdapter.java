package com.example.a84974.projectsmarttask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.R;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.ImageViewHolder> {
    private int[] images;
    public AnhAdapter(int[] images){
        this.images = images;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anh_anh,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
           int images_id = images[position];
           holder.anh.setImageResource(images_id);
           holder.text.setText("Image "+position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView anh;
        TextView text;
        public ImageViewHolder(View itemView) {
            super(itemView);
            anh =  itemView.findViewById(R.id.ivAnh);
            text = itemView.findViewById(R.id.txtAnh);
        }
    }
}
