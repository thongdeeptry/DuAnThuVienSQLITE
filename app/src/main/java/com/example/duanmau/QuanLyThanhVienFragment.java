package com.example.duanmau;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class QuanLyThanhVienFragment extends Fragment {
    LoginDao loginDao;
    Login login;
    ArrayList<Login> ds= new ArrayList<>();
    LoginAdapter adapter;
    FloatingActionButton floating;
    TextView tao;
    EditText nhapuser,hoten,nhappass,nhaprepass;
    ImageView avtreg,themanh;
    Button regacc,huy;
    Spinner nhappquyen;
    ArrayList<Login> logins;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyThanhVienFragment() {
        // Required empty public constructor
    }
    public static QuanLyThanhVienFragment newInstance(String param1, String param2) {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.review);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        floating= view.findViewById(R.id.nutadd);
        loginDao=new LoginDao(getContext());
        ds = loginDao.getAll("Thủ Thư");
        adapter=new LoginAdapter(getContext(),ds);
        recyclerView.setAdapter(adapter);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openDialogAdd();
            }
        });
    }
    public void openDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.activity_regadmin,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        //khai bao
        tao=v1.findViewById(R.id.tao1);
        nhappass=v1.findViewById(R.id.nhappass1);
        nhapuser=v1.findViewById(R.id.nhapuser1);
        hoten=v1.findViewById(R.id.hoten1);
        nhappquyen=v1.findViewById(R.id.nhappquyen);
        avtreg=v1.findViewById(R.id.avtreg1);
        regacc=v1.findViewById(R.id.regacc1);
        themanh=v1.findViewById(R.id.themanh);
        huy=v1.findViewById(R.id.huy);
        loginDao = new LoginDao(getContext());
        String[] arrTT1 = {"Admin","Thủ Thư"};
        ArrayAdapter<String> spnadapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,arrTT1);
        nhappquyen.setAdapter(spnadapter1);
        //xử lý click

        themanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick=new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser=Intent.createChooser(pick, "Lựa Chọn");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{pho});
                startActivityForResult(chosser, 999);
            }
        });

        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= nhapuser.getText().toString();
                String name=hoten.getText().toString();
                String pass = nhappass.getText().toString();
                String repass= "";
                String phanquyen=(String)nhappquyen.getSelectedItem();

                byte[] anh=ImageView_To_Byte(themanh);

                int checkpass = pass.compareTo(repass);
                if (TextUtils.isEmpty(user)) {
                    nhapuser.setError("Chưa nhập tên người dùng");
                }else if(TextUtils.isEmpty(name)){
                    hoten.setError("Chưa nhập họ tên");
                }else if(TextUtils.isEmpty(pass)){
                    nhappass.setError("Chưa nhập mật khẩu");
                }else if(TextUtils.isEmpty(phanquyen)){
                    nhaprepass.setError("Chưa chọn Quyền");
                }
                else {
                    if (loginDao.insert(user, name, pass, repass, phanquyen,anh) == true) {
                            Toast.makeText(getContext(), "Thêm Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            ds.clear();
                            ds.addAll(loginDao.getAll("Thủ Thư"));
                    } else {
                        Toast.makeText(getContext(), "Thêm Tài Khoản Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {

            if (data.getExtras() != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                themanh.setImageBitmap(imageBitmap);
            } else {
                Uri uri = data.getData();
                themanh.setImageURI(uri);
            }

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doipass, container, false);
    }
}