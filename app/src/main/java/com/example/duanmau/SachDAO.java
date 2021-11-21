package com.example.duanmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class SachDAO {
    SQLiteDatabase db;
    Database helper;
    public SachDAO(Context c) {
        helper = new Database(c);
        db = helper.getReadableDatabase();
    }
    public boolean insert(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("GiaTien", sach.getGiaTien());
        values.put("MaLoai", sach.getMaLoai());
        long row = db.insert("Sach", null, values);
        return row > 0;
    }
    public boolean insert(String TenSach,int GiaTien,int MaLoai) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", TenSach);
        values.put("GiaTien",GiaTien);
        values.put("MaLoai",MaLoai);
        long row = db.insert("Sach", null, values);
        return row > 0;
    }
    public ArrayList<Sach> xemsach() {
        ArrayList<Sach> ds = new ArrayList<Sach>();
        Cursor c = db.rawQuery("select * from Sach", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int MaSach = c.getInt(0);
            String TenSach = c.getString(1);
            int GiaTien = c.getInt(2);
            int MaLoai = c.getInt(3);
            Sach lg = new Sach(MaSach, TenSach,GiaTien,MaLoai);
            ds.add(lg);
            c.moveToNext(); //dong tiep theo
        }
        return ds;
    }
    public ArrayList<Sach> getAll(){
        ArrayList<Sach> ds = new ArrayList<Sach>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM Sach";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int masach=cs.getInt(0);
            String tensach=cs.getString(1);
            int tien =cs.getInt(2);
            int maloai=cs.getInt(3);
            Sach sach = new Sach(masach,tensach,tien,maloai);
            ds.add(sach);
            cs.moveToNext(); //dong tiep theo
        }
        cs.close();
        db.close();
        return ds;
    }
    public Sach getById(int masach) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM Sach WHERE MaSach="+masach;
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        int ma = cs.getInt(0);
        String ten = cs.getString(1);
        int tien = cs.getInt(2);
        int maloai = cs.getInt(3);
        Sach pl = new Sach(ma, ten,tien,maloai);
        cs.close();
        db.close();
        return pl;
    }

    public Sach getByTS(String tensach) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM Sach WHERE TenSach="+tensach;
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        int ma = cs.getInt(0);
        String ten = cs.getString(1);
        int tien = cs.getInt(2);
        int maloai = cs.getInt(3);
        Sach pl = new Sach(ma, ten,tien,maloai);
        cs.close();
        db.close();
        return pl;
    }
    public ArrayList<Sach> getAll(String TenSach){
        ArrayList<Sach> ds = new ArrayList<Sach>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM Sach WHERE TenSach='"+TenSach+"'";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String ten=cs.getString(1);
            int tien = cs.getInt(2);
            int maloai = cs.getInt(3);
            Sach pl = new Sach(ma,ten,tien,maloai);
            ds.add(pl);
            cs.moveToNext(); //dong tiep theo
        }
        cs.close();
        db.close();
        return ds;
    }
    public boolean update(Sach sach) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("GiaTien",sach.getGiaTien());
        values.put("MaLoai",sach.getMaLoai());
        int row =db.update("Sach",values,"MaSach=?",new String[]{sach.getMaSach()+""});
        return (row>0);
    }
    public boolean delete(int MaSach){
        SQLiteDatabase db =helper.getReadableDatabase();
        int row = db.delete("Sach","MaSach=?",new String[]{MaSach+""});
        return (row>0);
    }
}
