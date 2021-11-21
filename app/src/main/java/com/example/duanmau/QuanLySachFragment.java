package com.example.duanmau;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class QuanLySachFragment extends Fragment {
    SachDAO sachDAO;
    Sach sach;
    ArrayList<Sach> dss = new ArrayList<Sach>();
    SachAdapter adapter;
    FloatingActionButton floating;
    TextView taos;
    EditText nhaptens,nhapgias;
    Spinner nhaploaiss;
    ImageView avtreg;
    Button regacc, huy;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLySachFragment() {
        // Required empty public constructor
    }

    public static QuanLySachFragment newInstance(String param1, String param2) {
        QuanLySachFragment fragment = new QuanLySachFragment();
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
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.reviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        floating = view.findViewById(R.id.nutadds);
        sachDAO = new SachDAO(getContext());
        dss = sachDAO.xemsach();
        adapter = new SachAdapter(getContext(), dss);
        recyclerView.setAdapter(adapter);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
    }
    public void openDialogAdd () {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.addsach, null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        //khai bao
        taos = v1.findViewById(R.id.tvadds);
        nhaptens = v1.findViewById(R.id.tens);
        nhapgias = v1.findViewById(R.id.gias);
        nhaploaiss = v1.findViewById(R.id.maloais);
        regacc = v1.findViewById(R.id.adds);
        huy = v1.findViewById(R.id.huyadds);
        sachDAO = new SachDAO(getContext());
        LoaiSachDAO loaiSachDAO=new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> listpl = loaiSachDAO.getAll();
        ArrayAdapter spnadapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,listpl);
        nhaploaiss.setAdapter(spnadapter);

        //xử lý click
        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = nhaptens.getText().toString();
                int gia = Integer.parseInt(nhapgias.getText().toString());
                LoaiSach pl =(LoaiSach) nhaploaiss.getSelectedItem();
                int loai = pl.getMaLoai();
                if (TextUtils.isEmpty(tensach)) {
                    nhaptens.setError("Chưa nhập tên sách");

                }else if (TextUtils.isEmpty(nhapgias.getText().toString())) {
                    nhapgias.setError("Chưa nhập giá sách");

                } else {
                    Sach sach = new Sach(tensach,gia,loai);
                    if (sachDAO.insert(sach) == true) {
                        Toast.makeText(getContext(), "Thêm Sách Thành Công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        dss.clear();
                        dss.addAll(sachDAO.xemsach());
                    } else {
                        Toast.makeText(getContext(), "Thêm Sách Thất Bại", Toast.LENGTH_SHORT).show();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }
}