package com.example.a84974.projectsmarttask.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.Module.bAng;
import com.example.a84974.projectsmarttask.R;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    RecyclerView rcViewBang;
    List<bAng> bAngs;
    BangAdapter bangAdapter;
    String gettieudethe,getmotathe;
    TextView btnThemThe;
    List<bAng> bAng0;
    List<bAng> bAng1;
    List<bAng> bAng2;

    public SlideAdapter(Context context){
        this.context = context;
    }
    //array
    public int[] slide_images = {
            R.drawable.logo_main,
            R.drawable.logo_main,
            R.drawable.logo_main
    };

    public String[] slide_text={
            "ToDo",
            "Doing",
            "Done"
    };

    @Override
    public int getCount() {
        return slide_text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_slide_bang_layout,container,false);
        //ImageView slideImage = view.findViewById(R.id.imgslide1);
        TextView slidetext = view.findViewById(R.id.slide_text);
        rcViewBang = view.findViewById(R.id.rcViewBang);
        btnThemThe = view.findViewById(R.id.btnThemThe);
        //adapter 1
        if(position==0) {
               slidefragment0();
               btnThemThe.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Dialog dialog = new Dialog(view.getContext());
                       dialog.setTitle("Thêm thẻ");
                       dialog.setCancelable(false);
                       dialog.setContentView(R.layout.diaglog_themthe);

                       EditText edtTitle = dialog.findViewById(R.id.tieudethe);
                       EditText edtMota = dialog.findViewById(R.id.motathe);
                       TextView themThe = dialog.findViewById(R.id.btnThem);
                       themThe.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view1) {
                               gettieudethe = edtTitle.getText().toString().trim();
                               getmotathe = edtMota.getText().toString().trim();
                               bAng0.add(new bAng(gettieudethe));
                               dialog.cancel();
                           }
                       });
                       dialog.show();
                   }
               });
        }
        //adapter 2
        if(position==1){
            slidefragment1();
            btnThemThe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(view.getContext());
                    dialog.setTitle("Thêm thẻ");
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.diaglog_themthe);

                    EditText edtTitle = dialog.findViewById(R.id.tieudethe);
                    EditText edtMota = dialog.findViewById(R.id.motathe);
                    TextView themThe = dialog.findViewById(R.id.btnThem);
                    themThe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            gettieudethe = edtTitle.getText().toString().trim();
                            getmotathe = edtMota.getText().toString().trim();
                            bAng1.add(new bAng(gettieudethe));
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        }

        if(position==2){
            slidefragment2();
            btnThemThe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(view.getContext());
                    dialog.setTitle("Thêm thẻ");
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.diaglog_themthe);

                    EditText edtTitle = dialog.findViewById(R.id.tieudethe);
                    EditText edtMota = dialog.findViewById(R.id.motathe);
                    TextView themThe = dialog.findViewById(R.id.btnThem);
                    themThe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            gettieudethe = edtTitle.getText().toString().trim();
                            getmotathe = edtMota.getText().toString().trim();
                            bAng2.add(new bAng(gettieudethe));
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        }

        //slideImage.setImageResource(slide_images[position]);
        slidetext.setText(slide_text[position]);

        container.addView(view);


        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
    //adapter 0
    public void fakeData0(){
        bAng0.add(new bAng("Tên thẻ 1"));
        bAng0.add(new bAng("Tên thẻ 2"));
    }
    //fakedata1
    public void fakeData1(){
        bAng1.add(new bAng("Tên thẻ 3"));
        bAng1.add(new bAng("Tên thẻ 4"));
    }
    // slide fragment 0
    public void slidefragment0(){
        bAng0 = new ArrayList<>();
        fakeData0();
        bangAdapter = new BangAdapter(bAng0);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcViewBang.setLayoutManager(manager);
        rcViewBang.setAdapter(bangAdapter);
    }
    //slide fragment 1
    public void slidefragment1(){
        bAng1 = new ArrayList<>();
        fakeData1();
        bangAdapter = new BangAdapter(bAng1);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcViewBang.setLayoutManager(manager);
        rcViewBang.setAdapter(bangAdapter);
    }
    //slide fragment 2
    public void slidefragment2(){
        bAng2 = new ArrayList<>();
        bangAdapter = new BangAdapter(bAng2);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rcViewBang.setLayoutManager(manager);
        rcViewBang.setAdapter(bangAdapter);
    }

}
