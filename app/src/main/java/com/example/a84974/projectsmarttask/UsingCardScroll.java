package com.example.a84974.projectsmarttask;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import android.text.format.DateFormat;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.a84974.projectsmarttask.Module.CongViec;
import com.example.a84974.projectsmarttask.adapter.CongViecAdapter;
import com.example.a84974.projectsmarttask.broadcastservice.AlarmReceiver;
import com.example.a84974.projectsmarttask.database.DatabaseManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UsingCardScroll extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView ngayhoanthanh, mota;
    String tenbang, tencv1, getmota;
    int day, mon, year, hour, minute,idMoveBang,idthenhancongviec;
    long timemili,endMillis ;
    int dayF, monF, yearF, hourF, minuteF;
    private Cursor cursor,cursor1,cursor2,cursor3,cursor4,cursor5;
    private Cursor cursorSpinBang;
    private Cursor cursorThe,cursorTheMove,cursorTheMove2;
    private String tenMoveBang, tenMoveList;
    private DatabaseManager manager;
    private SwipeMenuListView listcv;
    private LinearLayout ln;
    private SimpleCursorAdapter simpleCursorAdapter;
    ArrayList<CongViec> arrCV;
    CongViecAdapter congViecAdapter;
    private SimpleCursorAdapter adapterSpinCardBang;
    private SimpleCursorAdapter adapterCongViec;

    private String listTenlist[] = {"ToDo", "Doing", "Done"};
    private ArrayAdapter<String> adapter;
    private String trangthaibandau = "0";

    //bqh

    private EditText edtCmt;
    private ListView lvListCmt;
    private String date;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_card_scrolling);
        listcv = findViewById(R.id.listviewCV);
        manager = new DatabaseManager(this);
        ln = findViewById(R.id.checklist);
        arrCV = new ArrayList<>();
        congViecAdapter = new CongViecAdapter(this, R.layout.item_congviec, arrCV);
        listcv.setAdapter(congViecAdapter);
        mota = findViewById(R.id.card_mota);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ngayhoanthanh = findViewById(R.id.ngayhoanthanh);
        Intent intent = getIntent();
        //tên thẻ
        String title = intent.getStringExtra("titleBLA");
        //tenbang dat la tag
        String tag = intent.getStringExtra("tenbangBLA");
        String idtheBLA = intent.getStringExtra("idtheBLA");
        String idbangBLA = intent.getStringExtra("idbangBLA");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //get ten list
        String tenlist = intent.getStringExtra("tenlistBLA");

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
                        manager.insertCongViec(tencv1, title, tag, trangthaibandau, idbangBLA, idtheBLA);
                        getCV(title, idbangBLA, idtheBLA);
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        getMota(Integer.parseInt(idtheBLA), idbangBLA);
        mota.setText(getmota);

        getCV(title, idbangBLA, idtheBLA);
        GetNgayHoanTHanh(idtheBLA);


        ngayhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                mon = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UsingCardScroll.this, UsingCardScroll.this, year, mon, day);
                datePickerDialog.show();
            }
        });

        //create swipe item
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(70);
                // set a icon
                deleteItem.setIcon(R.drawable.outline_delete_white_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listcv.setMenuCreator(creator);
        //swipe item listener
        listcv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        manager.deleteCV(arrCV.get(position).getIdCV());
                        getCV(title, String.valueOf(idbangBLA), idtheBLA);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_deepinside_tcbang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.moveCard:
                Toast.makeText(this, "Move Card", Toast.LENGTH_SHORT).show();
                moveCardDialog();
                return true;
            case R.id.deleteCard:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xóa thẻ");
                builder.setMessage("Bạn có muốn xóa thẻ không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        String tenlist = intent.getStringExtra("tenlistBLA");
                        String idthe = intent.getStringExtra("idtheBLA");
                        String idbang = intent.getStringExtra("idbangBLA");
                        cursorThe = manager.getCard(tenlist, idbang);
                        while (cursorThe.moveToNext()) {
                            int idThe = cursorThe.getInt(0);
                            if (String.valueOf(idThe).equals(idthe)) {
                                manager.deleteThe(idThe);
                                manager.deleteLich(String.valueOf(idThe));
                            }
                        }
                        startActivity(new Intent(UsingCardScroll.this,saukhidangnhap.class));
                        finish();
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearF = i;
        monF = i1 + 1;
        dayF = i2;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        timemili =  c.getTimeInMillis();
        Log.d("TimeMili"," "+timemili);
        TimePickerDialog timePickerDialog = new TimePickerDialog(UsingCardScroll.this, UsingCardScroll.this,
                hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourF = i;
        minuteF = i1;
        Intent intent = getIntent();
        String idthe = intent.getStringExtra("idtheBLA");
        String idbang = intent.getStringExtra("idbangBLA");
        Calendar endTime = Calendar.getInstance();
        endTime.set(yearF, monF, dayF, hourF, minuteF);
        endMillis = endTime.getTimeInMillis();
        String monFF="";
        if(monF<10){
             monFF = "0"+String.valueOf(monF);
        }else{
             monFF = String.valueOf(monF);
        }

        if(ngayhoanthanh.getText().equals("Date...")) {
            manager.insertLich(String.valueOf(minuteF), String.valueOf(hourF), String.valueOf(dayF), monFF, String.valueOf(yearF), idthe, String.valueOf(endMillis),idbang);
            GetNgayHoanTHanh(idthe);
        }else{
            manager.updateLich(String.valueOf(minuteF), String.valueOf(hourF), String.valueOf(dayF), monFF, String.valueOf(yearF), idthe, String.valueOf(endMillis),idbang);
            GetNgayHoanTHanh(idthe);
        }
        GetNgayBaoThuc(idthe);

    }


    public void GetNgayHoanTHanh(String idthe){
        cursor4 = manager.getNgayHoanThanh(idthe);

        if(cursor4.moveToLast()) {
            cursor4.moveToLast();
            String phut = cursor4.getString(1);
            String gio = cursor4.getString(2);
            String ngay = cursor4.getString(3);
            String thang = cursor4.getString(4);
            String nam = cursor4.getString(5);
            ngayhoanthanh.setText("Het han vao " + ngay + " thang " + thang + " luc " + gio + ":" + phut);
        }else {
            Log.e("Bangnull","cursor 4 == null");
        }
    }
    //pendinhintent khác voi intent thuong. intent thuong chi ton tai khi no khoi tao va ket thuc nhiem vu con pendinh intent ton tai suot khi ban thoat ung dung

    public void GetNgayBaoThuc(String idthe){
        // cho phép truy cập vào hệ thống báo động của máy

        cursor5 = manager.getNgayHoanThanh(idthe);
        if(cursor4!=null) {
            cursor4.moveToLast();
            String idLich = cursor4.getString(0);
            String phut = cursor4.getString(1);
            String gio = cursor4.getString(2);
            String ngay = cursor4.getString(3);
            String thang = cursor4.getString(4);
            String nam = cursor4.getString(5);
            String milintime = cursor4.getString(7);
            long milinX=0;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.clear();
            c.set(Integer.parseInt(nam), Integer.parseInt(thang)-1, Integer.parseInt(ngay), Integer.parseInt(gio), Integer.parseInt(phut));

            milinX=c.getTimeInMillis();
            AlarmManager alarmManager =  (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intentAlarm = new Intent(UsingCardScroll.this, AlarmReceiver.class);
            intentAlarm.putExtra("tenBang",tenbang);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    UsingCardScroll.this,
                    Integer.parseInt(idLich),
                    intentAlarm,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            alarmManager.set(AlarmManager.RTC_WAKEUP, milinX,pendingIntent);

        }else {
            Log.e("Bangnull","cursor 4 == null");
        }
    }


    //hiển thị cv
    public void getCV(String title, String idbang, String idthe) {
        cursor = manager.getCV(title, idbang, idthe);
        if (congViecAdapter != null) {
            arrCV.clear();
            listcv.setAdapter(congViecAdapter);
            while (cursor.moveToNext()) {
                String tencv = cursor.getString(1);
                int id = cursor.getInt(0);
                boolean trangthaicv = Boolean.parseBoolean(cursor.getString(4));
                arrCV.add(new CongViec(id, tencv, trangthaicv));
            }
        } else if (congViecAdapter == null) {
            while (cursor.moveToNext()) {
                String tencv = cursor.getString(1);
                int id = cursor.getInt(0);
                boolean trangthaicv = Boolean.parseBoolean(cursor.getString(4));
                arrCV.add(new CongViec(id, tencv, trangthaicv));
            }
        }
        congViecAdapter.notifyDataSetChanged();
        listcv.setAdapter(congViecAdapter);
    }

    //hiển thị mô tả theo id the va id bang
    public void getMota(int idthe, String idbang) {
        cursor1 = manager.getMota(idthe, idbang);
        while (cursor1.moveToNext()) {
            getmota = cursor1.getString(2);
        }
    }

    // chuyển thể
    public void moveCardDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_move_card);
        TextView movecard = dialog.findViewById(R.id.action_move_card);
        Spinner spincardbang = dialog.findViewById(R.id.spin_move_card_bang);

        //spin tên list : tdo,ding,dne
        Spinner spincardlist = dialog.findViewById(R.id.spin_move_card_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTenlist);
        spincardlist.setAdapter(adapter);
        spincardlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        tenMoveList = "ToDo";
                        break;
                    case 1:
                        tenMoveList = "Doing";
                        break;
                    case 2:
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
                idMoveBang = cursor.getInt(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //onclick move thẻ
        movecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String title = intent.getStringExtra("titleBLA");
                //tenbang dat la tag
                String tag = intent.getStringExtra("tenbangBLA");
                String tenlist = intent.getStringExtra("tenlistBLA");
                String idthe = intent.getStringExtra("idtheBLA");
                String idbang = intent.getStringExtra("idbangBLA");
                cursor2 = manager.getMota(Integer.parseInt(idthe), idbang);
                while (cursor2.moveToNext()) {
                    getmota = cursor2.getString(2);
                }
                // thêm thẻ vào bảng di chuyểnn đến tenthe , mota , tenlist, tenbang , id bang cần chuyển tới
                manager.inserThe(title, getmota , tenMoveList, tenMoveBang, String.valueOf(idMoveBang));
                cursorTheMove2 = manager.getCard(tenMoveList, String.valueOf(idMoveBang));
                cursorTheMove2.moveToLast();
                int idTheMoveMove = cursorTheMove2.getInt(0);

                //xóa thẻ cần di chuyển
                cursorThe = manager.getCard(tenlist, idbang);
                while (cursorThe.moveToNext()) {
                    int idThe = cursorThe.getInt(0);
                    String tenThe = cursorThe.getString(1);
                    if (String.valueOf(idThe).equals(idthe)) {
                        manager.deleteThe(idThe);
                        manager.deleteLich(String.valueOf(idThe));
                    }
                }

                // move noi dung, chuyển công việc sang và xóa công việc được chuyển sang ở thẻ cũ
                cursor3 = manager.getCV(title, idbang, idthe);
                while (cursor3.moveToNext()) {
                    String tencv = cursor3.getString(1);
                    int id = cursor3.getInt(0);
                    int trangthaibd = cursor3.getInt(4);
                    //lấy id thẻ vừa được chuyển sang
                    manager.insertCongViec(tencv, title, tenMoveBang, String.valueOf(trangthaibd), String.valueOf(idMoveBang), String.valueOf(idTheMoveMove));
                    // xóa công việc  ở thẻ cũ
                    manager.deleteCV(id);
                }

                Toast.makeText(view.getContext(), "Move thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                startActivity(new Intent(UsingCardScroll.this,saukhidangnhap.class));
                finish();
            }
        });
        dialog.show();
    }

}


