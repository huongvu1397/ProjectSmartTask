package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Bang extends AppCompatActivity {
    private ViewPager slideView;
    private LinearLayout mDotlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truycap_bang);
        slideView = findViewById(R.id.viewPager2);
        mDotlayout = findViewById(R.id.mdotlayout);
    }

    public void trove(View view) {
        finish();
    }


}
