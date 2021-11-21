package com.example.duanmau;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    Context c;
    ArrayList<Top> list;
    ArrayList<PhieuMuon> dspm;
    Sach sach;
    SachDAO sachDAO;
    TextView tensachtop,soluong;
    public TopAdapter(Context context, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.c=context;
        this.list = lists;
    }


    @NonNull
    @Override
    public View getView(int position,  View v,  ViewGroup parent) {
        View v1 = v;
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        v1 = layoutInflater.inflate(R.layout.top_item,null);
        tensachtop = v1.findViewById(R.id.tensachtop);
        soluong = v1.findViewById(R.id.soluong);
        final Top item = list.get(position);
        sachDAO=new SachDAO(c);
        int masach1= item.getMaSach();
        sach=sachDAO.getById(masach1);
            tensachtop.setText("Sách : " +sach.getTenSach());
            soluong.setText("Số Lượng : " + item.getSoLuong());
        return v1;
    }
}
