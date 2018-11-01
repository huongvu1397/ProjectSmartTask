package com.example.a84974.projectsmarttask.fragment_bang;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a84974.projectsmarttask.Module.BangList;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.BangListAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentDoing extends Fragment {
    private String tenbang, tenMoveBang, gettieudethe, getmotathe, tenlist;
    private int idbang;
    private RecyclerView rcView;
    private Context context;
    private List<BangList> bangLists;
    private BangListAdapter bangListAdapter;
    private TextView themthe,TenList;
    private Toolbar tb;
    private Cursor cursor,cursorXoaDS;
    private DatabaseManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_bang_doing,container,false);
        rcView = view.findViewById(R.id.rcViewFBangDoing);
        themthe = view.findViewById(R.id.btnFBangThemTheDoing);
        TenList = view.findViewById(R.id.textFBangDoing);
        tenlist = TenList.getText().toString();
        manager = new DatabaseManager(getContext());
        //lấy tên bảng, id bảng
        Intent intent = getActivity().getIntent();
        tenbang = intent.getStringExtra("tenbang");
        idbang = intent.getIntExtra("idbang", 0);
        //additem toolbar
        tb = view.findViewById(R.id.tbFBangDoing);
        tb.inflateMenu(R.menu.item_menu_inside_tcbang);
        tbClicked(view);
        //add item rcview
        bangLists = new ArrayList<>();
        bangListAdapter = new BangListAdapter(bangLists,context,rcView,tenbang,tenlist,String.valueOf(idbang));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcView.setLayoutManager(manager);
        //hiển thị thẻ
        getCard();
        //thêm thẻ
        inDialog();
        return view;
    }

    public void tbClicked(View view){
        tb.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //delete list
                    case R.id.moveItemList2:

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Xóa danh sách");
                        builder.setMessage("Bạn có muốn xóa danh sách không ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cursorXoaDS = manager.getCard(tenlist, String.valueOf(idbang));
                                while (cursorXoaDS.moveToNext()) {
                                    int id = cursorXoaDS.getInt(0);
                                    manager.deleteThe(id);
                                    manager.deleteLich(String.valueOf(id));
                                    manager.deleteCVbyThe(String.valueOf(id));
                                }
                                getCard();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                        return true;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void inDialog(){
        themthe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.diaglog_themthe);
                EditText edtTitle = dialog.findViewById(R.id.tieudethe);
                EditText edtMota = dialog.findViewById(R.id.motathe);
                TextView themThe = dialog.findViewById(R.id.btnThem);
                themThe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        gettieudethe = edtTitle.getText().toString().trim();
                        getmotathe = edtMota.getText().toString().trim();
                        manager.inserThe(gettieudethe,getmotathe,tenlist,tenbang,String.valueOf(idbang));
                        getCard();
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
    //hiển thị thẻ
    public void getCard(){
        cursor = manager.getCard(tenlist,String.valueOf(idbang));
        //LOOP AND ADD TO ARRAYLIST
        if(bangLists==null) {
            while (cursor.moveToNext()) {
                int idthe = cursor.getInt(0);
                String name = cursor.getString(1);
                BangList b = new BangList(String.valueOf(idthe), name);
                bangLists.add(b);
            }
            rcView.setAdapter(bangListAdapter);
        }else{
            bangLists.clear();
            while (cursor.moveToNext()) {
                int idthe = cursor.getInt(0);
                String name = cursor.getString(1);
                BangList b = new BangList(String.valueOf(idthe), name);
                bangLists.add(b);
            }
            rcView.setAdapter(bangListAdapter);
        }
    }
}
