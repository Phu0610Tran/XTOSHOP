package com.example.bookshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.ActivityUser.Products_information_activity;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHoder> implements Filterable {
    public static List<SanPhamDTO> mListUsers;
    private List<SanPhamDTO> mListUsersOld;
    private Context mContext;
    public UserAdapter(Context context, List<SanPhamDTO> mListUsers){
        this.mContext = context;
        this.mListUsers = mListUsers;
        this.mListUsersOld = mListUsers;
    }
    @NonNull
    @Override
    public UserViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timkiem, parent, false);
        return new UserViewHoder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHoder holder, int position) {
        SanPhamDTO sanPhamDTO = mListUsers.get(position);
        if(sanPhamDTO == null){
            return;
        }
        holder.TenSP_TK.setText("Tên : " + sanPhamDTO.getTenSP());
        holder.GiaSP_TK.setText("Giá : " + String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPhamDTO.getGiaSP())) + " VNĐ");
        holder.SLSP_TK.setText(" Số Lượng : " + sanPhamDTO.getSl_SP());
        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = sanPhamDTO.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.imgHinh_TK.setImageBitmap(bitmap);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Products_information_activity.class);
//                intent.putExtra("ID_VIDEO",video.getIDVD());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListUsers != null){
            return mListUsers.size();
        }
        return 0;
    }
    public class UserViewHoder extends RecyclerView.ViewHolder{
        private ImageView imgHinh_TK;
        private TextView TenSP_TK,GiaSP_TK;
        private TextView SLSP_TK;
        private LinearLayout layoutItem;
        public UserViewHoder(@NonNull View itemView) {
            super(itemView);
            SLSP_TK = itemView.findViewById(R.id.SLSP_TK);
            imgHinh_TK = itemView.findViewById(R.id.imgHinh_TK);
            TenSP_TK = itemView.findViewById(R.id.TenSP_TK);
            GiaSP_TK = itemView.findViewById(R.id.GiaSP_TK);
            layoutItem = itemView.findViewById(R.id.layoutItem);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListUsers = mListUsersOld;
                }else {
                    List<SanPhamDTO> list = new ArrayList<>();
                    for(SanPhamDTO sanPhamDTO : mListUsersOld){
                        if(sanPhamDTO.getTenSP().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(sanPhamDTO);
                        }
                    }
                    mListUsers = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUsers;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListUsers = (List<SanPhamDTO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
