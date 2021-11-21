package com.example.duanmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonDAO {
    SimpleDateFormat spfm = new SimpleDateFormat("dd-MM-yyyy");
    SQLiteDatabase db;
    Database helper;
    public PhieuMuonDAO(Context c) {
        helper = new Database(c);
        db = helper.getReadableDatabase();
    }
    public boolean insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("NgayThue", spfm.format(phieuMuon.getNgayThue()));
        values.put("GiaTien", phieuMuon.getGiaTien());
        values.put("NgayTra", spfm.format(phieuMuon.getNgayTra()));
        values.put("TenTV", phieuMuon.getTenTV());
        values.put("TrangThai", phieuMuon.getTrangThai());
        values.put("MaSach", phieuMuon.getMaSach());
        long row = db.insert("PhieuMuon", null, values);
        return row > 0;
    }
    public boolean insert(Date NgayThue,int GiaTien,Date NgayTra,String TenTV,String TrangThai,int MaSach) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("NgayThue", spfm.format(NgayThue));
        values.put("GiaTien", GiaTien);
        values.put("NgayTra", spfm.format(NgayTra));
        values.put("TenTV", TenTV);
        values.put("TrangThai", TrangThai);
        values.put("MaSach", MaSach);
        long row = db.insert("PhieuMuon", null, values);
        return row > 0;
    }
    public boolean addbytv(Date NgayThue,int GiaTien,Date NgayTra,String TrangThai,int MaSach,int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("NgayThue", spfm.format(NgayThue));
        values.put("GiaTien", GiaTien);
        values.put("NgayTra", spfm.format(NgayTra));
        values.put("TrangThai", TrangThai);
        values.put("MaSach", MaSach);
        values.put("TenTV", id);
        long row = db.insert("PhieuMuon", null, values);
        return row > 0;
    }
    public ArrayList<PhieuMuon> xemphieumuon() {
        ArrayList<PhieuMuon> ds = new ArrayList<PhieuMuon>();
        Cursor c = db.rawQuery("select * from PhieuMuon", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int MaPM = c.getInt(0);
            Date NgayThue = null;
            try {
                NgayThue =spfm.parse(c.getString(1));
            } catch (Exception e) {
            }
            int GiaTien = c.getInt(2);
            Date NgayTra = null;
            try {
                NgayTra =spfm.parse(c.getString(3));
            } catch (Exception e) {
            }
            String TenTV = c.getString(4);
            String TrangThai = c.getString(5);
            int MaSach = c.getInt(6);
            PhieuMuon lg = new PhieuMuon(MaPM,NgayThue,GiaTien,NgayTra,TenTV,TrangThai,MaSach);
            ds.add(lg);
            c.moveToNext(); //dong tiep theo
        }
        return ds;
    }
    public ArrayList<PhieuMuon> getAll() {
        ArrayList<PhieuMuon> ds = new ArrayList<PhieuMuon>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM PhieuMuon";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast() == false) {
            int MaPM = cs.getInt(0);
            Date NgayThue = null;
            try {
                NgayThue = spfm.parse(cs.getString(1));
            } catch (Exception e) {
            }
            int GiaTien = cs.getInt(2);
            Date NgayTra = null;
            try {
                NgayTra = spfm.parse(cs.getString(3));
            } catch (Exception e) {
            }
            String TenTV = cs.getString(4);
            String TrangThai = cs.getString(5);
            int MaSach = cs.getInt(6);
            PhieuMuon lg = new PhieuMuon(MaPM, NgayThue, GiaTien, NgayTra, TenTV, TrangThai, MaSach);
            ds.add(lg);
            cs.moveToNext(); //dong tiep theo
        }
            cs.close();
            db.close();
            return ds;
        }
    public boolean update(PhieuMuon phieuMuon) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("NgayThue", spfm.format(phieuMuon.getNgayThue()));
        values.put("GiaTien", phieuMuon.getGiaTien());
        values.put("NgayTra", spfm.format(phieuMuon.getNgayTra()));
        values.put("TenTV", phieuMuon.getTenTV());
        values.put("TrangThai", phieuMuon.getTrangThai());
        values.put("MaSach", phieuMuon.getMaSach());
        int row =db.update("PhieuMuon",values,"MaPM=?",new String[]{phieuMuon.getMaPM()+""});
        return (row>0);
    }
    public boolean delete(int MaPM){
        SQLiteDatabase db =helper.getReadableDatabase();
        int row = db.delete("PhieuMuon","MaPM=?",new String[]{MaPM+""});
        return (row>0);
    }
}
