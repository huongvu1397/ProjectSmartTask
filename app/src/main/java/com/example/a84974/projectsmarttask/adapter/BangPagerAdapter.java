package com.example.a84974.projectsmarttask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a84974.projectsmarttask.fragment_bang.FragmentDoing;
import com.example.a84974.projectsmarttask.fragment_bang.FragmentDone;
import com.example.a84974.projectsmarttask.fragment_bang.FragmentThem;
import com.example.a84974.projectsmarttask.fragment_bang.FragmentToDo;

public class BangPagerAdapter extends FragmentPagerAdapter {
    public BangPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentToDo();
            case 1: return new FragmentDoing();
            case 2: return new FragmentDone();
            case 3: return new FragmentThem();
            default: return new FragmentToDo();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
