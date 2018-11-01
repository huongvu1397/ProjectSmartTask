package com.example.a84974.projectsmarttask.pvc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.R;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Image> imageList;

    public ImageAdapter(Context context, int layout, List<Image> imageList) {
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txTitle,txDescription;
        ImageView imghinh;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txTitle= (TextView)convertView.findViewById(R.id.tvTitleItem);
            holder.txDescription=(TextView)convertView.findViewById(R.id.tvDescriptionItem);
            holder.imghinh=(ImageView)convertView.findViewById(R.id.imageItem);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Image image = imageList.get(position);

        holder.txTitle.setText(image.getTitle());
        holder.txDescription.setText(image.getDescription());
        // chuyen mang byte -> bitmap
        byte[] hinhAnh = image.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        holder.imghinh.setImageBitmap(bitmap);

        return convertView;
    }

}
