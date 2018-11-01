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
    public final String TB_Lich = "lich";
    public final int DB_VERSION = 16;
    public final String DB_PATH = "/data/data/com.example.a84974.projectsmarttask/databases/"+DB_NAME;
    private SQLiteDatabase database;

    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String taoBang = "CREATE TABLE IF NOT EXISTS bang(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenBang TEXT,QuyenXem TEXT,MauNen TEXT)";
            String taoThe = "CREATE TABLE IF NOT EXISTS card(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenThe TEXT,MoTa TEXT,TenList TEXT,TenBang TEXT,IdBang TEXT)";
            String taoCongViec = "CREATE TABLE IF NOT EXISTS congviec(_id INTEGER PRIMARY KEY AUTOINCREMENT,TenCV TEXT,Title TEXT,TenBang TEXT,CheckBox TEXT,IdBang TEXT,IdThe TEXT)";
            String taoLich = "CREATE TABLE IF NOT EXISTS lich(_id INTEGER PRIMARY KEY AUTOINCREMENT,Phut TEXT,Gio TEXT,Ngay TEXT,Thang TEXT,Nam TEXT,IdThe TEXT,GetTimeMilin TEXT,IdBang Text)";
            sqLiteDatabase.execSQL(taoBang);
            sqLiteDatabase.execSQL(taoThe);
            sqLiteDatabase.execSQL(taoCongViec);
            sqLiteDatabase.execSQL(taoLich);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS card");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS congviec");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS lich");
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

    public void insertLich(String phut, String gio, String ngay, String thang, String nam, String idthe, String timemilin, String idbang) {
        ContentValues values = new ContentValues();
        values.put("Phut", phut);
        values.put("Gio", gio);
        values.put("Ngay", ngay);
        values.put("Thang", thang);
        values.put("Nam", nam);
        values.put("IdThe", idthe);
        values.put("GetTimeMilin", timemilin);
        values.put("IdBang", idbang);
        database.insert(TB_Lich, null, values);
    }

    public void updateLich(String phut, String gio, String ngay, String thang, String nam, String idthe, String timemillin, String idbang) {
        ContentValues values = new ContentValues();
        values.put("Phut", phut);
        values.put("Gio", gio);
        values.put("Ngay", ngay);
        values.put("Thang", thang);
        values.put("Nam", nam);
        values.put("IdThe", idthe);
        values.put("GetTimeMilin", timemillin);
        values.put("IdBang", idbang);
        database.update(TB_Lich, values, "IdThe LIKE " + "'" + idthe + "'", null);
    }

    public void deleteLich(String idthe) {
        database.delete(TB_Lich, "IdThe LIKE " + "'" + idthe + "'", null);
    }

    public void deleteLichbyBang(String idbang) {
        database.delete(TB_Lich, "IdBang LIKE " + "'" + idbang + "'", null);
    }


    //thêm cv tên cv tên thẻ tên bảng checkbox id bảng id thẻ
    public void insertCongViec(String tencv, String title, String tenbang, String checkbox, String idbang, String idthe) {
        ContentValues values = new ContentValues();
        values.put("TenCV", tencv);
        values.put("Title", title);
        values.put("TenBang", tenbang);
        values.put("CheckBox", checkbox);
        values.put("IdBang", idbang);
        values.put("IdThe", idthe);
        database.insert(TB_CongViec, null, values);
    }

    //them the
    public void inserThe(String tenthe, String mota, String tenlist, String tenbang, String idbang) {
        ContentValues values = new ContentValues();
        values.put("TenThe", tenthe);
        values.put("MoTa", mota);
        values.put("TenList", tenlist);
        values.put("TenBang", tenbang);
        values.put("IdBang", idbang);
        database.insert(TB_Card, null, values);
    }

    //delete
    public void deleteBang(int id) {
        database.delete(TB_Bang, " _id = " + id, null);
    }

    public void deleteCV(int id) {
        database.delete(TB_CongViec, "_id = " + id, null);
    }

    public void deleteThe(int id) {
        database.delete(TB_Card, "_id = " + id, null);
    }

    public void deleteCVbyThe(String idThe) {
        database.delete(TB_CongViec, "IdThe LIKE " + "'" + idThe + "'", null);
    }

    public void deleteCVbyBang(String idbang) {
        database.delete(TB_CongViec, "IdBang LIKE " + "'" + idbang + "'", null);
    }

    public void deleteThebyBang(String idbang) {
        database.delete(TB_Card, "IdBang LIKE " + "'" + idbang + "'", null);

    }

    //update
    public void updateCb(int id, boolean bienCheck) {
        ContentValues values = new ContentValues();
        values.put("CheckBox", bienCheck);
        database.update(TB_CongViec, values, "_id = " + id, null);
    }

    //load data
    public Cursor getDataCheckBox(int id) {
        String selection = "_id = " + id;
        String[] columns = {"TenCV", "CheckBox"};
        return database.query(TB_CongViec, columns, selection, null, null, null, null);
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

    //lay ngay hoan thanh
    public Cursor getNgayHoanThanh(String idthe) {
        String selection = "IdThe LIKE " + "'" + idthe + "'";
        return database.query(TB_Lich,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    //lay full ngay hoan thanh
    public Cursor getDate() {
        return database.query(TB_Lich,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getDateLich(String ngay,String thang,String nam){
        String selection = "Ngay LIKE " + "'" + ngay + "'" + " AND Thang LIKE " + "'" + thang + "'" + " AND Nam LIKE " + "'" + nam + "'";
        return database.query(TB_Lich,null,selection,null,null,null,null);
    }

    //lấy thẻ theo id bảng và theo tên list của bảng : tdo ding dne
    public Cursor getCard(String tenlist, String idbang) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = "TenList LIKE " + "'" + tenlist + "'" + " AND IdBang LIKE " + "'" + idbang + "'";
        return database.query(TB_Card,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    public Cursor getCardAll() {
        //String[] columns={"id","TenThe","MoTa"};
        //String selection = "TenList LIKE " + "'" + tenlist + "'" + " AND IdBang LIKE " + "'" + idbang + "'";
        return database.query(TB_Card,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    //getMota
    public Cursor getMota(int idthe, String idbang) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = "_id = " + "'" + idthe + "'" + " AND IdBang LIKE" + "'" + idbang + "'";
        return database.query(TB_Card,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    public Cursor getCardByID(String id){
        String selection = "_id = " + "'" + id + "'";
        return database.query(TB_Card,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    //getCV theo tên thẻ và id bảng
    public Cursor getCV(String title, String idbang, String idthe) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = "Title LIKE " + "'" + title + "'" + " AND IdBang LIKE " + "'" + idbang + "'" + " AND IdThe LIKE " + "'" + idthe + "'";
        return database.query(TB_CongViec,
                null,
                selection,
                null,
                null,
                null,
                null);
    }

    public Cursor getCVID(String idthe) {
        //String[] columns={"id","TenThe","MoTa"};
        String selection = " IdThe LIKE " + "'" + idthe + "'";
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
