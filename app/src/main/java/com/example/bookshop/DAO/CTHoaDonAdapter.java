package com.example.bookshop.DAO;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.DTO.CTHoaDon;
import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.HoaDon;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CTHoaDonAdapter extends BaseAdapter {
    private Fragment context;
    private int layout;
    public static List<CTHoaDon> ListCTHoaDon;
    int id;



    public CTHoaDonAdapter(Fragment context, int layout, List<CTHoaDon> ListCTHoaDon) {
        this.context = context;
        this.layout = layout;
        this.ListCTHoaDon = ListCTHoaDon;
    }


    @Override
    public int getCount() {
        return ListCTHoaDon.size();
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
        ImageButton btncong,btntru;

    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        GioHangAdapter.ViewHolder holder;

        if (view == null) {
            holder = new GioHangAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_TenSP = (TextView) view.findViewById(R.id.textviewTenCustom);
            holder.txt_GiaSP = (TextView) view.findViewById(R.id.textviewTTCustom);
            holder.txt_SLSP = (TextView) view.findViewById(R.id.textviewSLCustom);
            view.setTag(holder);
        } else {
            holder = (GioHangAdapter.ViewHolder) view.getTag();
        }

        CTHoaDon ctHoaDon = ListCTHoaDon.get(i);
        return view;
    }

}
