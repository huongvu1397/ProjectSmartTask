package com.example.a84974.projectsmarttask.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a84974.projectsmarttask.Module.CongViec;
import com.example.a84974.projectsmarttask.Module.thongBao;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.UsingCardScroll;
import com.example.a84974.projectsmarttask.ahbottombaractivity.thongBaoBottom;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class thongBaoAdapter extends RecyclerView.Adapter<thongBaoAdapter.ViewHolder> {
    private List<thongBao> thongBaos;
    private Context context;
    private RecyclerView mRecyclerV;
    private Cursor cursor;
    private DatabaseManager manager;
    private CongViecAdapter congViecAdapter;
    private ArrayList<CongViec> arrCV;
    private ListView listView;
    private ImageView deleteView;


    public thongBaoAdapter(List<thongBao> thongBaos, Context context, RecyclerView mRecyclerV) {
        this.thongBaos = thongBaos;
        this.mRecyclerV = mRecyclerV;
        this.context = context;
    }

    @Override
    public thongBaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_thongbao, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(thongBaoAdapter.ViewHolder holder, int position) {
        thongBao tb = thongBaos.get(position);
        holder.tvName.setText(tb.getName());
        holder.tvComment.setText(tb.getComment());
        //set with link ảnh
        //Glide.with(context).load(tb.getAva()).into(holder.imgAvatar);
        holder.imgAvatar.setImageResource(R.drawable.icon_bell_r);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager = new DatabaseManager(view.getContext());
                arrCV = new ArrayList<>();
                congViecAdapter = new CongViecAdapter(view.getContext(), R.layout.item_congviec, arrCV);
                Dialog dialog = new Dialog(context);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_thongbao);
                listView =dialog.findViewById(R.id.listTB);
                deleteView = dialog.findViewById(R.id.deleteTheDialog);
                String idThe = tb.getName().trim();
                GetCVbyID(idThe);
                deleteView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Xóa thẻ");
                        builder.setMessage("Bạn có muốn xóa thẻ không ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                manager.deleteThe(Integer.parseInt(idThe));
                                manager.deleteLich(idThe);
                                manager.deleteCVbyThe(idThe);
                                dialogInterface.dismiss();
                                Intent intent = new Intent(context, thongBaoBottom.class);
                                context.startActivity(intent);
                                ((Activity) context).finish();
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return thongBaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvComment;
        public ImageView imgAvatar;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvComment = itemView.findViewById(R.id.tvComment);
            imgAvatar = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardViewItemTB);
        }
    }

    public void GetCVbyID(String idthe){
        cursor = manager.getCVID(idthe);
        if (congViecAdapter != null) {
            arrCV.clear();
            listView.setAdapter(congViecAdapter);
            while (cursor.moveToNext()) {
                String tencv = cursor.getString(1);
                int id = cursor.getInt(0);
                boolean trangthaicv = Boolean.parseBoolean(cursor.getString(4));
                arrCV.add(new CongViec(id, tencv, trangthaicv));
            }
        } else if (congViecAdapter == null) {
            while (cursor.moveToNext()) {
                String tencv = cursor.getString(1);
                int id = cursor.getInt(0);
                boolean trangthaicv = Boolean.parseBoolean(cursor.getString(4));
                arrCV.add(new CongViec(id, tencv, trangthaicv));
            }
        }
        congViecAdapter.notifyDataSetChanged();
        listView.setAdapter(congViecAdapter);
    }
}
