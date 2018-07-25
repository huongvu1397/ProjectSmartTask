package com.example.a84974.projectsmarttask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    public final String DB_NAME = "quanlybang";
    public final String TB_Bang = "bang";
    public final String TB_Card = "card";
    public final String TB_List = "list";
    public final int DB_VERSION = 2;
    private SQLiteDatabase database;

    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String taoBang = "CREATE TABLE IF NOT EXISTS bang(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenBang TEXT,QuyenXem TEXT,MauNen TEXT)";
            String taoThe = "CREATE TABLE IF NOT EXISTS card(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenThe TEXT,MoTa TEXT)";
            sqLiteDatabase.execSQL(taoBang);
            sqLiteDatabase.execSQL(taoThe);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS card");
            onCreate(sqLiteDatabase);
        }

    }
    //thêm bảng
    public void inserBang(String tenbang, String quyenxem, String maunen) {
        ContentValues values = new ContentValues();
        values.put("TenBang", tenbang);
        values.put("QuyenXem", quyenxem);
        values.put("MauNen", maunen);
        database.insert(TB_Bang, null, values);
    }
    //them the
    public void inserThe(String tenthe, String mota) {
        ContentValues values = new ContentValues();
        values.put("TenThe", tenthe);
        values.put("MoTa", mota);
        database.insert(TB_Card, null, values);
    }
    //xoa bang
    public void deleteBang(){

    }
    //
    public Cursor getBang() {
        return database.query(TB_Bang,
                null,
                null,
                null,
                null,
                null,
                null);
    }
    //getThe
    public Cursor getCard() {
        //String[] columns={"id","TenThe","MoTa"};

        return database.query(TB_Card,
                null,
                null,
                null,
                null,
                null,
                null);
    }
    public DatabaseManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }

}
