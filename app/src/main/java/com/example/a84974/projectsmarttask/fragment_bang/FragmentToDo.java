package com.example.a84974.projectsmarttask.fragment_bang;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.Module.BangList;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.BangListAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentToDo extends Fragment {
    String tenbang;
    RecyclerView rcView;
    Context context;
    List<BangList> bangLists;
    BangListAdapter bangListAdapter;
    TextView themthe,TenList;
    String gettieudethe, getmotathe,tenlist;
    Toolbar tb;
    private Cursor cursor;
    private DatabaseManager manager;
    private SimpleCursorAdapter simpleCursorAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_bang_todo, container, false);
        rcView = view.findViewById(R.id.rcViewFBangTodo);
        themthe = view.findViewById(R.id.btnFBangThemTheTodo);
        TenList = view.findViewById(R.id.textFBangTodo);
        tenlist = TenList.getText().toString();
        Intent intent = getActivity().getIntent();
        tenbang = intent.getStringExtra("tenbang");
        //Toast.makeText(view.getContext(), "Fragment To Do said"+tenbang, Toast.LENGTH_SHORT).show();

        //add item
        tb = view.findViewById(R.id.tbFBangTodo);
        tb.inflateMenu(R.menu.item_menu_inside_tcbang);
        //Menu menu = tb.getMenu();
        tbClicked(view);
        manager = new DatabaseManager(getContext());

        bangLists = new ArrayList<>();
        //fakeData();
        bangListAdapter = new BangListAdapter(bangLists, context,rcView,tenbang);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcView.setLayoutManager(manager);
        getCard();
        inDialog();
        return view;
    }

    // sự kiện cho toolbar option menu
    public void tbClicked(View view) {
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.moveItemList:
                        Toast.makeText(view.getContext(), "Move List", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.moveItemList2:
                        Toast.makeText(view.getContext(), "Delete List", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });
    }


//    public void fakeData() {
//        bangLists.add(new BangList("Tên thẻ 1"));
//        bangLists.add(new BangList("Tên thẻ 2"));
//    }

    public void inDialog() {
        themthe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setTitle("Thêm thẻ");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.diaglog_themthe);
                EditText edtTitle = dialog.findViewById(R.id.tieudethe);
                EditText edtMota = dialog.findViewById(R.id.motathe);
                TextView themThe = dialog.findViewById(R.id.btnThem);
                themThe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        // su kien nut them the
                        gettieudethe = edtTitle.getText().toString().trim();
                        getmotathe = edtMota.getText().toString().trim();
                        manager.inserThe(gettieudethe,getmotathe,tenlist,tenbang);
                        //bangLists.add(new BangList(gettieudethe));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
    public void getCard(){
        cursor = manager.getCard(tenlist,tenbang);
        //LOOP AND ADD TO ARRAYLIST
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String mota=cursor.getString(2);
            String taglist = cursor.getString(3);
            BangList b = new BangList(name);
            bangLists.add(b);
        }
        rcView.setAdapter(bangListAdapter);
    }
}
