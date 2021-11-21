package com.example.duanmau;

public class Sach {
    int MaSach;
    String TenSach;
    int GiaTien;
    int MaLoai;

    public Sach(int maSach, String tenSach, int giaTien, int maLoai) {
        MaSach = maSach;
        TenSach = tenSach;
        GiaTien = giaTien;
        MaLoai = maLoai;
    }

    public Sach() {

    }

    @Override
    public String toString() {
        return TenSach;
    }
    public Sach(String tensach, int gia, int loai) {
        TenSach = tensach;
        GiaTien = gia;
        MaLoai = loai;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }
}
