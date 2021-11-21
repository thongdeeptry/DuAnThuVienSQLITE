package com.example.duanmau;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class TongDoanhThuFragment extends Fragment {
    TextView tongsach,tongloai,tongtv,tongpm,tongtien,tongtienall;
    ImageView imtongsach,imtongloai,imtongtv,imtongpm,imtongtien,imtongtienall;
    AppCompatButton ngaybd,ngaykt,tinhdt;
    SimpleDateFormat spfm1 = new SimpleDateFormat("dd-MM-yyyy");
    int mYear;
    int mMonth;
    int mDay;
    SachDAO sachDAO;
    PhieuMuonDAO phieuMuonDAO;
    LoaiSachDAO loaiSachDAO;
    LoginDao loginDao;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TongDoanhThuFragment() {
        // Required empty public constructor
    }

    public static TongDoanhThuFragment newInstance(String param1, String param2) {
        TongDoanhThuFragment fragment = new TongDoanhThuFragment();
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
        tongsach = view.findViewById(R.id.tongsach);
        tongloai = view.findViewById(R.id.tongloais);
        tongtv = view.findViewById(R.id.tongtv);
        tongpm = view.findViewById(R.id.tongpm);
        imtongsach=view.findViewById(R.id.imtongsach);
        imtongloai=view.findViewById(R.id.imloais);
        imtongtv=view.findViewById(R.id.imtongtv);
        imtongpm=view.findViewById(R.id.imtongpm);
        tongtien=view.findViewById(R.id.tongtien);
        imtongtien=view.findViewById(R.id.imtongtien);
        tongtienall=view.findViewById(R.id.tongtienall);
        imtongtienall=view.findViewById(R.id.imtongtienall);
        ngaybd=view.findViewById(R.id.ngaybd);
        ngaykt=view.findViewById(R.id.ngaykt);
        tinhdt=view.findViewById(R.id.tinhdt);

        sachDAO = new SachDAO(getContext());
        loaiSachDAO = new LoaiSachDAO(getContext());
        loginDao = new LoginDao(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> listSach = sachDAO.getAll();
        tongsach.setText("Tổng Sách : "+listSach.size());

        ArrayList<LoaiSach> listLoaiSach = loaiSachDAO.getAll();
        tongloai.setText("Tổng Loại Sách : "+listLoaiSach.size());

        ArrayList<Login> listtv = loginDao.getAll();
        tongtv.setText("Tổng Thành Viên : "+listtv.size());

        ArrayList<PhieuMuon> listpm = phieuMuonDAO.getAll();
        tongpm.setText("Tổng Phiếu Mượn : "+listpm.size());

        tongtienall.setText("Tổng Doanh Thu : "+thongKeDAO.getAll() + " VND");

        DatePickerDialog.OnDateSetListener mDatetungay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                mDay = day;
                mMonth = month;
                mYear = year;
                GregorianCalendar c = new GregorianCalendar(mDay,mMonth,mYear);
                ngaybd.setText(spfm1.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mDatedenngay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                mDay = day;
                mMonth = month;
                mYear = year;
                GregorianCalendar c = new GregorianCalendar(mDay,mMonth,mYear);
                ngaykt.setText(spfm1.format(c.getTime()));
            }
        };

        ngaybd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,mDatetungay,mDay,mMonth,mYear);
        d.show();
            }
        });
        ngaykt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,mDatedenngay,mDay,mMonth,mYear);
                d.show();
            }
        });
        tinhdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tungay1 = ngaybd.getText().toString();
                String denngay1 = ngaykt.getText().toString();
                tongtien.setText("Doanh Thu : "+thongKeDAO.getAll(tungay1,denngay1)+ " VND");
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
}