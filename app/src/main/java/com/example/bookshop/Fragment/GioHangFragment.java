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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DAO.GioHangAdapter;
import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.LoginActivity;
import com.example.bookshop.Products_information_activity;
import com.example.bookshop.R;

import java.util.ArrayList;


public class GioHangFragment extends Fragment {

    private View view;
    ListView Listview_SanPham;
    ArrayList<GioHang> sanPhamArrayList;
    GioHangAdapter adapter;
    TextView txtthongbao;


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
        AnhXa();
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

    private void AnhXa() {
        txtthongbao = (TextView) view.findViewById(R.id.thongbaogiohang);
    }

    private void GetData() {
        //get data

        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM GIOHANG WHERE IDTK = " + LoginActivity.taiKhoanDTO.getMATK());
        sanPhamArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamArrayList.add(new GioHang(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            ));
        }
        adapter.notifyDataSetChanged();


        if (LoginActivity.taiKhoanDTO.getMATK() == -1)
        {
            txtthongbao.setText(" Bạn hãy đăng nhập để có thể mua hàng !");
        }else if (sanPhamArrayList.isEmpty()){
            txtthongbao.setText(" Bạn chưa mua hàng !");
        }
        Toast.makeText(getActivity(), "id cua may la : "+ LoginActivity.taiKhoanDTO.getMATK(), Toast.LENGTH_SHORT).show();
    }
}