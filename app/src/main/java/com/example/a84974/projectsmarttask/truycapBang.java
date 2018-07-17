package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a84974.projectsmarttask.adapter.SlideAdapter;

public class truycapBang extends AppCompatActivity {
    private ViewPager slideView;
    private SlideAdapter slideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truycap_bang);
        slideView = findViewById(R.id.viewPager2);
        slideAdapter = new SlideAdapter(this);
        slideView.setAdapter(slideAdapter);

    }


    public void trove(View view) {
        finish();
    }



}
