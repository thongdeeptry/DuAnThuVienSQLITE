package com.example.duanmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    SQLiteDatabase db;
    Database helper;
    public ThanhVienDao(Context c) {
        helper = new Database(c);
        db = helper.getReadableDatabase();
    }

    public void insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("username", thanhVien.username);
        values.put("name", thanhVien.name);
        values.put("pass", thanhVien.pass);
        values.put("diachi", thanhVien.diachi);
        values.put("sdt", thanhVien.sdt);
        values.put("phanquyen", thanhVien.phanquyen);
        values.put("anh", thanhVien.anh);
        db.insert("ThanhVien", null, values);
    }

    public boolean insert(String username, String name, String pass, String diachi, int sdt,String phanquyen,byte[] anh) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("pass", pass);
        values.put("diachi", diachi);
        values.put("sdt", sdt);
        values.put("phanquyen", phanquyen);
        values.put("anh", anh);
        long row = db.insert("ThanhVien", null, values);
        return row > 0;
    }
    public boolean checktt(String username, String pass,String phanquyen) {
        SQLiteDatabase db =helper.getReadableDatabase();
        String str = "select * FROM ThanhVien"+" WHERE username= '"+username+"' AND pass= '"+pass+"'AND phanquyen= '"+phanquyen+"'";
        Cursor cs= db.rawQuery(str,null);
        cs.moveToFirst();
        if(cs.getCount()!=0){
            return true;
        }
        return false;
    }
    public ArrayList<ThanhVien> xemacc() {
        ArrayList<ThanhVien> ds = new ArrayList<ThanhVien>();
        Cursor c = db.rawQuery("select * from ThanhVien", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int id = c.getInt(0);
            String username = c.getString(1);
            String name = c.getString(2);
            String pass = c.getString(3);
            String diachi = c.getString(4);
            int sdt = c.getInt(5);
            String phanquyen = c.getString(6);
            byte[] anh = c.getBlob(7);
            ThanhVien lg = new ThanhVien(id, username, name, pass, diachi,sdt, phanquyen,anh);
            ds.add(lg);
            c.moveToNext(); //dong tiep theo
        }
            return ds;
    }

    public ArrayList<ThanhVien> getAll(String phanquyen1){
        ArrayList<ThanhVien> ds = new ArrayList<ThanhVien>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM ThanhVien WHERE phanquyen='"+phanquyen1+"'";
        Cursor c=db.rawQuery(sql,null);
        c.moveToFirst(); //ddaauf tieen
        while (c.isAfterLast()==false){
            int id = c.getInt(0);
            String username = c.getString(1);
            String name = c.getString(2);
            String pass = c.getString(3);
            String diachi = c.getString(4);
            int sdt = c.getInt(5);
            String phanquyen = c.getString(6);
            byte[] anh = c.getBlob(7);
            ThanhVien pl = new ThanhVien(id,username,name,pass,diachi,sdt,phanquyen,anh);
            ds.add(pl);
            c.moveToNext(); //dong tiep theo
        }
        c.close();
        db.close();
        return ds;
    }
    public ArrayList<ThanhVien> getAll(){
        ArrayList<ThanhVien> ds = new ArrayList<ThanhVien>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM ThanhVien";
        Cursor c=db.rawQuery(sql,null);
        c.moveToFirst(); //ddaauf tieen
        while (c.isAfterLast()==false){
            int id = c.getInt(0);
            String username = c.getString(1);
            String name = c.getString(2);
            String pass = c.getString(3);
            String diachi = c.getString(4);
            int sdt = c.getInt(5);
            String phanquyen = c.getString(6);
            byte[] anh = c.getBlob(7);
            ThanhVien pl = new ThanhVien(id,username,name,pass,diachi,sdt,phanquyen,anh);
            ds.add(pl);
            c.moveToNext(); //dong tiep theo
        }
        c.close();
        db.close();
        return ds;
    }
    public ThanhVien getByUser(String user) {
        List<ThanhVien> ds = new ArrayList<ThanhVien>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM ThanhVien WHERE username="+user;
        Cursor c=db.rawQuery(sql,null);
        c.moveToFirst(); //ddaauf tieen
        while (c.isAfterLast()==false){
            int id = c.getInt(0);
            String username = c.getString(1);
            String name = c.getString(2);
            String pass = c.getString(3);
            String diachi = c.getString(4);
            int sdt = c.getInt(5);
            String phanquyen = c.getString(6);
            byte[] anh = c.getBlob(7);
            ThanhVien pl = new ThanhVien(id,username,name,pass,diachi,sdt,phanquyen,anh);
            c.moveToNext(); //dong tiep theo
            ds.add(pl);
        }
        c.close();
        db.close();
        return null;
    }
    public boolean updatecn(ThanhVien thanhVien) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", thanhVien.getName());
        values.put("pass", thanhVien.getPass());
        values.put("diachi", thanhVien.getDiachi());
        values.put("sdt", thanhVien.getSdt());
        int row =db.update("ThanhVien",values,"username=?",new String[]{thanhVien.getUsername()+""});
        return (row>0);
    }
    public boolean update(ThanhVien thanhVien) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", thanhVien.getName());
        values.put("pass", thanhVien.getPass());
        values.put("diachi", thanhVien.getDiachi());
        values.put("sdt", thanhVien.getSdt());
        values.put("anh", thanhVien.getAnh());
        int row =db.update("ThanhVien",values,"id=?",new String[]{thanhVien.getId()+""});
        return (row>0);
    }
    public ThanhVien getByUser1(String user) {
        List<ThanhVien> ds = new ArrayList<ThanhVien>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM ThanhVien WHERE username="+user;
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int id = cs.getInt(0);
            String username = cs.getString(1);
            String name = cs.getString(2);
            String pass = cs.getString(3);
            String diachi = cs.getString(4);
            int sdt = cs.getInt(5);
            String phanquyen = cs.getString(6);
            byte[] anh = cs.getBlob(7);
            ThanhVien pl = new ThanhVien(id,username,name,pass,diachi,sdt,phanquyen,anh);
            cs.moveToNext(); //dong tiep theo
            ds.add(pl);
        }
        cs.close();
        db.close();
        return null;
    }
    public boolean delete(int id){
        SQLiteDatabase db =helper.getReadableDatabase();
        int row = db.delete("ThanhVien","id=?",new String[]{id+""});
        return (row>0);
    }
}