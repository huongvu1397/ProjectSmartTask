package com.example.a84974.projectsmarttask.ahbottombaractivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.a84974.projectsmarttask.Module.thongBao;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.adapter.thongBaoAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;
import com.example.a84974.projectsmarttask.saukhidangnhap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class thongBaoBottom extends AppCompatActivity {
    private thongBaoAdapter thongbaoAdapters;
    private RecyclerView rcView;
    private List<thongBao> thongBaoList;
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private TextView txtTB;
    private DatabaseManager manager;
    private Cursor cursor, cursor2;
    private long realtime = 0;
    private int rYear, rMon, rDay, rHour, rMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_bottom);
        rcView = findViewById(R.id.rcView);
        manager = new DatabaseManager(this);
        thongBaoList = new ArrayList<>();
        thongbaoAdapters = new thongBaoAdapter(thongBaoList, this, rcView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rcView.setLayoutManager(manager);
        txtTB = findViewById(R.id.txtThongBao);
        Calendar c = Calendar.getInstance();
        realtime = c.getTimeInMillis();

        rYear = c.get(Calendar.YEAR);
        // rMon 0-11
        rMon = c.get(Calendar.MONTH) + 1;
        rDay = c.get(Calendar.DAY_OF_MONTH);
        rHour = c.get(Calendar.HOUR_OF_DAY);
        rMin = c.get(Calendar.MINUTE);
        getTB();
        initUI();
    }


    public void getTB() {
        cursor = manager.getCardAll();
        //LOOP AND ADD TO ARRAYLIST
        if (thongBaoList == null) {
            while (cursor.moveToNext()) {
                String timemilis = "";
                int yF = 0, mF = 0, dF = 0, hF = 0, minF = 0;
                int idthe = cursor.getInt(0);
                String name = cursor.getString(1);
                cursor2 = manager.getNgayHoanThanh(String.valueOf(idthe));
                while (cursor2.moveToNext()) {
                    minF = Integer.parseInt(cursor2.getString(1));
                    hF = Integer.parseInt(cursor2.getString(2));
                    dF = Integer.parseInt(cursor2.getString(3));
                    mF = Integer.parseInt(cursor2.getString(4));
                    yF = Integer.parseInt(cursor2.getString(5));
                    timemilis = cursor2.getString(7);
                }
                String ava = "";
                String cm = "Thẻ hết hạn vào "+minF+":"+hF+" ngày "+dF+" tháng "+mF+"";
                //String ava = "https://vignette.wikia.nocookie.net/simpsons/images/9/99/Nedcrazy.png/revision/latest?cb=20111201091045";
                thongBao b = new thongBao(String.valueOf(idthe),name,cm,ava,timemilis);
                if (timemilis.equals("") || timemilis.equals(null)) {
                } else if ((rYear >= yF) &&
                        (rMon >= mF) &&
                        (rDay >= dF) &&
                        (rHour >= hF) &&
                        (rMin >= minF)
                        ) {
                    thongBaoList.add(0, b);
                    txtTB.setText("");
                }

            }
            rcView.setAdapter(thongbaoAdapters);
        } else {
            thongBaoList.clear();
            while (cursor.moveToNext()) {
                String timemilis = "";
                int yF = 0, mF = 0, dF = 0, hF = 0, minF = 0;
                int idthe = cursor.getInt(0);
                String name = cursor.getString(1);
                cursor2 = manager.getNgayHoanThanh(String.valueOf(idthe));
                while (cursor2.moveToNext()) {
                    minF = Integer.parseInt(cursor2.getString(1));
                    hF = Integer.parseInt(cursor2.getString(2));
                    dF = Integer.parseInt(cursor2.getString(3));
                    mF = Integer.parseInt(cursor2.getString(4));
                    yF = Integer.parseInt(cursor2.getString(5));
                    timemilis = cursor2.getString(7);
                }
                String minX = ""+minF;
                if(minF<10){
                    minX = "0"+minF;
                }else{
                    minX = ""+minF;
                }
                String cm = "Thẻ hết hạn vào "+hF+":"+minX+" ngày "+dF+" tháng "+mF+"";
                String ava = "";
                //String ava = "https://vignette.wikia.nocookie.net/simpsons/images/9/99/Nedcrazy.png/revision/latest?cb=20111201091045";
                thongBao b = new thongBao(String.valueOf(idthe),name,cm,ava,timemilis);
                if (timemilis.equals("") || timemilis.equals(null)) {
                    Log.d("Bump", "không có thời gian hạn chế");
                } else if ((rYear >= yF) &&
                        (rMon >= mF) &&
                        (rDay >= dF) &&
                        (rHour >= hF) &&
                        (rMin >= minF)) {
                    thongBaoList.add(0, b);
                    txtTB.setText("");
                }
            }
            rcView.setAdapter(thongbaoAdapters);
        }
    }

    //bottom bar
    private void initUI() {
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        //create item
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_home, R.color.mauTrang);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.title_noti, R.drawable.ic_public_white, R.color.mauTrang);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.title_user, R.drawable.ic_action_user, R.color.mauTrang);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        //thêm item vào bottom bar qua arraylist bottomNavigationItems
        bottomNavigation.addItems(bottomNavigationItems);
        //màu nền của bottom bar
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#0091EA"));
        // Disable the translation inside the CoordinatorLayout
        //bottomNavigation.setBehaviorTranslationEnabled(false);
        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#ffffff"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(1);
        // Add or remove notification for each item
        //bottomNavigation.setNotification("3", 1);
        // OR
        AHNotification notification = new AHNotification.Builder()
                .setText("")
                .setBackgroundColor(ContextCompat.getColor(thongBaoBottom.this, R.color.colorAccent))
                .setTextColor(ContextCompat.getColor(thongBaoBottom.this, R.color.mauTrang))
                .build();
        bottomNavigation.setNotification(notification, 1);
        //set item disable color
        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
        // Set listeners khi ấn vào item bottom bar
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //show fragment
                if (position == 0) {
                    startActivity(new Intent(getApplication(), saukhidangnhap.class));
                    finish();
                } else if (position == 1) {
                    Intent intent = new Intent(getApplication(), thongBaoBottom.class);
                    startActivity(intent);
                    finish();
                } else if (position == 2) {

                }
                return true;
            }
        });


        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
            }
        });

    }
}
