package com.example.a84974.projectsmarttask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    public final String DB_NAME = "quanlybang";
    public final String TB_Bang = "bang";
    public final int DB_VERSION = 1;
    private SQLiteDatabase database;

    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String taoBang = "CREATE TABLE IF NOT EXISTS bang(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenBang TEXT,QuyenXem TEXT,MauNen TEXT)";
            sqLiteDatabase.execSQL(taoBang);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bang");
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
    public DatabaseManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }
}
