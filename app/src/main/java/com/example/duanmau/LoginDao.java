package com.example.duanmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    SQLiteDatabase db;
    Database helper;
    public LoginDao(Context c) {
        helper = new Database(c);
        db = helper.getReadableDatabase();
    }

    public void insert(Login login) {
        ContentValues values = new ContentValues();
        values.put("username", login.username);
        values.put("name", login.name);
        values.put("pass", login.pass);
        values.put("repass", login.repass);
        values.put("phanquyen", login.phanquyen);
        values.put("anh", login.anh);
        db.insert("Login", null, values);
    }

    public boolean insert(String username, String name, String pass, String repass, String phanquyen,byte[] anh) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("pass", pass);
        values.put("repass", repass);
        values.put("phanquyen", phanquyen);
        values.put("anh",anh);
        long row = db.insert("Login", null, values);
        return row > 0;
    }
    public boolean checktt(String username, String pass,String phanquyen) {
        SQLiteDatabase db =helper.getReadableDatabase();
        String str = "select * FROM Login"+" WHERE username= '"+username+"' AND pass= '"+pass+"'AND phanquyen= '"+phanquyen+"'";
        Cursor cs= db.rawQuery(str,null);
        cs.moveToFirst();
        if(cs.getCount()!=0){
            return true;
        }
        return false;
    }
    public ArrayList<Login> xemacc() {
        ArrayList<Login> ds = new ArrayList<Login>();
        Cursor c = db.rawQuery("select * from Login", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int id = c.getInt(0);
            String username = c.getString(1);
            String name = c.getString(2);
            String pass = c.getString(3);
            String repass = c.getString(4);
            String phanquyen = c.getString(5);
            byte[] anh = c.getBlob(6);
            Login lg = new Login(id, username, name, pass, repass, phanquyen,anh);
            ds.add(lg);
            c.moveToNext(); //dong tiep theo
        }
            return ds;
    }

    public ArrayList<Login> getAll(String phanquyen1){
        ArrayList<Login> ds = new ArrayList<Login>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM Login WHERE phanquyen='"+phanquyen1+"'";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int id=cs.getInt(0);
            String username=cs.getString(1);
            String name=cs.getString(2);
            String phanquyen = cs.getString(5);
            byte[] anh = cs.getBlob(6);
            Login pl = new Login(id,username,name,phanquyen,anh);
            ds.add(pl);
            cs.moveToNext(); //dong tiep theo
        }
        cs.close();
        db.close();
        return ds;
    }
    public ArrayList<Login> getAll(){
        ArrayList<Login> ds = new ArrayList<Login>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM Login";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int id=cs.getInt(0);
            String name=cs.getString(1);
            String phanquyen = cs.getString(5);
            byte[] anh = cs.getBlob(6);
            Login pl = new Login(id,name,phanquyen,anh);
            ds.add(pl);
            cs.moveToNext(); //dong tiep theo
        }
        cs.close();
        db.close();
        return ds;
    }
    public boolean update(Login login) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", login.getName());
        values.put("pass", login.getPass());
        values.put("repass", login.getRepass());
        values.put("phanquyen", login.getPhanquyen());
        values.put("anh", login.getAnh());
        int row =db.update("Login",values,"id=?",new String[]{login.getId()+""});
        return (row>0);
    }
    public boolean updatecn(Login login) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", login.getName());
        values.put("pass", login.getPass());
        values.put("repass", login.getRepass());
        int row =db.update("Login",values,"username=?",new String[]{login.getUsername()+""});
        return (row>0);
    }
    public Login getByUser(String user) {
        List<Login> ds = new ArrayList<Login>();
        SQLiteDatabase db =helper.getReadableDatabase();
        String sql="SELECT * FROM Login WHERE username="+user;
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst(); //ddaauf tieen
        while (cs.isAfterLast()==false){
            int id = cs.getInt(0);
            String username = cs.getString(1);
            String name = cs.getString(2);
            String pass = cs.getString(3);
            String repass = cs.getString(4);
            String phanquyen = cs.getString(5);
            byte[] anh = cs.getBlob(6);
            Login pl = new Login(id,username, name, pass,repass,phanquyen,anh);
            cs.moveToNext(); //dong tiep theo
            ds.add(pl);
        }
        cs.close();
        db.close();
        return null;
    }
    public boolean delete(int id){
        SQLiteDatabase db =helper.getReadableDatabase();
        int row = db.delete("Login","id=?",new String[]{id+""});
        return (row>0);
    }
}