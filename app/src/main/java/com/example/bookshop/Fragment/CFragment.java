package com.example.bookshop.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DAO.SachKhoaHocDAO;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.R;

import java.util.ArrayList;


public class CFragment extends Fragment {

    private View view;

    private static Database database;
    GridView gridView_SanPham;
    ArrayList<SanPhamDTO> sanPhamDTOArrayList;
    SachKhoaHocDAO adapter;


    public CFragment() {
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
        adapter = new SachKhoaHocDAO(CFragment.this, R.layout.product_layout, sanPhamDTOArrayList);
        gridView_SanPham.setAdapter(adapter);
        registerForContextMenu(gridView_SanPham);

        GetData();

        return view;
    }

    private void GetData() {
        //get data
        Cursor cursor = database.Getdata("SELECT * FROM SANPHAM WHERE IDDANHMUC = 1");
        while (cursor.moveToNext())
        {
            sanPhamDTOArrayList.add(new SanPhamDTO(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}