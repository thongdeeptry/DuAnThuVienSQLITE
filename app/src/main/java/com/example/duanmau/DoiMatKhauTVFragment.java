package com.example.duanmau;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class DoiMatKhauTVFragment extends Fragment {
    TextView tvup;
    ImageView avtud;
    EditText dtht,mkmoi,diachi,tvuser,mkcu,sdt;
    Button capnhat,huy;
ThanhVienDao thanhVienDao;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoiMatKhauTVFragment() {
        // Required empty public constructor
    }

    public static DoiMatKhauTVFragment newInstance(String param1, String param2) {
        DoiMatKhauTVFragment fragment = new DoiMatKhauTVFragment();
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
    public void onViewCreated(View v1, Bundle savedInstanceState) {
        super.onViewCreated(v1, savedInstanceState);
        tvup = v1.findViewById(R.id.tvud111);
        avtud = v1.findViewById(R.id.avtud111);
        dtht = v1.findViewById(R.id.dthtcn111);
        mkmoi = v1.findViewById(R.id.mkmoicn111);
        diachi = v1.findViewById(R.id.diachi111);
        sdt = v1.findViewById(R.id.sdt111);
        capnhat = v1.findViewById(R.id.capnhatcn111);
        huy=v1.findViewById(R.id.huycn111);
        thanhVienDao = new ThanhVienDao(getContext());
        SharedPreferences preferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String layuser = preferences.getString("user","");
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dtht.getText().length()==0||mkmoi.getText().length()==0||diachi.getText().length()==0) {
                    Toast.makeText(getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                }else{
                    ThanhVien checkuser1 = thanhVienDao.getByUser1(layuser);
                    checkuser1.setName(dtht.getText().toString());
                    checkuser1.setPass(mkmoi.getText().toString());
                    checkuser1.setDiachi(diachi.getText().toString());
                    checkuser1.setSdt(Integer.parseInt(sdt.getText().toString()));
                    if(thanhVienDao.updatecn(checkuser1)==true){
                        Toast.makeText(getContext(), "Cập Nhật Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                        dtht.setText("");
                        mkmoi.setText("");
                        diachi.setText("");
                        sdt.setText("");
                    }else{
                        Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
huy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dtht.setText("");
        mkmoi.setText("");
        diachi.setText("");
        sdt.setText("");
    }
});

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_changepasstv, container, false);
    }
}