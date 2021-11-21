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


public class QuanLyLoaiSachFragment extends Fragment {

    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    ArrayList<LoaiSach> dsls = new ArrayList<LoaiSach>();
    LoaiSachAdapter adapter;
    FloatingActionButton floating;
    TextView taols;
    EditText nhaptenls;
    ImageView avtreg;
    Button regacc, huy;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }

    public static QuanLyLoaiSachFragment newInstance(String param1, String param2) {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.reviewls);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        floating = view.findViewById(R.id.nutaddls);
        loaiSachDAO = new LoaiSachDAO(getContext());
        dsls = loaiSachDAO.xemloai();
        adapter = new LoaiSachAdapter(getContext(), dsls);
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
            View v1 = layoutInflater.inflate(R.layout.addloaisach, null);
            builder.setView(v1);
            AlertDialog dialog = builder.create();
            dialog.show();
            //khai bao
            taols = v1.findViewById(R.id.tvaddls);
            nhaptenls = v1.findViewById(R.id.tenls);
            regacc = v1.findViewById(R.id.addls);
            huy = v1.findViewById(R.id.huyaddls);
            loaiSachDAO = new LoaiSachDAO(getContext());

            //xử lý click
            regacc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tenloai = nhaptenls.getText().toString();
                    if (TextUtils.isEmpty(tenloai)) {
                        nhaptenls.setError("Chưa nhập tên loại");
                    } else {
                        if (loaiSachDAO.insert(tenloai) == true) {
                                Toast.makeText(getContext(), "Thêm Tên Loại Thành Công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                dsls.clear();
                                dsls.addAll(loaiSachDAO.xemloai());
                        } else {
                            Toast.makeText(getContext(), "Thêm Tên Loại Thất Bại", Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }
}