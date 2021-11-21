package com.example.duanmau;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {
    ThanhVienDao thanhVienDao;
    ThanhVien thanhVien;
    ArrayList<ThanhVien> ds= new ArrayList<>();
    ThanhVienAdapter adapter;
    FloatingActionButton floating;
    TextView tao;
    EditText nhapuser,hoten,nhappass,nhapdiachi,nhapsdt;
    ImageView themanhtv;
    Button regacc,huy;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThanhVienFragment() {
        // Required empty public constructor
    }
    public static ThanhVienFragment newInstance(String param1, String param2) {
        ThanhVienFragment fragment = new ThanhVienFragment();
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
        RecyclerView recyclerView = view.findViewById(R.id.review11);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        floating= view.findViewById(R.id.nutadd11);
        thanhVienDao=new ThanhVienDao(getContext());
        ds = thanhVienDao.getAll();
        adapter=new ThanhVienAdapter(getContext(),ds);
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
        View v1 = layoutInflater.inflate(R.layout.activity_regtv,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        //khai bao
        tao=v1.findViewById(R.id.tao11);
        nhappass=v1.findViewById(R.id.nhappass11);
        nhapuser=v1.findViewById(R.id.nhapuser11);
        hoten=v1.findViewById(R.id.hoten11);
        nhapdiachi=v1.findViewById(R.id.diachi11);
        nhapsdt=v1.findViewById(R.id.sdt11);
        themanhtv=v1.findViewById(R.id.themanhtv);
        regacc=v1.findViewById(R.id.regacc11);
        huy=v1.findViewById(R.id.huy11);
        thanhVienDao = new ThanhVienDao(getContext());
        themanhtv.setOnClickListener(new View.OnClickListener() {
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
        //xử lý click
        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= nhapuser.getText().toString();
                String name=hoten.getText().toString();
                String pass = nhappass.getText().toString();
                String diachi= nhapdiachi.getText().toString();
                String phanquyen = "Thành Viên";
                byte[] anh=ImageView_To_Byte(themanhtv);
                int sdt= Integer.parseInt(nhapsdt.getText().toString());
                if (TextUtils.isEmpty(user)) {
                    nhapuser.setError("Chưa nhập tên người dùng");
                }else if(TextUtils.isEmpty(name)){
                    hoten.setError("Chưa nhập họ tên");
                }else if(TextUtils.isEmpty(pass)){
                    nhappass.setError("Chưa nhập mật khẩu");
                }else if(TextUtils.isEmpty(diachi)){
                    nhapdiachi.setError("Chưa nhập địa chỉ");

                }else if(TextUtils.isEmpty(sdt+"")){
                    nhapsdt.setError("Chưa nhập sđt");
                }
                else {
                    if (thanhVienDao.insert(user, name, pass, diachi, sdt,phanquyen,anh) == true) {
                            Toast.makeText(getContext(), "Thêm Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            ds.clear();
                            ds.addAll(thanhVienDao.getAll());
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
                themanhtv.setImageBitmap(imageBitmap);
            } else {
                Uri uri = data.getData();
                themanhtv.setImageURI(uri);
            }

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doipass11, container, false);
    }
}