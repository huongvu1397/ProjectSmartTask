package com.example.a84974.projectsmarttask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a84974.projectsmarttask.fragment_bang.FragmentToDo;

public class DatabaseManager {
    public final String DB_NAME = "quanlybang";
    public final String TB_Bang = "bang";
    public final String TB_Card = "card";
    public final String TB_CongViec = "congviec";
    public final int DB_VERSION = 8;
    private SQLiteDatabase database;

    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String taoBang = "CREATE TABLE IF NOT EXISTS bang(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenBang TEXT,QuyenXem TEXT,MauNen TEXT)";
            String taoThe = "CREATE TABLE IF NOT EXISTS card(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenThe TEXT,MoTa TEXT,TagList TEXT,TenBang TEXT)";
            String taoCongViec = "CREATE TABLE IF NOT EXISTS congviec(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenCV TEXT,Title TEXT,TenBang TEXT)";
            sqLiteDatabase.execSQL(taoBang);
            sqLiteDatabase.execSQL(taoThe);
            sqLiteDatabase.execSQL(taoCongViec);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS card");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS congviec");
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
    //thêm cv
    public void insertCongViec(String tencv, String title, String tenbang) {
        ContentValues values = new ContentValues();
        values.put("TenCV", tencv);
        values.put("Title", title);
        values.put("TenBang", tenbang);
        database.insert(TB_CongViec, null, values);
    }

    //them the
    public void inserThe(String tenthe, String mota, String taglist,String tenbang) {
        ContentValues values = new ContentValues();
        values.put("TenThe", tenthe);
        values.put("MoTa", mota);
        values.put("TagList", taglist);
        values.put("TenBang",tenbang);
        database.insert(TB_Card, null, values);
    }

    //delete
    public void deleteBang(String tenbang) {
        database.delete(TB_Bang, "TenBang LIKE " +"'"+tenbang+"'" , null);
        database.delete(TB_Card,"TenBang LIKE " +"'"+tenbang+"'" , null);
        database.delete(TB_CongViec,"TenBang LIKE " +"'"+tenbang+"'" , null);

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
    public Cursor getCard(String tenlist,String tenbang) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = "TagList LIKE"+ "'"+tenlist+"'"+" AND TenBang LIKE"+"'"+tenbang+"'"; //CustomerName LIKE 'a%'
        return database.query(TB_Card,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    //getCV
    public Cursor getCV(String title,String tenbang) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = "Title LIKE"+ "'"+title+"'"+" AND TenBang LIKE"+"'"+tenbang+"'"; //CustomerName LIKE 'a%'
        return database.query(TB_CongViec,
                null,
                selection,
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
