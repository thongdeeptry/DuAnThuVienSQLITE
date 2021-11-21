package com.example.duanmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database;
import com.example.duanmau.Login;

import java.util.ArrayList;

public class LoaiSachDAO {
    SQLiteDatabase db;
    Database helper;
    public LoaiSachDAO(Context c) {
        helper = new Database(c);
        db = helper.getReadableDatabase();
    }
    public void insert(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("MaLoai", loaiSach.MaLoai);
        values.put("TenLoai", loaiSach.TenLoai);
        db.insert("LoaiSach", null, values);
    }
    public boolean insert(String TenLoai) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", TenLoai);
        long row = db.insert("LoaiSach", null, values);
        return row > 0;
    }
    public ArrayList<LoaiSach> xemloai() {
        ArrayList<LoaiSach> ds = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery("select * from LoaiSach", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int MaLoai = c.getInt(0);
            String TenLoai = c.getString(1);
            LoaiSach lg = new LoaiSach(MaLoai, TenLoai);
            ds.add(lg);
            c.moveToNext(); //dong tiep theo
        }
        return ds;
    }
    public ArrayList<LoaiSach> getAll(){
        ArrayList<LoaiSach> ds = new ArrayList<LoaiSach>();
        SQLiteDatabase db =helper.getReadableDatabase();
            String sql="SELECT * FROM LoaiSach";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String ten=cs.getString(1);
            LoaiSach pl = new LoaiSach(ma,ten);
            ds.add(pl);
            cs.moveToNext(); //dong tiep theo
        }
        cs.close();
        db.close();
        return ds;
    }
    public LoaiSach getById(int maloai) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM LoaiSach WHERE MaLoai="+maloai;
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        int ma = cs.getInt(0);
        String ten = cs.getString(1);
        LoaiSach pl = new LoaiSach(ma, ten);
        cs.close();
        db.close();
        return pl;
    }
    public boolean update(LoaiSach loaiSach) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiSach.getTenLoai());
        int row =db.update("LoaiSach",values,"MaLoai=?",new String[]{loaiSach.getMaLoai()+""});
        return (row>0);
    }
    public boolean delete(int MaLoai){
        SQLiteDatabase db =helper.getReadableDatabase();
        int row = db.delete("LoaiSach","MaLoai=?",new String[]{MaLoai+""});
        return (row>0);
    }
}
