package com.example.duanmau;

import java.util.Date;

public class PhieuMuon {
    int MaPM;
    Date NgayThue;
    int GiaTien;
    Date NgayTra;
    String TenTV;
    String TrangThai;
    int MaSach;
    int id;

    public PhieuMuon(int maPM, Date ngayThue, int giaTien, Date ngayTra, String tenTV, String trangThai, int maSach) {
        MaPM = maPM;
        NgayThue = ngayThue;
        GiaTien = giaTien;
        NgayTra = ngayTra;
        TenTV = tenTV;
        TrangThai = trangThai;
        MaSach = maSach;
    }
    public PhieuMuon(int maPM, Date ngayThue, int giaTien, Date ngayTra, String tenTV, String trangThai, int maSach,int id) {
        MaPM = maPM;
        NgayThue = ngayThue;
        GiaTien = giaTien;
        NgayTra = ngayTra;
        TenTV = tenTV;
        TrangThai = trangThai;
        MaSach = maSach;
        id=id;
    }

    public PhieuMuon( Date ngayThue, int giaTien, Date ngayTra, String tenTV, String trangThai, int maSach) {
        NgayThue = ngayThue;
        GiaTien = giaTien;
        NgayTra = ngayTra;
        TenTV = tenTV;
        TrangThai = trangThai;
        MaSach = maSach;
    }

    public PhieuMuon(int giaTien) {
        GiaTien = giaTien;
    }

    public int getMaPM() {
        return MaPM;
    }

    public void setMaPM(int maPM) {
        MaPM = maPM;
    }

    public Date getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(Date ngayThue) {
        NgayThue = ngayThue;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date ngayTra) {
        NgayTra = ngayTra;
    }

    public String getTenTV() {
        return TenTV;
    }


    public void setTenTV(String tenTV) {
        TenTV = tenTV;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
