package com.example.bookshop.ActivityAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshop.ActivityUser.ChiTietLichSu;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.ActivityUser.lichsuActivity;
import com.example.bookshop.Adapter.HoaDonAdapter;
import com.example.bookshop.DTO.HoaDon;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HoaDonAdmin extends AppCompatActivity {

    ListView Listview_Lichsu;
    ArrayList<HoaDon> hoaDonArrayList;
    HoaDonAdapter adapter;
    LinearLayout layoutdoanhthu;
    TextView txtthongbao,title_qlhd,tongtien_HD,tongchi;
    ImageButton ibtnExit_lichsu;
    int idcthd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);
        TrangChuFragment.database = new Database(HoaDonAdmin.this,"BookShop",null,2);
        AnhXa();
        Listview_Lichsu = (ListView) findViewById(R.id.listview_danhsachhoadon_lichsu);

        hoaDonArrayList = new ArrayList<>();
        adapter = new HoaDonAdapter(HoaDonAdmin.this, R.layout.danhsach_lichsu, hoaDonArrayList);
        Listview_Lichsu.setAdapter(adapter);
        registerForContextMenu(Listview_Lichsu);
        Listview_Lichsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HoaDonAdmin.this, ChiTietLichSu.class);
                HoaDon hoaDon = HoaDonAdapter.ListHoaDon.get(i);
                idcthd = hoaDon.getIDCTHOADON();
                intent.putExtra("idcthd",idcthd);
                intent.putExtra("KEYHD", i);
                startActivity(intent);
            }
        });

        GetData();
    }

    private void AnhXa() {
        layoutdoanhthu = findViewById(R.id.layoutdoanhthu);
        layoutdoanhthu.setBackgroundResource(R.color.blue_nhat);
        title_qlhd = findViewById(R.id.title_qlhd);
        tongtien_HD = findViewById(R.id.tongtien_HD);
        title_qlhd.setText("Thống kê Doanh Thu");
        tongchi = findViewById(R.id.tongchi);
        tongchi.setText(" Tổng Doanh Thu ");
        ibtnExit_lichsu = findViewById(R.id.ibtnExit_lichsu);
        ibtnExit_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void GetData() {
        //get data
        Cursor cursor1 = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON ");
        cursor1.moveToNext();
        tongtien_HD.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(cursor1.getInt(0)) + " VNĐ"));


        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM HOADON ");
        hoaDonArrayList.clear();
        while (cursor.moveToNext())
        {
            hoaDonArrayList.add(new HoaDon(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}