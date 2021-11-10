package com.example.bookshop.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DAO.GioHangAdapter;
import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Products_information_activity;
import com.example.bookshop.R;

import java.util.ArrayList;


public class GioHangFragment extends Fragment {

    private View view;
    ListView Listview_SanPham;
    ArrayList<GioHang> sanPhamArrayList;
    GioHangAdapter adapter;

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        TrangChuFragment.database = new Database(getActivity(),"BookShop",null,2);
//        database.QueryData("CREATE TABLE IF NOT EXISTS DoAn(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
//                ", Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");

        Listview_SanPham = (ListView) view.findViewById(R.id.listview_danhsachsp_gohang);

        sanPhamArrayList = new ArrayList<>();
        adapter = new GioHangAdapter(GioHangFragment.this, R.layout.products_giohang, sanPhamArrayList);
        Listview_SanPham.setAdapter(adapter);
//        Listview_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), Products_information_activity.class);
//
//
//                intent.putExtra("id",i);
//                startActivity(intent);
//
//            }
//        });
        registerForContextMenu(Listview_SanPham);

        GetData();
        return view;
    }
    private void GetData() {
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM GIOHANG");
        sanPhamArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamArrayList.add(new GioHang(
                    cursor.getInt(0),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}