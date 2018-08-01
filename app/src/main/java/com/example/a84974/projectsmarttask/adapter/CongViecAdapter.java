package com.example.a84974.projectsmarttask.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.Module.CongViec;
import com.example.a84974.projectsmarttask.R;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CongViec> list;

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

    private class ViewHolder{
        TextView txt ;
        CheckBox cb;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder;
       if(view == null){
           holder = new ViewHolder();
           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(layout,null);
           holder.txt = view.findViewById(R.id.tencv);
           holder.cb = view.findViewById(R.id.cbcv);
           view.setTag(holder);
       }else{
           holder = (ViewHolder)view.getTag();
       }
       CongViec congViec = list.get(i);
       holder.txt.setText(congViec.getTenCV());
       return view;

    }
}
