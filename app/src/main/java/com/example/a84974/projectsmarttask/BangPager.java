package com.example.a84974.projectsmarttask;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
        tb = findViewById(R.id.toolbar_bang_pager);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        manager = new DatabaseManager(this);
        //btn back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bangPagerAdapter = new BangPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bangPagerAdapter);
        //dot
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        Intent intent = getIntent();
        String tenbang = intent.getStringExtra("tenbang");
        int idbang = intent.getIntExtra("idbang", 0);
        //set title task
        getSupportActionBar().setTitle(tenbang);
    }


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
                int idbang = intent.getIntExtra("idbang", 0);
                //xóa bảng theo id bảng
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xóa Bảng");
                builder.setMessage("Bạn có muốn xóa bảng không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        manager.deleteBang(idbang);
                        //xóa công việc trong bảng theo id bảng
                        manager.deleteCVbyBang(String.valueOf(idbang));
                        //xóa thẻ trong bảng theo id bảng
                        manager.deleteThebyBang(String.valueOf(idbang));
                        manager.deleteLichbyBang(String.valueOf(idbang));
                        dialogInterface.dismiss();
                        Intent intentXoa = new Intent(BangPager.this,saukhidangnhap.class);
                        startActivity(intentXoa);
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            //icon trở về
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void trove(View view) {
        finish();
    }


}
