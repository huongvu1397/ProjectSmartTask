package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a84974.projectsmarttask.ahbottombaractivity.thongBaoBottom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // khi bam nut dang ky
    public void xulydangky(View view) {
        startActivity(new Intent(this,thongBaoBottom.class));
    }

    // khi bam nut dang nhap
    public void xulydangnhap(View view) {
        startActivity(new Intent(this,saukhidangnhap.class));
    }
}
