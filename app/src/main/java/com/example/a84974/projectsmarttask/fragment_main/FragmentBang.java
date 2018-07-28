package com.example.a84974.projectsmarttask.fragment_main;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.BangPager;
import com.example.a84974.projectsmarttask.UsingCardScroll;
import com.example.a84974.projectsmarttask.database.DatabaseManager;
import com.example.a84974.projectsmarttask.fragment_bang.FragmentToDo;

public class FragmentBang extends Fragment {
    private ListView lstViewcanhan,lstViewnhom;
    private Cursor cursor;
    private DatabaseManager manager;
    private SimpleCursorAdapter simpleCursorAdapter;
    private String tenbang="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_fragment_main_bang,container,false);
            lstViewcanhan = view.findViewById(R.id.lstViewCaNhan);
            manager = new DatabaseManager(getContext());

            //tam thoi set tinh
            lstViewcanhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent a = new Intent(view.getContext(),BangPager.class);
                        TextView tagText = view.findViewById(R.id.tvTenBang);
                        String tag = tagText.getText().toString().trim();
                        a.putExtra("tenbang",tag);
                        startActivity(a);

                }
            });
            getBang();


            return view;
    }
    public void getBang(){
        cursor = manager.getBang();
        simpleCursorAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.item_list_canhan,
                cursor,
                new String[]{"TenBang"},
                new int[]{R.id.tvTenBang});
        lstViewcanhan.setAdapter(simpleCursorAdapter);
    }


}
