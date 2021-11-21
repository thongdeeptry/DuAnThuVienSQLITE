package com.example.duanmau;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context c) {
        super(c, "qltvpm114", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Login" +
                "( " +
                "id integer primary key autoincrement," +
                "username text," +
                "name text," +
                "pass text," +
                "repass text," +
                "phanquyen text," +
                "anh BLOB" +
                ")";
        db.execSQL(sql);
        sql = "insert into Login values(1,'admin','Ngô Thành Thông','admin','admin','Admin',null)";
        db.execSQL(sql);
        sql = "insert into Login values(2,'thuthu','Ngô Thành Thông','thuthu','thuthu','Admin',null)";
        db.execSQL(sql);

        String sqlloaisach = "create table LoaiSach" +
                "( " +
                "MaLoai integer primary key autoincrement," +
                "TenLoai text" +
                ")";
        db.execSQL(sqlloaisach);
        sqlloaisach = "insert into LoaiSach values(1,'Sách Khoa Học')";
        db.execSQL(sqlloaisach);

        String sqlsach = "create table Sach" +
                "( " +
                "MaSach integer primary key autoincrement," +
                "TenSach text," +
                "GiaTien integer," +
                "MaLoai integer references LoaiSach(MaLoai)" +
                ")";
        db.execSQL(sqlsach);
        sqlsach = "insert into Sach values(1,'Lập Trình Android',9999,1)";
        db.execSQL(sqlsach);
        String sqlphieumuon = "create table PhieuMuon" +
                "( " +
                "MaPM integer primary key autoincrement," +
                "NgayThue date," +
                "GiaTien integer references Sach(GiaTien)," +
                "NgayTra date," +
                "TenTV text," +
                "TrangThai text," +
                "MaSach integer references Sach(MaSach)" +
                ")";
        db.execSQL(sqlphieumuon);


        String sqltv = "create table ThanhVien" +
                "( " +
                "id integer primary key autoincrement," +
                "username text," +
                "name text," +
                "pass text," +
                "diachi text," +
                "sdt integer,"+
                "phanquyen text,"+
                "anh BLOB" +
                ")";
        db.execSQL(sqltv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS ThuThu");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS LoaiSach");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS Sach");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
        this.onCreate(db);

    }

}
