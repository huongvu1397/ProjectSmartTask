package com.example.a84974.projectwooktime.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a84974.projectwooktime.FragBang;
import com.example.a84974.projectwooktime.fragAnh;
import com.example.a84974.projectwooktime.fragLich;
import com.example.a84974.projectwooktime.fragVitri;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private FragBang fragBang;
    private fragVitri fragVitri;
    private fragLich fragLich;
    private fragAnh fragAnh;
    public MainPagerAdapter(FragmentManager fm,FragBang fragBang,fragAnh fragAnh,fragLich fragLich,fragVitri fragVitri) {
        super(fm);
        this.fragAnh=fragAnh;
        this.fragBang=fragBang;
        this.fragVitri=fragVitri;
        this.fragLich=fragLich;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return fragBang;
            case 1:
                return fragAnh;
            case 2:
                return fragLich;
            case 3:
                return fragVitri;
            default: return fragBang;
        }
    }



    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Bảng";
            case 1:
                return "Ảnh";
            case 2:
                return "Lịch";
            case 3:
                return "Vị trí";
            default: return "Bảng";
        }
    }
}
