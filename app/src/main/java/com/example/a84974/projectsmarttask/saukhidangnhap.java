package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.a84974.projectsmarttask.ahbottombaractivity.taiKhoanBottom;
import com.example.a84974.projectsmarttask.ahbottombaractivity.thongBaoBottom;
import com.example.a84974.projectsmarttask.adapter.MainPagerAdapter;
import com.example.a84974.projectsmarttask.fragment_main.FragmentAnh;
import com.example.a84974.projectsmarttask.fragment_main.FragmentBang;
import com.example.a84974.projectsmarttask.fragment_main.FragmentLich;
import com.example.a84974.projectsmarttask.fragment_main.FragmentVitri;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class saukhidangnhap extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainPagerAdapter pagerAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private FloatingActionMenu fab;
    private FloatingActionButton fab1, fab2, fab3;
    private FragmentBang fragmentBang;
    private com.example.a84974.projectsmarttask.fragment_main.FragmentVitri FragmentVitri;
    private com.example.a84974.projectsmarttask.fragment_main.FragmentLich FragmentLich;
    private com.example.a84974.projectsmarttask.fragment_main.FragmentAnh FragmentAnh;
    //UI
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saukhidangnhap);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        fab = findViewById(R.id.menu_fab);
        fab1 = findViewById(R.id.fab_1);
        fab2 = findViewById(R.id.fab_2);
        fab3 = findViewById(R.id.fab_3);
        fragmentBang = new FragmentBang();
        FragmentVitri = new FragmentVitri();
        FragmentLich = new FragmentLich();
        FragmentAnh = new FragmentAnh();
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentBang, FragmentAnh, FragmentLich, FragmentVitri);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //if(position==3){
                //   FragmentVitri.init();
                //}
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        fab.setClosedOnTouchOutside(true);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        initUI();
    }

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
        // mau khi o vi tri hien tai
        bottomNavigation.setAccentColor(Color.parseColor("#ffffff"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        // Add or remove notification for each item
        //bottomNavigation.setNotification("3", 1);
        // OR
        AHNotification notification = new AHNotification.Builder()
                .setText("4")
                .setBackgroundColor(ContextCompat.getColor(saukhidangnhap.this, R.color.colorAccent))
                .setTextColor(ContextCompat.getColor(saukhidangnhap.this, R.color.mauTrang))
                .build();
        bottomNavigation.setNotification(notification, 1);
        // Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(1);
//        bottomNavigation.enableItemAtPosition(0);
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
        //
        //set item disable color
        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
        // Set listeners khi ấn vào item bottom bar
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //show fragment
                if (position == 0) {
                    //bottomNavigation.disableItemAtPosition(0);
                    startActivity(new Intent(getApplication(), saukhidangnhap.class));
                    finish();
                } else if (position == 1) {
                    //remove notification wwhen clicked
                    bottomNavigation.setNotification("", 1);
                    Intent intent = new Intent(getApplication(), thongBaoBottom.class);
                    startActivity(intent);
                    //finish();
                } else if (position == 2) {

                    startActivity(new Intent(getApplicationContext(), taiKhoanBottom.class));

                }
                return true;
            }
        });


        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Toast.makeText(getApplicationContext(), "Âccacacac", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //onClick trên floating action menu
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_1:
                break;
            case R.id.fab_2:
                Toast.makeText(saukhidangnhap.this, "hello2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_3:

                startActivity(new Intent(this, TaoBang.class));
                //fab.set close gi day
                break;
        }
    }


}
