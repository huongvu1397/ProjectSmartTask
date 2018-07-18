package com.example.a84974.projectsmarttask.fragment_main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.BangPager;

public class FragmentBang extends Fragment {
    private ListView lstViewcanhan,lstViewnhom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_fragment_main_bang,container,false);
            lstViewcanhan = view.findViewById(R.id.lstViewCaNhan);
            //tam thoi set tinh
            lstViewcanhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent a = new Intent(getActivity(),BangPager.class);
                    startActivity(a);

                }
            });


            return view;
    }


}
