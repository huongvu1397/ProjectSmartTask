package com.example.a84974.projectsmarttask.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a84974.projectsmarttask.FragmentBang;
import com.example.a84974.projectsmarttask.FragmentAnh;
import com.example.a84974.projectsmarttask.FragmentLich;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private FragmentBang fragmentBang;
    private com.example.a84974.projectsmarttask.FragmentVitri FragmentVitri;
    private FragmentLich FragmentLich;
    private FragmentAnh FragmentAnh;
    public MainPagerAdapter(FragmentManager fm, FragmentBang fragmentBang, FragmentAnh FragmentAnh, FragmentLich FragmentLich, com.example.a84974.projectsmarttask.FragmentVitri FragmentVitri) {
        super(fm);
        this.FragmentAnh = FragmentAnh;
        this.fragmentBang = fragmentBang;
        this.FragmentVitri = FragmentVitri;
        this.FragmentLich = FragmentLich;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return fragmentBang;
            case 1:
                return FragmentAnh;
            case 2:
                return FragmentLich;
            case 3:
                return FragmentVitri;
            default: return fragmentBang;
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
