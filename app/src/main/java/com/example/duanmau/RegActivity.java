package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegActivity extends AppCompatActivity {
TextView tao,daco;
EditText nhapuser,hoten,nhappass,nhaprepass;
ImageView backve,avtreg;
Button regacc;
    ArrayList<Login> logins;
    LoginDao loginDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        tao=findViewById(R.id.tao);
        nhappass=findViewById(R.id.nhappass);
        nhapuser=findViewById(R.id.nhapuser);
        hoten=findViewById(R.id.hoten);
        nhaprepass=findViewById(R.id.nhaprepass);
        daco=findViewById(R.id.daco);
        backve=findViewById(R.id.backve);
        avtreg=findViewById(R.id.avtreg);
        regacc=findViewById(R.id.regacc);
        loginDao = new LoginDao(RegActivity.this);
        //xử lý click
        daco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= nhapuser.getText().toString();
                String name=hoten.getText().toString();
                String pass = nhappass.getText().toString();
                String repass= nhaprepass.getText().toString();
                String phanquyen="Thành Viên";
                int checkpass = pass.compareTo(repass);
                if (TextUtils.isEmpty(user)) {
                    nhapuser.setError("Chưa nhập username");
                }else if(TextUtils.isEmpty(name)){
                    hoten.setError("Chưa nhập Họ Tên");
                }else if(TextUtils.isEmpty(pass)){
                    nhappass.setError("Chưa nhập password");
                }else if(TextUtils.isEmpty(repass)){
                    nhaprepass.setError("Chưa nhập repassword");
                }else {
                    if (loginDao.insert(user, name, pass, repass, phanquyen,null) == true) {
                        if(checkpass==0) {
                            Intent i = new Intent(RegActivity.this, LoginActivity.class);
                            startActivity(i);
                            Toast.makeText(RegActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegActivity.this, "Mật Khẩu Không Trùng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}