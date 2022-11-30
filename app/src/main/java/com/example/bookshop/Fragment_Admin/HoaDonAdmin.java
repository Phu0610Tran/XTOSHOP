package com.example.bookshop.Fragment_Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.ActivityUser.ChiTietLichSu;
import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.HoaDonAdapter;
import com.example.bookshop.Models.Category;
import com.example.bookshop.Models.HoaDon;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HoaDonAdmin extends AppCompatActivity {

    Spinner spnAddTheloai_TK;
    ArrayList<Category> listCategory;
    ArrayList<Category> list;
    CategoryAdapter categoryAdapter;
    int Danhmuc;

    ListView Listview_Lichsu;
    ArrayList<HoaDon> hoaDonArrayList;
    HoaDonAdapter adapter;
    LinearLayout layoutdoanhthu;
    TextView title_qlhd,tongtien_HD,tongchi;
    ImageButton ibtnExit_lichsu;
    int idcthd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);
        TrangChuFragment.database = new Database(HoaDonAdmin.this,"BookShop",null,2);
        AnhXa();
        Listview_Lichsu = (ListView) findViewById(R.id.listview_danhsachhoadon_lichsu);
        spnAddTheloai_TK = findViewById(R.id.spnAddTheloai_TK);
        listCategory = getListCategory();
        categoryAdapter = new CategoryAdapter(HoaDonAdmin.this, R.layout.item_select, listCategory);
        spnAddTheloai_TK.setAdapter(categoryAdapter);
        Danhmuc = 0;
        spnAddTheloai_TK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Danhmuc = categoryAdapter.getItem(position).getIDcategory();
                GetData();
                GetTienAlone();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








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
        GetTien();
        GetData();
    }

    @Override
    protected void onStart() {
        GetTien();
        GetData();
        super.onStart();
    }

    private void AnhXa() {
        layoutdoanhthu = findViewById(R.id.layoutdoanhthu);
        layoutdoanhthu.setBackgroundResource(R.color.blue_nhat);
        title_qlhd = findViewById(R.id.title_qlhd);
        tongtien_HD = findViewById(R.id.tongtien_HD);
        title_qlhd.setText("Thống kê Doanh Thu");
        tongchi = findViewById(R.id.tongchi);
        tongchi.setText(" Tổng Doanh Thu: ");
        ibtnExit_lichsu = findViewById(R.id.ibtnExit_lichsu);
        ibtnExit_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void GetTien()
    {
        Cursor cursor1 = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON ");
        cursor1.moveToNext();
        tongtien_HD.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(cursor1.getInt(0)) + " VNĐ"));

    }
    private void GetTienAlone()
    {
        Cursor cursor1 = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE IDTAIKHOAN = " + Danhmuc);
        cursor1.moveToNext();
        Toast.makeText(HoaDonAdmin.this, "Tổng tiền : " + String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(cursor1.getInt(0)) + " VNĐ"), Toast.LENGTH_LONG).show();

    }
    private void GetData() {
        //get data


        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM HOADON WHERE IDTAIKHOAN = " + Danhmuc);
//        Toast.makeText(HoaDonAdmin.this, "sads : " + Danhmuc, Toast.LENGTH_SHORT).show();
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
    private ArrayList<Category> getListCategory() {

        Cursor cursor =  TrangChuFragment.database.Getdata("SELECT * FROM TAIKHOAN ");
        list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(new Category(
                            cursor.getString(1),
                            cursor.getInt(0)
                    )
            );
        }



        return list;
    }
}