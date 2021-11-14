package com.example.bookshop.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Data.CreateDatabase;
import com.example.bookshop.Fragment.LoginFragment;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.LoginActivity;
import com.example.bookshop.R;

import java.util.List;

public class GioHangAdapter extends BaseAdapter {

    SQLiteDatabase database;

    private Fragment context;
    private int layout;
    public static List<GioHang> sanPhamGioHangList;
    int id;



    public GioHangAdapter(Fragment context, int layout, List<GioHang> sanPhamGioHangList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamGioHangList = sanPhamGioHangList;
    }


    @Override
    public int getCount() {
        return sanPhamGioHangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        TextView txt_TenSP, txt_GiaSP, txt_SLSP,txt_count;
        ImageView img_HinhAnh;
        ImageButton btncong,btntru;
        int soluong;

    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_TenSP = (TextView) view.findViewById(R.id.textviewTenCustom);
            holder.txt_GiaSP = (TextView) view.findViewById(R.id.textviewTTCustom);
            holder.txt_SLSP = (TextView) view.findViewById(R.id.textviewSLCustom) ;
            holder.img_HinhAnh = (ImageView) view.findViewById(R.id.imageHinhCustom);
            holder.txt_count = (TextView) view.findViewById(R.id.count_giohang);
            holder.btncong= (ImageButton) view.findViewById(R.id.cong_giohang);
            holder.btntru= (ImageButton) view.findViewById(R.id.tru_giohang);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        GioHang gioHang = sanPhamGioHangList.get(i);
         SanPhamDTO sanPhamDTO = TrangChuFragment.database.getSoLuong(gioHang.getIDSP());
        holder.btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getActivity(), "ssssssssss"+ sanPhamDTO.getSl_SP(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.soluong = gioHang.getSOLUONG();
        holder.btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.soluong = holder.soluong + 1;
                if(holder.soluong >= sanPhamDTO.getSl_SP())
                {
                    Toast.makeText(context.getActivity(), "Sản phẩm chỉ còn lại : " + gioHang.getSOLUONG(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context.getActivity(), "Sản phẩm " +  holder.soluong, Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.txt_TenSP.setText(gioHang.getTENSANPHAM());
        holder.txt_GiaSP.setText(String.valueOf(gioHang.getTHANHTIEN()) + "VNĐ" );
        holder.txt_SLSP.setText(String.valueOf(gioHang.getSOLUONG()) );
        id = gioHang.getIDGIOHANG();

        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = gioHang.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.img_HinhAnh.setImageBitmap(bitmap);

        return view;
    }

}
