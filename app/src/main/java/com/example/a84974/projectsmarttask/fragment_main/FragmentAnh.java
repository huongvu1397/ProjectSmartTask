package com.example.a84974.projectsmarttask.fragment_main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.AnhAdapter;

public class FragmentAnh extends Fragment {
    private RecyclerView rcViewAnh;
    private int[] images = {R.drawable.logo_main,
            R.drawable.logo_main,R.drawable.anh_1,R.drawable.anh_2,
            R.drawable.anh_3,R.drawable.anh_4,R.drawable.anh_5};
    private RecyclerView.LayoutManager layoutManager;
    private AnhAdapter anhAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_main_anh,container,false);
        rcViewAnh = view.findViewById(R.id.rcViewAnh);
        layoutManager = new GridLayoutManager(getContext(),2);
        rcViewAnh.setHasFixedSize(true);
        rcViewAnh.setLayoutManager(layoutManager);
        anhAdapter = new AnhAdapter(images);
        rcViewAnh.setAdapter(anhAdapter);
        return view;
    }
}
