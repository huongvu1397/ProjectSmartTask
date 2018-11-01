package com.example.a84974.projectsmarttask.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.Module.CongViec;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private Context context;
    private DatabaseManager manager;
    private int layout;
    private Cursor cursor;
    private List<CongViec> list;
    private boolean bienCheck = false;

    public CongViecAdapter(Context context, int layout, List<CongViec> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txt;
        CheckBox cb;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt = view.findViewById(R.id.tencv);
            holder.cb = view.findViewById(R.id.cbcv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CongViec congViec = list.get(i);
        manager = new DatabaseManager(context);
        cursor = manager.getDataCheckBox(congViec.getIdCV());
        holder.txt.setText(congViec.getTenCV());
        //dung nhu nay de luu lai check box
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    manager = new DatabaseManager(context);
                    bienCheck = true;
                    manager.updateCb(congViec.getIdCV(), bienCheck);
                    congViec.setSelected(bienCheck);

                    //load
                    while (cursor.moveToNext()) {
                        String tencv = cursor.getString(0);
                        String trangthai = cursor.getString(1);
                        if (trangthai.equals("1")) {
                            holder.cb.setChecked(true);
                        } else {
                            holder.cb.setChecked(false);
                        }
                    }

                } else {
                    manager = new DatabaseManager(context);
                    bienCheck = false;
                    congViec.setSelected(bienCheck);
                    manager.updateCb(congViec.getIdCV(), bienCheck);

                    //load
                    while (cursor.moveToNext()) {
                        String tencv = cursor.getString(0);
                        String trangthai = cursor.getString(1);
                        if (trangthai.equals("1")) {
                            holder.cb.setChecked(true);
                        } else {
                            holder.cb.setChecked(false);
                        }
                    }

                }
            }
        });
        holder.cb.setChecked(congViec.isSelected());

        //load
        while (cursor.moveToNext()) {
            String tencv = cursor.getString(0);
            String trangthai = cursor.getString(1);
            if (trangthai.equals("1")) {
                holder.cb.setChecked(true);
            } else {
                holder.cb.setChecked(false);
            }
        }

        return view;
    }


}
