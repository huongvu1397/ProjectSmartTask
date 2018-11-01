package com.example.a84974.projectsmarttask;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a84974.projectsmarttask.database.DatabaseManager;
import com.example.a84974.projectsmarttask.fragment_main.FragmentBang;

public class TaoBang extends AppCompatActivity {
    private Spinner spiner;
    private EditText edtTenBang;
    private DatabaseManager manager;
    private ImageView txtlaymau;
    private Toolbar toolbar;
    String layquyenxem ="";
    String laymau ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_bang);
        spiner = findViewById(R.id.spinnertb);
        edtTenBang = findViewById(R.id.editTenBang);
        txtlaymau = findViewById(R.id.laymau);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        manager = new DatabaseManager(this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spineritem)
        );

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(myAdapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0: layquyenxem ="private"; break;
                    case 1: layquyenxem ="public"; break;
                    default: layquyenxem ="private"; break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void taomoibang(View view) {
        String tenbang = edtTenBang.getText().toString().trim();
        String quyenxem = layquyenxem.trim() ;
        ColorDrawable maunentext = (ColorDrawable) txtlaymau.getBackground();
        //maunentext.getColor();
        //String maunen = txtlaymau.getBackground().toString().trim();
        if(TextUtils.isEmpty(tenbang)){
            Toast.makeText(this, "Nhập tên bảng", Toast.LENGTH_SHORT).show();
        }
        else{
            manager.inserBang(tenbang,quyenxem, String.valueOf(maunentext.getColor()));
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(TaoBang.this, saukhidangnhap.class);
            startActivity(refresh);
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent refresh = new Intent(TaoBang.this, saukhidangnhap.class);
                startActivity(refresh);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
