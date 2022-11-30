package com.example.bookshop.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.Models.TaiKhoanDTO;
import com.example.bookshop.R;

import java.util.List;

public class TaiKhoanAdminAdapter extends BaseAdapter {
    SQLiteDatabase database;

    private Fragment context;
    private int layout;
    public static List<TaiKhoanDTO> taiKhoanDTOList;
    int id;


    public TaiKhoanAdminAdapter(Fragment context, int layout, List<TaiKhoanDTO> taiKhoanDTOList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanDTOList = taiKhoanDTOList;
    }

    @Override
    public int getCount() {
        return taiKhoanDTOList.size();
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
        TextView txtTentk_qltk,txt_mk_qltk,txt_sdt_qltk,
                txt_ngaysinh_qltk,txt_loaitk_qltk,txt_diachi_qltk,txt_email_qltk;
        ImageView img_user_qltk;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        TaiKhoanAdminAdapter.ViewHolder holder;

        if (view == null){
            holder = new TaiKhoanAdminAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTentk_qltk = (TextView) view.findViewById(R.id.txtTentk_qltk);
            holder.txt_mk_qltk = (TextView) view.findViewById(R.id.txt_mk_qltk);
            holder.txt_sdt_qltk = (TextView) view.findViewById(R.id.txt_sdt_qltk);
            holder.txt_ngaysinh_qltk = (TextView) view.findViewById(R.id.txt_ngaysinh_qltk);
            holder.txt_loaitk_qltk = (TextView) view.findViewById(R.id.txt_loaitk_qltk);
            holder.txt_diachi_qltk = (TextView) view.findViewById(R.id.txt_diachi_qltk);
            holder.txt_email_qltk = (TextView) view.findViewById(R.id.txt_email_qltk);
            holder.img_user_qltk = (ImageView) view.findViewById(R.id.img_user_qltk);

            view.setTag(holder);
        } else {
            holder = (TaiKhoanAdminAdapter.ViewHolder) view.getTag();
        }

        TaiKhoanDTO taiKhoanDTO = taiKhoanDTOList.get(i);
        holder.txtTentk_qltk.setText("Tên : " + taiKhoanDTO.getTENTK());
        holder.txt_mk_qltk.setText(String.valueOf(taiKhoanDTO.getMATKHAU()));
        holder.txt_sdt_qltk.setText("SDT : " + String.valueOf(taiKhoanDTO.getSDT()));
        holder.txt_ngaysinh_qltk.setText(taiKhoanDTO.getNGAYSINH());
        holder.txt_loaitk_qltk.setText(String.valueOf(taiKhoanDTO.getMAQUYEN()));
        holder.txt_diachi_qltk.setText(taiKhoanDTO.getDIACHI());
        holder.txt_email_qltk.setText(taiKhoanDTO.getEMAIL());
        id = taiKhoanDTO.getMATK();

        // chuyen byte[] -> ve bitmap
        if (taiKhoanDTO.getHINHANH() == null){
            holder.img_user_qltk.setImageResource(R.drawable.user);
        }else {
            byte[] hinhAnh = taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            holder.img_user_qltk.setImageBitmap(bitmap);
        }
        return view;
    }
}
