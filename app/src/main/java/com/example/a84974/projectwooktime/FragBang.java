package com.example.a84974.projectwooktime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragBang extends Fragment {
    private ListView lstViewcanhan,lstViewnhom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_frag_bang,container,false);
            lstViewcanhan = view.findViewById(R.id.lstViewCaNhan);
            //tam thoi set tinh
            lstViewcanhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent a = new Intent(getActivity(),truycapBang.class);
                    startActivity(a);

                }
            });


            return view;
    }


}
