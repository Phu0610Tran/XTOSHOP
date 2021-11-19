package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshop.DAO.HoaDonAdapter;
import com.example.bookshop.DTO.HoaDon;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.TrangChuFragment;

import java.util.ArrayList;

public class lichsuActivity extends AppCompatActivity {

    ListView Listview_Lichsu;
    ArrayList<HoaDon> hoaDonArrayList;
    HoaDonAdapter adapter;
    TextView txtthongbao;
    int idcthd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);
        TrangChuFragment.database = new Database(lichsuActivity.this,"BookShop",null,2);
        AnhXa();
        Listview_Lichsu = (ListView) findViewById(R.id.listview_danhsachhoadon_lichsu);

        hoaDonArrayList = new ArrayList<>();
        adapter = new HoaDonAdapter(lichsuActivity.this, R.layout.danhsach_lichsu, hoaDonArrayList);
        Listview_Lichsu.setAdapter(adapter);
        registerForContextMenu(Listview_Lichsu);
        Listview_Lichsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(lichsuActivity.this,ChiTietLichSu.class);
                HoaDon hoaDon = HoaDonAdapter.ListHoaDon.get(i);
                idcthd = hoaDon.getIDCTHOADON();
                intent.putExtra("idcthd",idcthd);
                startActivity(intent);
            }
        });

        GetData();
    }
    private void AnhXa() {
        txtthongbao = (TextView) findViewById(R.id.thongbaolichsu);
    }

    private void GetData() {
        //get data

        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM HOADON WHERE IDTAIKHOAN = " + LoginActivity.taiKhoanDTO.getMATK());
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


        if (LoginActivity.taiKhoanDTO.getMATK() == -1)
        {
            txtthongbao.setText(" Bạn hãy đăng nhập để có thể xem hóa đơn !");
        }else if (hoaDonArrayList.isEmpty()){
            txtthongbao.setText(" Bạn chưa có hóa đơn !");
        }
    }
}