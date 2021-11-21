package com.example.duanmau;

public class Login {
    int id;
    String username;
    String name;
    String pass;
    String repass;
    String phanquyen;
    byte[] anh;

    public Login(int id, String username, String name, String pass, String repass, String phanquyen, byte[] anh) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.pass = pass;
        this.repass = repass;
        this.phanquyen = phanquyen;
        this.anh = anh;
    }

    public Login(int id, String username, String name, String pass, String repass) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.pass = pass;
        this.repass = repass;
    }

    public Login(String username,String phanquyen) {
        this.username = username;
        this.phanquyen = phanquyen;
    }

    public Login(int id, String username, String name, String phanquyen,byte[] anh) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.anh = anh;
        this.phanquyen = phanquyen;
    }


    @Override
    public String toString() {
        return name;
    }
    public Login(String name, String pass, String repass) {
        this.name = name;
        this.pass = pass;
        this.repass=repass;
    }
    public Login(int id, String name,String phanquyen,byte[] anh) {
        this.id = id;
        this.name = name;
        this.phanquyen=phanquyen;
        this.anh = anh;
    }

    public Login(String username, String name, String pass, String repass) {
        this.username = username;
        this.name = name;
        this.pass = pass;
        this.repass=repass;
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

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
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
