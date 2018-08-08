package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.adapter.BangPagerAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

public class BangPager extends AppCompatActivity {
    private ViewPager viewPager;
    private BangPagerAdapter bangPagerAdapter;
    private Toolbar tb;
    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_pager_bang);
        viewPager = findViewById(R.id.viewPager2);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        manager = new DatabaseManager(this);
        //btn back
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        bangPagerAdapter = new BangPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bangPagerAdapter);
        Intent intent = getIntent();
        String tenbang = intent.getStringExtra("tenbang");
        Toast.makeText(this, "BangPager said "+tenbang, Toast.LENGTH_SHORT).show();
    }
    //back button
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_xoabang:
                Intent intent = getIntent();
                String tenbang = intent.getStringExtra("tenbang");
                Toast.makeText(getApplicationContext(),"Item 1 Selected"+tenbang,Toast.LENGTH_LONG).show();
                manager.deleteBang(tenbang);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void trove(View view) {
        finish();
    }



}
