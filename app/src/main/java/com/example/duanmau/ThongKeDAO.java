package com.example.duanmau;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.IntRange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {
    SimpleDateFormat spfm = new SimpleDateFormat("dd-MM-yyyy");
    SQLiteDatabase db;
    Database helper;
    Context c;
    public ThongKeDAO(Context c) {
        helper = new Database(c);
        db = helper.getWritableDatabase();
    }
    public int getAll(String tungay,String denngay) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT SUM(GiaTien) AS TongTien FROM PhieuMuon WHERE NgayThue BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cs = db.rawQuery(sql, new String[]{tungay,denngay});
        while (cs.moveToNext()) {
           try{
               list.add(Integer.parseInt(cs.getString(cs.getColumnIndex("TongTien"))));
           }catch (Exception e){
               list.add(0);
           }

        }
        return list.get(0);
    }
    public int getAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT SUM(GiaTien) AS TongTien FROM PhieuMuon";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cs = db.rawQuery(sql, null);
        while (cs.moveToNext()) {
            try{
                list.add(Integer.parseInt(cs.getString(cs.getColumnIndex("TongTien"))));
            }catch (Exception e){
                list.add(0);
            }

        }
        return list.get(0);
    }

    public List<Top> getTOP(int so){
        String sql = "SELECT MaSach , count(MaSach) as soLuong FROM PhieuMuon GROUP BY MaSach ORDER BY soLuong DESC LIMIT "+so;
        List<Top> list = new ArrayList<Top>();
        Cursor cs = db.rawQuery(sql, null);
        while (cs.moveToNext()) {
            Top top=new Top();
            top.maSach = Integer.parseInt(cs.getString(cs.getColumnIndex("MaSach")));
            top.soLuong = Integer.parseInt(cs.getString(cs.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }
}
