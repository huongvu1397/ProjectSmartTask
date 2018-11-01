package com.example.a84974.projectsmarttask.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a84974.projectsmarttask.fragment_main.FragmentBang;
import com.example.a84974.projectsmarttask.fragment_main.FragmentAnh;
import com.example.a84974.projectsmarttask.fragment_main.FragmentLich;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private FragmentBang fragmentBang;
    private FragmentLich FragmentLich;
    private FragmentAnh FragmentAnh;

    public MainPagerAdapter(FragmentManager fm, FragmentBang fragmentBang, FragmentAnh FragmentAnh, FragmentLich FragmentLich) {
        super(fm);
        this.FragmentAnh = FragmentAnh;
        this.fragmentBang = fragmentBang;
        this.FragmentLich = FragmentLich;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentBang;
            case 1:
                return FragmentAnh;
            case 2:
                return FragmentLich;

            default:
                return fragmentBang;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bảng";
            case 1:
                return "Ảnh";
            case 2:
                return "Lịch";
            default:
                return "Bảng";
        }
    }
}
