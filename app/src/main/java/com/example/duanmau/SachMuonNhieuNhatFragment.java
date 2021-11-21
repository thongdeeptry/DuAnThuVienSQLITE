package com.example.duanmau;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SachMuonNhieuNhatFragment extends Fragment {

    ListView lv;
    AppCompatButton check;
    EditText nhapkttop;
    ArrayList<Top> list10;
    TopAdapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SachMuonNhieuNhatFragment() {
        // Required empty public constructor
    }

    public static SachMuonNhieuNhatFragment newInstance(String param1, String param2) {
        SachMuonNhieuNhatFragment fragment = new SachMuonNhieuNhatFragment();
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
        lv=view.findViewById(R.id.lv);
        nhapkttop =view.findViewById(R.id.nhapkttop);
        check =view.findViewById(R.id.kiemtratop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so = Integer.parseInt(nhapkttop.getText().toString());
                list10 = (ArrayList<Top>) thongKeDAO.getTOP(so);
                adapter = new TopAdapter(getContext(),list10);
                lv.setAdapter(adapter);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sach_muon_nhieu_nhat, container, false);
    }
}