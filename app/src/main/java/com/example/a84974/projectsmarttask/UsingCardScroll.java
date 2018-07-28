package com.example.a84974.projectsmarttask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import android.text.format.DateFormat;
import java.util.Calendar;

public class UsingCardScroll extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    TextView tit,subtit,ngayhoanthanh;
    int day,mon,year,hour,minute;
    int dayF,monF,yearF,hourF,minuteF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_card_scrolling);
        subtit = findViewById(R.id.subtitle_cor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ngayhoanthanh = findViewById(R.id.ngayhoanthanh);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String tag = intent.getStringExtra("tenbang");
        getSupportActionBar().setTitle(title);
        subtit.setText(tag);
        Toast.makeText(this, ""+tag, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
}
