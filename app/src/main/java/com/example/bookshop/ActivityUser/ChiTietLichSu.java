package com.example.bookshop.ActivityUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookshop.DAO.CTHoaDonAdapter;
import com.example.bookshop.DTO.CTHoaDon;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.util.ArrayList;

public class ChiTietLichSu extends AppCompatActivity {

    ListView Listview_Lichsu;
    ArrayList<CTHoaDon> cthoaDonArrayList;
    CTHoaDonAdapter adapter;
    int idcthd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_su);
        TrangChuFragment.database = new Database(ChiTietLichSu.this,"BookShop",null,2);

        Intent intent = getIntent();
        idcthd = intent.getIntExtra("idcthd",1123);
        Toast.makeText(ChiTietLichSu.this, "ssss : " + idcthd, Toast.LENGTH_SHORT).show();

        AnhXa();
        Listview_Lichsu = (ListView) findViewById(R.id.listview_danhsachchitiethoadon_lichsu);

        cthoaDonArrayList = new ArrayList<>();
        adapter = new CTHoaDonAdapter(ChiTietLichSu.this, R.layout.danhsachchitietlichsu, cthoaDonArrayList);
        Listview_Lichsu.setAdapter(adapter);
        registerForContextMenu(Listview_Lichsu);

        GetData();
    }
    private void AnhXa() {

    }

    private void GetData() {
        //get data

        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM CHITIETHOADON WHERE IDCTHOADON = " + idcthd);
        cthoaDonArrayList.clear();
        while (cursor.moveToNext())
        {
            cthoaDonArrayList.add(new CTHoaDon(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}