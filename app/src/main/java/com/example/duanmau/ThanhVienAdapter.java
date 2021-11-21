package com.example.duanmau;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.LoginViewHolder> {
    private ArrayList<ThanhVien> ds;
    private Context c;
    ThanhVienDao thanhVienDao;
    int position1;
    public ThanhVienAdapter(Context c, ArrayList<ThanhVien> ds) {
        this.ds = ds;
        this.c = c;
        thanhVienDao=new ThanhVienDao(c);
    }


    @Override
    public LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.view_item1,parent,false);
        LoginViewHolder viewHolder = new LoginViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThanhVienAdapter.LoginViewHolder holder, int position) {
        ThanhVien lg = ds.get(position);
        holder.usern.setText("Username : "+lg.getUsername());
        holder.diachi.setText("Tên : "+lg.getName());
        holder.sdt.setText("Số Phone : "+lg.getSdt());
        holder.name.setText("Địa Chỉ : "+lg.getDiachi());

        Bitmap bitmap= BitmapFactory.decodeByteArray(lg.anh,0,lg.anh.length);
        holder.anhtv.setImageBitmap(bitmap);
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
                        if(thanhVienDao.delete(lg.getId())){
                            Toast.makeText(c, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            ds.clear();
                            ds.addAll(thanhVienDao.getAll());
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
        return ds.size();
    }

    public class LoginViewHolder extends RecyclerView.ViewHolder{
        TextView usern,diachi,sdt,name;
        ImageView edit,delete,anhtv;
        CardView cardView;

        public LoginViewHolder(View view){
            super(view);

            usern =view.findViewById(R.id.usern1);
            diachi = view.findViewById(R.id.diachi);
            sdt = view.findViewById(R.id.sdt);
            name = view.findViewById(R.id.name);
            edit = view.findViewById(R.id.edit1);
            anhtv = view.findViewById(R.id.anhtv);
            delete = view.findViewById(R.id.delete1);
            cardView = view.findViewById(R.id.cardviewloai11);

        }
    }
    public void openDialogUpdate(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.fragment_doipass,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvup;
        ImageView avtud;
        EditText dtht,mkmoi,diachi,sdt,tvuser;
        Button capnhat,huy;
        tvup = v1.findViewById(R.id.tvud);
        avtud = v1.findViewById(R.id.avtud);
        tvuser = v1.findViewById(R.id.tvuser1);
        dtht = v1.findViewById(R.id.dtht1);
        mkmoi = v1.findViewById(R.id.mkmoi1);
        diachi = v1.findViewById(R.id.diachine);
        sdt = v1.findViewById(R.id.nhapsdt);
        capnhat = v1.findViewById(R.id.capnhat1);
        huy=v1.findViewById(R.id.huy1);
        tvuser.setText(thanhVien.getUsername());
        dtht.setText(thanhVien.getName());
        diachi.setText(thanhVien.getDiachi());

        //update du lieu tu dialog
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVien.setName(dtht.getText().toString());
                thanhVien.setPass(mkmoi.getText().toString());
                thanhVien.setDiachi(diachi.getText().toString());
                thanhVien.setSdt(Integer.parseInt(sdt.getText().toString()));
                if(thanhVienDao.update(thanhVien)==true){
                    Toast.makeText(c, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    ds.clear();
                    ds.addAll(thanhVienDao.getAll());
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
