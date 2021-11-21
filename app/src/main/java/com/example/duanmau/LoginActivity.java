package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView dangnhap,reg;
    ImageView back;
    EditText user,pass,repass;
    Button login,reset;
    ImageView avt;
    LoginDao loginDao1;
    Spinner pquyen;
    CheckBox checkBox;
    Login login1;
    SharedPreferences sharedPreferences;
    ThanhVienDao thanhVienDao;
    SharedPreferences.Editor editor;
    String USERNAME_KEY = "user";
    String PASSWORD_KEY = "password";
    String REPASSWORD_KEY = "repassword";
    String PHANQUYEN_KEY = "pquyen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        back=findViewById(R.id.back);
        dangnhap=findViewById(R.id.dangnhap);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        reset=findViewById(R.id.reset);
        checkBox=findViewById(R.id.checkBox);
        pquyen=findViewById(R.id.pquyen);
        avt=findViewById(R.id.avt);
        loginDao1 = new LoginDao(LoginActivity.this);
        thanhVienDao = new ThanhVienDao(LoginActivity.this);
        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
        //xử lý click

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setText("");
                pass.setText("");
            }
        });

        String[] arrTT = {"Thành Viên","Admin","Thủ Thư"};
        ArrayAdapter<String> spnadapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item,arrTT);
        pquyen.setAdapter(spnadapter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk= user.getText().toString();
                String mk= pass.getText().toString();
                String setpquyen= (String)pquyen.getSelectedItem();

                //gan gia tr cua phan loai len dialog
                if (TextUtils.isEmpty(tk)) {
                    user.setError("Chưa nhập username");
                }else if(TextUtils.isEmpty(mk)){
                    pass.setError("Chưa nhập password");
                }
                else {
                    if (loginDao1.checktt(tk, mk,setpquyen)||thanhVienDao.checktt(tk,mk,setpquyen)) {
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            i.putExtra("tennd1",user.getText().toString());
                            i.putExtra("pquyen",setpquyen);
                            startActivity(i);
                            if(checkBox.isChecked()) {
                                editor = sharedPreferences.edit();
                                editor.putString(USERNAME_KEY, tk);
                                editor.putString(PASSWORD_KEY, mk);
                                editor.putString(PHANQUYEN_KEY,setpquyen);
                                editor.commit();
                            }
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}