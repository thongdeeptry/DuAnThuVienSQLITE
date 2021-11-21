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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private ArrayList<Sach> dss;
    private Context c;
    SachDAO sachDAO;
    int position1;
    LoaiSach loaiSach;
    public SachAdapter(Context c,ArrayList<Sach> dss) {
        this.dss = dss;
        this.c = c;
        sachDAO=new SachDAO(c);
    }


    @Override
    public SachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.tam_item,parent,false);
        SachViewHolder viewHolder = new SachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SachAdapter.SachViewHolder holder, int position) {
        LoaiSachDAO loaiSachDAO=new LoaiSachDAO(c);
        Sach lg = dss.get(position);
        holder.tens.setText("Tên Sách : "+lg.getTenSach());
        holder.gias.setText("Gía Tiền : "+lg.getGiaTien());
        int maLoai1= lg.getMaLoai();
        loaiSach=loaiSachDAO.getById(maLoai1);
        holder.maloais.setText("Tên Loại : "+loaiSach.getTenLoai());
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
                        if(sachDAO.delete(lg.getMaSach())){
                            Toast.makeText(c, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            dss.clear();
                            dss.addAll(sachDAO.xemsach());
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
        return dss.size();
    }

    public class SachViewHolder extends RecyclerView.ViewHolder{
        TextView tens,gias,maloais;
        ImageView edit,delete;
        CardView cardView;

        public SachViewHolder(View view){
            super(view);

            tens =view.findViewById(R.id.ids);
            gias = view.findViewById(R.id.tens);
            maloais = view.findViewById(R.id.maloais);
            edit = view.findViewById(R.id.edits);
            delete = view.findViewById(R.id.deletes);
            cardView = view.findViewById(R.id.cardviewloai3);

        }
    }
    public void openDialogUpdate(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.updatesach,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvuds;
        EditText nhaptens,nhapgias;
        Spinner nhaploais;

        Button capnhat,huy;
        tvuds = v1.findViewById(R.id.tvuds);
        nhaptens = v1.findViewById(R.id.nhaptens);
        nhapgias = v1.findViewById(R.id.nhapgias);
        nhaploais = v1.findViewById(R.id.nhaploais);
        capnhat = v1.findViewById(R.id.capnhats);
        huy=v1.findViewById(R.id.huys);
        nhaptens.setText(sach.getTenSach());
        LoaiSachDAO loaiSachDAO=new LoaiSachDAO(c);
        ArrayList<LoaiSach> listpl = loaiSachDAO.getAll();
        ArrayAdapter spnadapter = new ArrayAdapter(c,android.R.layout.simple_spinner_dropdown_item,listpl);
        nhaploais.setAdapter(spnadapter);

        for(int i=0;i<listpl.size();i++){
            if((String.valueOf(sach.getMaLoai()).equalsIgnoreCase(listpl.toString()))){
                nhaploais.setSelection(i);
            }
        }
        //update du lieu tu dialog
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach.setTenSach(nhaptens.getText().toString());
                sach.setGiaTien(Integer.parseInt(nhapgias.getText().toString()));
                LoaiSach pl =(LoaiSach) nhaploais.getSelectedItem();
                sach.setMaLoai(Integer.parseInt(String.valueOf(pl.getMaLoai())));
                if(sachDAO.update(sach)==true){
                    Toast.makeText(c, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    dss.clear();
                    dss.addAll(sachDAO.getAll());
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
