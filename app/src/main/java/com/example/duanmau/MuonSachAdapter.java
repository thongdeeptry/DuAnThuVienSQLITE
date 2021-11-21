package com.example.duanmau;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MuonSachAdapter extends RecyclerView.Adapter<MuonSachAdapter.MuonSachViewHolder> {
    SimpleDateFormat spfm1 = new SimpleDateFormat("dd-MM-yyyy");
    private ArrayList<Sach> dsm;
    private ArrayList<PhieuMuon> dspm;
    private Context c;
    LoginDao loginDao;
    Login login;
    PhieuMuonDAO phieuMuonDAO;
    int position1;
    PhieuMuon phieuMuon;
    Sach sach;
    LoaiSach loaiSach;

    public MuonSachAdapter(Context c, ArrayList<Sach> dsm) {
        this.dsm = dsm;
        this.c = c;
        phieuMuonDAO = new PhieuMuonDAO(c);
    }


    @Override
    public MuonSachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View view = inflater.inflate(R.layout.nam_item, parent, false);
        MuonSachViewHolder viewHolder = new MuonSachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MuonSachAdapter.MuonSachViewHolder holder, int position) {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(c);
        SachDAO sachDAO =  new SachDAO(c);
        Sach lg = dsm.get(position);
        holder.tensachmuon.setText("Tên Sách : " + lg.getTenSach());
        holder.tienmuon.setText("Gía Tiền : " + lg.getGiaTien());
        int maLoai1 = lg.getMaLoai();
        loaiSach = loaiSachDAO.getById(maLoai1);
        holder.maloaimuon.setText("Tên Loại : " + loaiSach.getTenLoai());
    }

    @Override
    public int getItemCount() {
        return dsm.size();
    }

    public class MuonSachViewHolder extends RecyclerView.ViewHolder {
        TextView tensachmuon, tienmuon, maloaimuon;
        CardView cardView;

        public MuonSachViewHolder(View view) {
            super(view);

            tensachmuon = view.findViewById(R.id.tensachmuon);
            tienmuon = view.findViewById(R.id.tienmuon);
            maloaimuon = view.findViewById(R.id.maloaimuon);
            cardView = view.findViewById(R.id.cardviewloai5);


        }
    }

    public void MuonSach() {

        }
    }
