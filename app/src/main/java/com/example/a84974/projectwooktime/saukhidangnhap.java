package com.example.a84974.projectwooktime;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.a84974.projectwooktime.adapter.MainPagerAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class saukhidangnhap extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainPagerAdapter pagerAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private FloatingActionMenu fab;
    private FloatingActionButton fab1,fab2,fab3;
    private AHBottomNavigation bottomNavigation;
    private FragBang fragBang;
    private fragVitri fragVitri;
    private fragLich fragLich;
    private fragAnh fragAnh;

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
        fragBang = new FragBang();
        fragVitri = new fragVitri();
        fragLich = new fragLich();
        fragAnh = new fragAnh();
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),fragBang,fragAnh,fragLich,fragVitri);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                       if(position==3){
                           fragVitri.init();
                       }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setClosedOnTouchOutside(true);
        //fab.hideMenuButton(false);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);




        bottomNavigation =  findViewById(R.id.bottomNavigationView);
        //create item
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_launcher_background, R.color.mauTrang);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_launcher_background, R.color.mauTrang);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_launcher_background, R.color.mauTrang);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        //add item
        bottomNavigation.addItems(bottomNavigationItems);
        //setcolor
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#0091EA"));
        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);
        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
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
        //bottomNavigation.disableItemAtPosition(2);
        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position==0){
                        startActivity(new Intent(getApplicationContext(),saukhidangnhap.class));
                        finish();
                        bottomNavigation.disableItemAtPosition(0);
                }
                if(position==1){
                    //remove notification wwhen clicked
                    bottomNavigation.setNotification("",1);


                }

                return true;
            }
        }) ;
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Toast.makeText(saukhidangnhap.this, "Hello mother fucker", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Show or hide the bottom navigation with animation
     */

    public void showOrHideBottomNavigation(boolean show) {
        if (show) {
            bottomNavigation.restoreBottomNavigation(true);
        } else {
            bottomNavigation.hideBottomNavigation(true);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_1:
                break;
            case R.id.fab_2:
                Toast.makeText(saukhidangnhap.this, "hello2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_3:
                
                startActivity(new Intent(this,taoBang.class));
                //fab.set close gi day
                break;
        }
    }
}
