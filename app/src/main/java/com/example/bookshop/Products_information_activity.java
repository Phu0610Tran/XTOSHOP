package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.SanPhamDTO;

public class Products_information_activity extends AppCompatActivity {

    TextView name,price,content;
    ImageView imgHinh;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_information);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1123);
        Anhxa();
        GetDataSP();
    }

    private void Anhxa() {
        name = (TextView) findViewById(R.id.product_name_CT);
        price = (TextView) findViewById(R.id.product_content_CT);
        content = (TextView) findViewById(R.id.product_price_CT);
        imgHinh = (ImageView) findViewById(R.id.product_image_CT);
    }

    private void GetDataSP() {
        //get data
        SanPhamDTO sanPhamDTO = SanPhamDAO.sanPhamDTOList.get(id);
        String ten = sanPhamDTO.getTenSP();
        String mota = sanPhamDTO.getMotaSP();
        String gia = sanPhamDTO.getGiaSP() + " VNĐ";


        name.setText(ten);
        content.setText(mota);
        price.setText(gia);
        byte[] hinhAnh = sanPhamDTO.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);


    }
}