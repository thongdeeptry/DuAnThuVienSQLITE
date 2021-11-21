package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Login> ds= new ArrayList<Login>();
    DrawerLayout drawerLayout;
    Toolbar toolbar1;
    NavigationView navigationView;
    LoginDao loginDao;
    TextView tennd,quyennd;
    Login login;
    int vitri=0;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar1=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menuu);
        ab.setDisplayHomeAsUpEnabled(true);
        loginDao = new LoginDao(MainActivity.this);

        Menu nav_Menu = navigationView.getMenu();
        String tnd = getIntent().getExtras().getString("tennd1");
        String tnd1 = getIntent().getExtras().getString("pquyen");
        View headerView = navigationView.getHeaderView(vitri);
        TextView tend = (TextView) headerView.findViewById(R.id.tennd);
        TextView quyend = (TextView) headerView.findViewById(R.id.quyennd);
        ImageView avtlogo = (ImageView) headerView.findViewById(R.id.avtlogo);
        tend.setText("Xin chào : "+tnd);
        quyend.setText("Quyền Người Dùng: "+tnd1);

        if(tnd1.equals("Admin")){
            nav_Menu.findItem(R.id.nav_muonsach1).setVisible(false);
            nav_Menu.findItem(R.id.nav_doimktv1).setVisible(false);
            Toast.makeText(MainActivity.this, "Bạn Là Quản Trị Viên", Toast.LENGTH_SHORT).show();
        }else if(tnd1.equals("Thành Viên")){
            nav_Menu.findItem(R.id.nav_phieumuon1).setVisible(false);
            nav_Menu.findItem(R.id.nav_thanhvien1).setVisible(false);
            nav_Menu.findItem(R.id.nav_thanhvien11).setVisible(false);
            nav_Menu.findItem(R.id.nav_doanhthu1).setVisible(false);
            nav_Menu.findItem(R.id.nav_loaisach1).setVisible(false);
            nav_Menu.findItem(R.id.nav_sach1).setVisible(false);
            nav_Menu.findItem(R.id.nav_doimka).setVisible(false);
        }else{
            nav_Menu.findItem(R.id.nav_doimktv1).setVisible(false);
            nav_Menu.findItem(R.id.nav_thanhvien1).setVisible(false);
            nav_Menu.findItem(R.id.nav_muonsach1).setVisible(false);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {

                xulychon(menuItem);
                return false;
            }
        });


    }
    void xulychon(MenuItem menuItem){
        int id=menuItem.getItemId();
        Fragment fragment;
        Class classfragment = null;
        if(id==R.id.nav_home1){
            classfragment=HomeFragment.class;

        }
        if(id==R.id.nav_phieumuon1){
            classfragment=QuanLyPhieuMuonFragment.class;

        }
        if(id==R.id.nav_loaisach1){
            classfragment= QuanLyLoaiSachFragment.class;

        }
        if(id==R.id.nav_sach1){
            classfragment=QuanLySachFragment.class;

        }
        if(id==R.id.nav_thanhvien1){
            classfragment=QuanLyThanhVienFragment.class;

        }
        if(id==R.id.nav_muonsach1){
            classfragment=MuonSachFragment.class;

        }
        if(id==R.id.nav_thanhvien11){
            classfragment=ThanhVienFragment.class;

        }
        if(id==R.id.nav_10sach1){
            classfragment=SachMuonNhieuNhatFragment.class;

        }
        if(id==R.id.nav_doanhthu1){
            classfragment=TongDoanhThuFragment.class;

        }
        if(id==R.id.nav_gioithieu1){
            classfragment=GioiThieuFragment.class;

        }
        if(id==R.id.nav_doimka){
            classfragment=DoiMatKhauFragment.class;

        }
        if(id==R.id.nav_doimktv1){
            classfragment=DoiMatKhauTVFragment.class;

        }
        if(id==R.id.nav_thoat){
            AlertDialog.Builder builder1=new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Bạn Có Chắc Chắn Muốn Thoát?");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences myPrefs = getSharedPreferences("Login",
                                    MODE_PRIVATE);
                            SharedPreferences.Editor editor = myPrefs.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this,
                                    LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                }
            });
            builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder1.create();
            alertDialog.show();

        }
        try{
            fragment=(Fragment) classfragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
            menuItem.setCheckable(true);
            setTitle(menuItem.getTitle());
            drawerLayout.closeDrawer(GravityCompat.START);
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }
}