package com.example.bookshop.Fragment_Admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.bookshop.ActivityAdmin.QL_SuaSanPham;
import com.example.bookshop.ActivityUser.Products_information_activity;
import com.example.bookshop.DAO.GioHangAdapter;
import com.example.bookshop.DAO.SanPhamAdminAdapter;
import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.PythonFragment;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.util.ArrayList;

public class QLSanphamFragment extends Fragment {

    private View view;
    GridView gridView_SanPham;
    ArrayList<SanPhamDTO> sanPhamDTOArrayList;
    SanPhamAdminAdapter adapter;
    public QLSanphamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_q_l_sanpham, container, false);
        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewQLSanPham);
        sanPhamDTOArrayList = new ArrayList<>();
        adapter = new SanPhamAdminAdapter(QLSanphamFragment.this, R.layout.product_sanpham_admin, sanPhamDTOArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), QL_SuaSanPham.class);


                intent.putExtra("id",i);

                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();
        return view;
    }

    @Override
    public void onStart() {
        GetData();
        super.onStart();
    }

    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM SANPHAM ");
        sanPhamDTOArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamDTOArrayList.add(new SanPhamDTO(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(6),
                    cursor.getInt(7)
            ));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.menu_delete_item:
                SanPhamDTO sanPhamDTO = SanPhamAdminAdapter.sanPhamDTOList.get(info.position);
                TrangChuFragment.database.DELETE_SANPHAM(
                        sanPhamDTO.getMaSP()
                );

                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_LONG).show();
                GetData();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}