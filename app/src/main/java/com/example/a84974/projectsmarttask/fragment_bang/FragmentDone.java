package com.example.a84974.projectsmarttask.fragment_bang;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.Module.BangList;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.BangListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentDone extends Fragment {
    RecyclerView rcView;
    Context context;
    List<BangList> bangLists;
    BangListAdapter bangListAdapter;
    TextView themthe;
    String gettieudethe,getmotathe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_bang_done,container,false);
        rcView = view.findViewById(R.id.rcViewFBangDone);
        themthe = view.findViewById(R.id.btnFBangThemTheDone);
        bangLists = new ArrayList<>();
        fakeData();
        bangListAdapter = new BangListAdapter(bangLists,context);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcView.setLayoutManager(manager);
        rcView.setAdapter(bangListAdapter);
        inDialog();
        return view;
    }
    public void fakeData(){
//        bangLists.add(new BangList("Tên thẻ 1"));
//        bangLists.add(new BangList("Tên thẻ 2"));
    }
    public void inDialog(){
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
                        gettieudethe = edtTitle.getText().toString().trim();
                        getmotathe = edtMota.getText().toString().trim();
                        bangLists.add(new BangList(gettieudethe));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
}
