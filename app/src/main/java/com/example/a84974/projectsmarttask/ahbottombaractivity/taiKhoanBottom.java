package com.example.a84974.projectsmarttask.ahbottombaractivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.saukhidangnhap;

import java.util.ArrayList;

public class taiKhoanBottom extends AppCompatActivity {
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_bottom);
        initUI();
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
        bottomNavigation.setAccentColor(Color.parseColor("#ffffff"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(2);
        //set item disable color
        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
        // Set listeners khi ấn vào item bottom bar
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //show fragment
                if(position==0){
                    //bottomNavigation.disableItemAtPosition(0);
                    startActivity(new Intent(getApplication(), saukhidangnhap.class));
                    finish();
                }
                else if(position==1){
                    Intent intent = new Intent(getApplication(),thongBaoBottom.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2){

                    startActivity(new Intent(getApplicationContext(), taiKhoanBottom.class));
                    finish();

                }
                return true;
            }
        }) ;



        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Toast.makeText(getApplicationContext(), "Âccacacac", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
