package com.example.duanmau;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DoiMatKhauFragment extends Fragment {
    TextView tvup;
    ImageView avtud;
    EditText dtht,mkmoi,remkmoi,tvuser,mkcu;
    Button capnhat,huy;
LoginDao loginDao;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    public static DoiMatKhauFragment newInstance(String param1, String param2) {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        tvup = v1.findViewById(R.id.tvud);
        avtud = v1.findViewById(R.id.avtud);
        dtht = v1.findViewById(R.id.dthtcn);
        mkmoi = v1.findViewById(R.id.mkmoicn);
        remkmoi = v1.findViewById(R.id.remkmoicn);
        mkcu = v1.findViewById(R.id.mkcucn);
        capnhat = v1.findViewById(R.id.capnhatcn);
        huy=v1.findViewById(R.id.huycn);
        loginDao = new LoginDao(getContext());
        SharedPreferences preferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String layuser = preferences.getString("user","");
        String passcu = preferences.getString("password","");
        String hotcn = dtht.getText().toString();
        String passmoi = mkmoi.getText().toString();
        String repassmoi =  remkmoi.getText().toString();
        int checkmk = passmoi.compareTo(repassmoi);
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dtht.getText().length()==0||mkcu.getText().length()==0||mkmoi.getText().length()==0||remkmoi.getText().length()==0) {
                    Toast.makeText(getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                }else if(!passcu.equals(mkcu.getText().toString())){
                    Toast.makeText(getContext(), "Mật Khẩu Cũ Sai", Toast.LENGTH_SHORT).show();
                }else if(checkmk!=0){
                    Toast.makeText(getContext(), "Mật Khẩu Mới Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                }else{
                    Login checkuser = loginDao.getByUser(layuser);
                    checkuser.setName(dtht.getText().toString());
                    checkuser.setPass(mkmoi.getText().toString());
                    checkuser.setRepass(remkmoi.getText().toString());
                    if(loginDao.updatecn(checkuser)==true){
                        Toast.makeText(getContext(), "Cập Nhật Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                        dtht.setText("");
                        mkcu.setText("");
                        mkmoi.setText("");
                        remkmoi.setText("");
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
        mkcu.setText("");
        mkmoi.setText("");
        remkmoi.setText("");
    }
});

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_changepass, container, false);
    }
}