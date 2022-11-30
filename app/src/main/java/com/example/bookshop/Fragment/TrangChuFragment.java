package com.example.bookshop.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Models.SanPhamDTO;
import com.example.bookshop.Data.Database;
import com.example.bookshop.ActivityUser.Products_information_activity;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TrangChuFragment extends Fragment {

    private View view;
    ViewFlipper viewFlipper;
    public static Database database;
    GridView gridView_SanPham;
    RecyclerView recyclerView;
    ArrayList<SanPhamDTO> sanPhamDTOArrayList;
    SanPhamAdapter adapter;



    public TrangChuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        database = new Database(getActivity(),"BookShop",null,2);
//        database.QueryData("CREATE TABLE IF NOT EXISTS DoAn(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
//                ", Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");
        Anhxa();
        ActionViewFlipper();
        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewSanPham);
        sanPhamDTOArrayList = new ArrayList<>();
        adapter = new SanPhamAdapter(TrangChuFragment.this, R.layout.product_layout, sanPhamDTOArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Products_information_activity.class);


                intent.putExtra("id",i);
                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();


//        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( SOLUONG ) FROM GIOHANG WHERE IDTK = "
//                + LoginActivity.taiKhoanDTO.getMATK());
//        cursor.moveToNext();
//        int tong = cursor.getInt(0);
//        txt_count_giohang.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(tong) + " VNĐ"));


        return view;
    }

    private void Anhxa() {
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlippermanhinhchinh);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://www.rollingstone.com/wp-content/uploads/2021/07/metallica-elevated-group-e1627090436110.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2022/04/02/1423499/huong-dan-order-giay-converse-o-nuoc-ngoai-cuc-de-202204020114320272.jpg");
        mangquangcao.add("https://images.dsw.com/is/image/DSWShoes/P221401_header_converse-mobile?impolicy=qlt-medium&imwidth=768&imdensity=1");
        mangquangcao.add("https://giaysneaker.com.vn/public/media//thumb/af1_vi%E1%BB%81n_xam/nike-air-force-1-trang-vien-gold-like-auth_(11)-570x570.jpg");

        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private void GetData() {
        //get data
        Cursor cursor = database.Getdata("SELECT * FROM SANPHAM WHERE SPNEW = 1");
        sanPhamDTOArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamDTOArrayList.add(new SanPhamDTO(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}