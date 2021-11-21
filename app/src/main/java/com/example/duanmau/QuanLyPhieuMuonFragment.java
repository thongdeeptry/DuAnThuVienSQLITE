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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuanLyPhieuMuonFragment extends Fragment {
    SimpleDateFormat spfm1 = new SimpleDateFormat("dd-MM-yyyy");
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuon phieuMuon;
    ArrayList<PhieuMuon> dspmm = new ArrayList<PhieuMuon>();
    PhieuMuonAdapter adapter;
    FloatingActionButton floating;
    TextView tvaddpm,addtienpm;
    EditText addngaymuon,addngaytra,addtentv;
    Spinner addmasachpm;
    CheckBox addtrangthai;
    ImageView avtreg;
    Button regacc, huy;
    Sach sach;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public QuanLyPhieuMuonFragment() {
        // Required empty public constructor
    }
    public static QuanLyPhieuMuonFragment newInstance(String param1, String param2) {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
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
        RecyclerView recyclerView = view.findViewById(R.id.reviewpm);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        floating = view.findViewById(R.id.nutaddpm);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        dspmm = phieuMuonDAO.xemphieumuon();
        adapter = new PhieuMuonAdapter(getContext(), dspmm);
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
        View v1 = layoutInflater.inflate(R.layout.addphieumuon, null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        //khai bao
        tvaddpm = v1.findViewById(R.id.tvaddpm);
        addngaymuon = v1.findViewById(R.id.addngaythuepm);
        addngaytra = v1.findViewById(R.id.addngaytra);
        addtentv = v1.findViewById(R.id.addtentv);
        addmasachpm = v1.findViewById(R.id.addmasachpm);
        addtrangthai = v1.findViewById(R.id.addtrangthai);
        regacc = v1.findViewById(R.id.addpm);
        huy = v1.findViewById(R.id.huyaddpm);
        phieuMuonDAO = new PhieuMuonDAO(getContext());

        SachDAO sachDAO=new SachDAO(getContext());
        ArrayList<Sach> listpl = sachDAO.getAll();
        ArrayAdapter spnadapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,listpl);
        addmasachpm.setAdapter(spnadapter);

        //xử lý click
        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Date ngaymuon = spfm1.parse(addngaymuon.getText().toString());
                    Date ngaytra = spfm1.parse(addngaytra.getText().toString());

                String tentv = addtentv.getText().toString();
                        String tt = "Chưa Trả Sách";
                Sach pl =(Sach) addmasachpm.getSelectedItem();
                int masach = pl.getMaSach();
                    sach=sachDAO.getById(masach);
                    int gia= sach.getGiaTien();
                if (TextUtils.isEmpty(tentv)) {
                    addtentv.setError("Chưa nhập tên sách");

                }else {
                    PhieuMuon phieuMuon = new PhieuMuon(ngaymuon,gia,ngaytra,tentv,tt,masach);
                    if (phieuMuonDAO.insert(phieuMuon) == true) {
                        Toast.makeText(getContext(), "Thêm Sách Thành Công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        dspmm.clear();
                        dspmm.addAll(phieuMuonDAO.xemphieumuon());
                    } else {
                        Toast.makeText(getContext(), "Thêm Sách Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
                } catch (ParseException e) {
                    e.printStackTrace();
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
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }
}