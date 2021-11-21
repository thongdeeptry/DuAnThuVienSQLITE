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

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginViewHolder> {
    private ArrayList<Login> ds;
    private Context c;
    LoginDao loginDao;
    int position1;
    public LoginAdapter(Context c,ArrayList<Login> ds) {
        this.ds = ds;
        this.c = c;
        loginDao=new LoginDao(c);
    }


    @Override
    public LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.view_item,parent,false);
        LoginViewHolder viewHolder = new LoginViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoginAdapter.LoginViewHolder holder, int position) {
        Login lg = ds.get(position);
        holder.usern.setText("Username : "+lg.getUsername());
        holder.pquyen.setText("Quyền : "+lg.getPhanquyen());
        Bitmap bitmap= BitmapFactory.decodeByteArray(lg.anh,0,lg.anh.length);
        holder.anh.setImageBitmap(bitmap);
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
                        if(loginDao.delete(lg.getId())){
                            Toast.makeText(c, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            ds.clear();
                            ds.addAll(loginDao.getAll("Thủ Thư"));
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
        TextView usern,pquyen;
        ImageView edit,delete,anh;
        CardView cardView;

        public LoginViewHolder(View view){
            super(view);

            usern =view.findViewById(R.id.usern);
            pquyen = view.findViewById(R.id.pquyen);
            edit = view.findViewById(R.id.edit);
            anh = view.findViewById(R.id.anh);
            delete = view.findViewById(R.id.delete);
            cardView = view.findViewById(R.id.cardviewloai1);

        }
    }
    public void openDialogUpdate(Login login){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater layoutInflater = ((Activity)c).getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.fragment_doi_mat_khau,null);
        builder.setView(v1);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvup;
        ImageView avtud,suaanh;
        EditText dtht,mkmoi,remkmoi,tvuser;
        Spinner tvquyen;
        Button capnhat,huy;
        tvup = v1.findViewById(R.id.tvud);
        avtud = v1.findViewById(R.id.avtud);
        tvuser = v1.findViewById(R.id.tvuser);
        dtht = v1.findViewById(R.id.dtht);
        mkmoi = v1.findViewById(R.id.mkmoi);
        remkmoi = v1.findViewById(R.id.remkmoi);
        tvquyen = v1.findViewById(R.id.tvquyen);
        capnhat = v1.findViewById(R.id.capnhat);
        huy=v1.findViewById(R.id.huy);
        tvuser.setText(login.getUsername());
        dtht.setText(login.getName());
        String[] arrTT1 = {"Admin","Thủ Thư"};
        ArrayAdapter<String> spnadapter1 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item,arrTT1);
        tvquyen.setAdapter(spnadapter1);
        //gan gia tr cua phan loai len dialog
        for(int i=0;i<arrTT1.length;i++){
            if(login.getPhanquyen().equalsIgnoreCase(arrTT1[i])){
                tvquyen.setSelection(i);
            }
        }

        //update du lieu tu dialog
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setName(dtht.getText().toString());
                login.setPass(mkmoi.getText().toString());
                login.setRepass(remkmoi.getText().toString());
                login.setPhanquyen((String)tvquyen.getSelectedItem());
                if(loginDao.update(login)==true){
                    Toast.makeText(c, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    ds.clear();
                    ds.addAll(loginDao.getAll("Thủ Thư"));
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
