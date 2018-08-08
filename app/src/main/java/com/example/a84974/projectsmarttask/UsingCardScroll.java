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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
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
    private Cursor cursorSpinBang;
    private Cursor cursorSpinList;
    private String tenMoveBang,tenMoveList;
    private DatabaseManager manager;
    ListView listcv;
    private LinearLayout ln;
    private SimpleCursorAdapter simpleCursorAdapter;
    ArrayList<CongViec> arrCV;
    CongViecAdapter congViecAdapter;
    private SimpleCursorAdapter adapterSpinCardBang;
    private SimpleCursorAdapter adapterSpinCardList;
    private String listTenlist[] ={"ToDo","Doing","Done"};
    private ArrayAdapter <String> adapter;
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
        //tenbang dat la tag
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
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

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
                moveCardDialog();
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

    public void moveCardDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_move_card);
        TextView movecard = dialog.findViewById(R.id.action_move_card);
        Spinner spincardbang = dialog.findViewById(R.id.spin_move_card_bang);
        Spinner spincardlist = dialog.findViewById(R.id.spin_move_card_list);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listTenlist);
        spincardlist.setAdapter(adapter);
        spincardlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(UsingCardScroll.this, "ToDo", Toast.LENGTH_SHORT).show();
                        tenMoveList = "ToDo";
                        break;
                    case 1:
                        Toast.makeText(UsingCardScroll.this, "Doing", Toast.LENGTH_SHORT).show();
                        tenMoveList = "Doing";
                        break;
                    case 2:
                        Toast.makeText(UsingCardScroll.this, "Done", Toast.LENGTH_SHORT).show();
                        tenMoveList = "Done";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        cursorSpinBang = manager.getBang();

        adapterSpinCardBang = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_spinner_item,
                cursorSpinBang,
                new String[]{"TenBang"},
                new int[]{android.R.id.text1});
        spincardbang.setAdapter(adapterSpinCardBang);
        spincardbang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                tenMoveBang = cursor.getString(1);
                Toast.makeText(view.getContext(), ""+tenMoveBang, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        movecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String title = intent.getStringExtra("title");
                //tenbang dat la tag
                // o day tag de test thoi sua sau no phai la de mota
                String tag = intent.getStringExtra("tenbang");
                // moi move ten the thoi chua move noi dung
                manager.inserThe(title,tag,tenMoveList,tenMoveBang);
                Toast.makeText(view.getContext(), "Move thanh cong", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        dialog.show();
    }



    }


