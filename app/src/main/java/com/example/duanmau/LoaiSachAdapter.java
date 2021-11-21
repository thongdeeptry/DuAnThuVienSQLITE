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

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    private ArrayList<LoaiSach> dsls;
    private Context c;
    LoaiSachDAO loaiSachDAO;
    int position1;
    public LoaiSachAdapter(Context c,ArrayList<LoaiSach> dsls) {
        this.dsls = dsls;
        this.c = c;
        loaiSachDAO=new LoaiSachDAO(c);
    }


    @Override
    public LoaiSachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.two_item,parent,false);
        LoaiSachViewHolder viewHolder = new LoaiSachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoaiSachAdapter.LoaiSachViewHolder holder, int position) {
        LoaiSach lg = dsls.get(position);
        holder.usern.setText("Mã Loại : "+lg.getMaLoai());
        holder.pquyen.setText("Tên Loại : "+lg.getTenLoai());
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
                        if(loaiSachDAO.delete(lg.getMaLoai())){
                            Toast.makeText(c, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            dsls.clear();
                            dsls.addAll(loaiSachDAO.xemloai());
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
        return dsls.size();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder{
        TextView usern,pquyen;
        ImageView edit,delete;
        CardView cardView;

        public LoaiSachViewHolder(View view){
            super(view);

            usern =view.findViewById(R.id.idls);
            pquyen = view.findViewById(R.id.tenls);
            edit = view.findViewById(R.id.editls);
            delete = view.findViewById(R.id.deletels);
            cardView = view.findViewById(R.id.cardviewloai2);

        }
    }
    public void openDialogUpdate(LoaiSach loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.updateloai,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvls;
        EditText nhaptenls;

        Button capnhat,huy;
        tvls = v1.findViewById(R.id.tvudls);
        nhaptenls = v1.findViewById(R.id.nhaptenls);
        capnhat = v1.findViewById(R.id.capnhatls);
        huy=v1.findViewById(R.id.huyls);
        nhaptenls.setText(loaiSach.getTenLoai());

        //update du lieu tu dialog
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSach.setTenLoai(nhaptenls.getText().toString());
                if(loaiSachDAO.update(loaiSach)==true){
                    Toast.makeText(c, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    dsls.clear();
                    dsls.addAll(loaiSachDAO.xemloai());
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
