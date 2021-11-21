package com.example.duanmau;

public class ThanhVien {
    int id;
    String username;
    String name;
    String pass;
    String diachi;
    int sdt;
    String phanquyen;
    byte[] anh;


    public ThanhVien(int id, String username, String name, String pass, String diachi, int sdt, String phanquyen, byte[] anh) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.pass = pass;
        this.diachi = diachi;
        this.sdt = sdt;
        this.phanquyen = phanquyen;
        this.anh = anh;
    }

    public ThanhVien(String username, String name, String pass, String diachi, int sdt) {
        this.username = username;
        this.name = name;
        this.pass = pass;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(String phanquyen) {
        this.phanquyen = phanquyen;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
