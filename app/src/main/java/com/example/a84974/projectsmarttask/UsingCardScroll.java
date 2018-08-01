package com.example.a84974.projectsmarttask;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import android.text.format.DateFormat;

import com.example.a84974.projectsmarttask.Module.CongViec;
import com.example.a84974.projectsmarttask.adapter.CongViecAdapter;
import com.example.a84974.projectsmarttask.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class UsingCardScroll extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    TextView tit,subtit,ngayhoanthanh;
    String tenlist,tenbang,tencv1;
    int day,mon,year,hour,minute;
    int dayF,monF,yearF,hourF,minuteF;
    private ExpandableListView lvCategory;
    private Cursor cursor;
    private DatabaseManager manager;
    ListView listcv;
    private LinearLayout ln;
    private SimpleCursorAdapter simpleCursorAdapter;
    ArrayList<CongViec> arrCV;
    CongViecAdapter congViecAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_card_scrolling);
        listcv = findViewById(R.id.listviewCV);
        manager = new DatabaseManager(this);
        ln = findViewById(R.id.checklist);
        arrCV = new ArrayList<>();
        congViecAdapter = new CongViecAdapter(this,R.layout.item_congviec,arrCV);
        listcv.setAdapter(congViecAdapter);
        subtit = findViewById(R.id.subtitle_cor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ngayhoanthanh = findViewById(R.id.ngayhoanthanh);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        //tenbang dat la  tag :v loi
        String tag = intent.getStringExtra("tenbang");
        getSupportActionBar().setTitle(title);
        subtit.setText(tag);
        Toast.makeText(this, ""+tag, Toast.LENGTH_SHORT).show();
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(UsingCardScroll.this);
                dialog.setContentView(R.layout.dialog_themcongviec);
                EditText edtTenCV = dialog.findViewById(R.id.edttencv);

                TextView themCV = dialog.findViewById(R.id.btnThemCongViec);
                themCV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        // su kien nut them cv
                         tencv1 = edtTenCV.getText().toString().trim();
                        manager.insertCongViec(tencv1,title,tag);
                        //bangLists.add(new BangList(gettieudethe));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getCV(title,tag);
        setupReferences();
        ngayhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                mon = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UsingCardScroll.this,UsingCardScroll.this,year,mon,day);
                datePickerDialog.show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_deepinside_tcbang,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.moveCard:
                Toast.makeText(this, "Move Card", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.deleteCard:
                Toast.makeText(this, "Delete Card", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
              yearF = i;
              monF = i1+1;
              dayF = i2;
              Calendar c = Calendar.getInstance();
              hour = c.get(Calendar.HOUR_OF_DAY);
              minute = c.get(Calendar.MINUTE);

              TimePickerDialog timePickerDialog = new TimePickerDialog(UsingCardScroll.this,UsingCardScroll.this,
                      hour,minute, DateFormat.is24HourFormat(this));
              timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
             hourF =i;
             minuteF = i1;
             ngayhoanthanh.setText("Het han vao "+dayF+" thang "+monF+" luc "+hourF+":"+minuteF);
    }

    private void setupReferences() {
        //lvCategory = findViewById(R.id.lvCategory);
    }

    //hiển thị cv
    public void getCV(String title,String tenbang){

        cursor = manager.getCV(title,tenbang);
        while(cursor.moveToNext()){
            String tencv = cursor.getString(1);
            int id = cursor.getInt(0);
            arrCV.add(new CongViec(id,tencv));
        }
        congViecAdapter.notifyDataSetChanged();
        listcv.setAdapter(congViecAdapter);

    }

    }


