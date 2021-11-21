package com.example.duanmau;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    SimpleDateFormat spfm1 = new SimpleDateFormat("dd-MM-yyyy");
    private ArrayList<PhieuMuon> dspm;
    private Context c;
    PhieuMuonDAO phieuMuonDAO;
    int position1;
    PhieuMuon phieuMuon;
    Sach sach;
    public PhieuMuonAdapter(Context c,ArrayList<PhieuMuon> dspm) {
        this.dspm = dspm;
        this.c = c;
        phieuMuonDAO=new PhieuMuonDAO(c);
    }


    @Override
    public PhieuMuonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.tu_item,parent,false);
        PhieuMuonViewHolder viewHolder = new PhieuMuonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhieuMuonAdapter.PhieuMuonViewHolder holder, int position) {
        SachDAO sachDAO=new SachDAO(c);
        PhieuMuon lg = dspm.get(position);
        int masach1= lg.getMaSach();
        holder.mapm.setText("Mã Phiếu : "+lg.getMaPM());
        holder.ngaythue.setText("Ngày Thuê : "+spfm1.format(lg.getNgayThue()));
        int giathue1= lg.getGiaTien();
        sach=sachDAO.getById(masach1);
        holder.giathue.setText("Giá Sách : "+sach.getGiaTien());
        holder.ngaytra.setText("Ngày Trả : "+spfm1.format(lg.getNgayTra()));
        holder.tentv.setText("Tên : "+lg.getTenTV());

        sach=sachDAO.getById(masach1);
        holder.masach.setText("Tên Sách : "+sach.getTenSach());
        holder.trangthai.setText("Trạng Thái : "+lg.getTrangThai());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(lg);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1=new AlertDialog.Builder(c);
                builder1.setMessage("Bạn Có Chắc Chắn Muốn Xóa?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(phieuMuonDAO.delete(lg.getMaPM())){
                            Toast.makeText(c, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            dspm.clear();
                            dspm.addAll(phieuMuonDAO.xemphieumuon());
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(c, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder1.create();
                alertDialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return dspm.size();
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder{
        TextView mapm,ngaythue,ngaytra,giathue,tentv,masach,trangthai;
        ImageView edit,delete;
        CardView cardView;

        public PhieuMuonViewHolder(View view){
            super(view);

            mapm =view.findViewById(R.id.mapm);
            ngaythue = view.findViewById(R.id.ngaythuepm);
            ngaytra = view.findViewById(R.id.ngaytrapm);
            giathue = view.findViewById(R.id.tienthuepm);
            tentv = view.findViewById(R.id.tentvpm);
            masach = view.findViewById(R.id.masachpm);
            trangthai = view.findViewById(R.id.trangthaipm);
            edit = view.findViewById(R.id.editpm);
            delete = view.findViewById(R.id.deletep);
            cardView = view.findViewById(R.id.cardviewloai4);

        }
    }
    public void openDialogUpdate(PhieuMuon phieuMuon){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.updatephieumuon,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvudpm,tvtiensach;
        EditText nhapngaymuon,nhapngaytra,nhaptentv;
        Spinner nhapmasachpm;
        CheckBox nhaptrangthai;

        Button capnhat,huy;
        tvudpm = v1.findViewById(R.id.tvudpm);
        nhapngaymuon = v1.findViewById(R.id.nhapngaythuepm);
        nhapngaytra = v1.findViewById(R.id.nhapngaytra);
        nhaptentv = v1.findViewById(R.id.nhaptentv);
        nhapmasachpm = v1.findViewById(R.id.nhapmasachpm);
        nhaptrangthai = v1.findViewById(R.id.nhaptrangthai);
        capnhat = v1.findViewById(R.id.capnhatpm);
        huy=v1.findViewById(R.id.huypm);
        nhapngaymuon.setText(spfm1.format(phieuMuon.getNgayThue()));
        nhapngaytra.setText(spfm1.format(phieuMuon.getNgayTra()));
        nhaptentv.setText(phieuMuon.getTenTV());
        nhaptrangthai.setChecked(false);

        SachDAO sachDAO=new SachDAO(c);
        ArrayList<Sach> listpl = sachDAO.getAll();
        ArrayAdapter spnadapter = new ArrayAdapter(c,android.R.layout.simple_spinner_dropdown_item,listpl);
        nhapmasachpm.setAdapter(spnadapter);

        for(int i=0;i<listpl.size();i++){
            if((String.valueOf(phieuMuon.getMaSach()).equalsIgnoreCase(listpl.toString()))){
                nhapmasachpm.setSelection(i);
            }
        }
        Sach pl1 =(Sach) nhapmasachpm.getSelectedItem();
        ArrayList<Sach> listgt = sachDAO.getAll(String.valueOf(pl1));


        //update du lieu tu dialog
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    phieuMuon.setNgayThue(spfm1.parse(nhapngaymuon.getText().toString()));
                    phieuMuon.setNgayTra(spfm1.parse(nhapngaytra.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phieuMuon.setTenTV(nhaptentv.getText().toString());
                Sach pl =(Sach) nhapmasachpm.getSelectedItem();
                int masach = pl.getMaSach();
                sach=sachDAO.getById(masach);
                phieuMuon.setGiaTien(sach.getGiaTien());
                phieuMuon.setMaSach(Integer.parseInt(String.valueOf(pl.getMaSach())));
                if(nhaptrangthai.isChecked()){
                    phieuMuon.setTrangThai("Đã Trả Sách");
                }else{
                    phieuMuon.setTrangThai("Chưa Trả Sách");
                }
                if(phieuMuonDAO.update(phieuMuon)==true){
                    Toast.makeText(c, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    dspm.clear();
                    dspm.addAll(phieuMuonDAO.xemphieumuon());
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(c, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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
}
