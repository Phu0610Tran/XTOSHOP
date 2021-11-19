package com.example.bookshop.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.LoginActivity;
import com.example.bookshop.Products_information_activity;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class TrangChuFragment extends Fragment {

    private View view;

    public static Database database;
    GridView gridView_SanPham;
    ArrayList<SanPhamDTO> sanPhamDTOArrayList;
    SanPhamDAO adapter;
    TextView txt_count_giohang;


    public TrangChuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        database = new Database(getActivity(),"BookShop",null,2);
//        database.QueryData("CREATE TABLE IF NOT EXISTS DoAn(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
//                ", Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");

        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewSanPham);

        sanPhamDTOArrayList = new ArrayList<>();
        adapter = new SanPhamDAO(TrangChuFragment.this, R.layout.product_layout, sanPhamDTOArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Products_information_activity.class);


                intent.putExtra("id",i);
                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();


//        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( SOLUONG ) FROM GIOHANG WHERE IDTK = "
//                + LoginActivity.taiKhoanDTO.getMATK());
//        cursor.moveToNext();
//        int tong = cursor.getInt(0);
//        txt_count_giohang.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(tong) + " VNĐ"));


        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private void GetData() {
        //get data
        Cursor cursor = database.Getdata("SELECT * FROM SANPHAM");
        sanPhamDTOArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamDTOArrayList.add(new SanPhamDTO(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}