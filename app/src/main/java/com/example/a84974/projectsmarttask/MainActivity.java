package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a84974.projectsmarttask.ahbottombaractivity.thongBaoBottom;
import com.example.a84974.projectsmarttask.bqh.ActivityDangNhap;
import com.example.a84974.projectsmarttask.bqh.SignUp_Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // khi bam nut dang ky
    public void xulydangky(View view) {
        startActivity(new Intent(this,SignUp_Activity.class));
    }

    // khi bam nut dang nhap
    public void xulydangnhap(View view) {
        startActivity(new Intent(this,ActivityDangNhap.class));
    }

    public void saukhidangnhap(View view) {
        startActivity(new Intent(this,saukhidangnhap.class));
    }
}
