package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.adapter.BangPagerAdapter;

public class BangPager extends AppCompatActivity {
    private ViewPager viewPager;
    private BangPagerAdapter bangPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_pager_bang);
        viewPager = findViewById(R.id.viewPager2);
        bangPagerAdapter = new BangPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bangPagerAdapter);
        Intent intent = getIntent();
        String tenbang = intent.getStringExtra("tenbang");
        Toast.makeText(this, "BangPager said "+tenbang, Toast.LENGTH_SHORT).show();
    }


    public void trove(View view) {
        finish();
    }



}
