package com.example.bookshop.ActivityUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Adapter.TimKiemAdapter;
import com.example.bookshop.Models.SanPhamDTO;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class Products_information_activity extends AppCompatActivity {

    SanPhamDTO sanPhamDTO;
    TextView name,price,content;
    ImageView imgHinh;
    EditText editTextSL;
    Button btnaddcart;
    ImageButton btn_quaylai;
    int id,idtk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_information);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        idtk = intent.getIntExtra("idtk",2);
//        Toast.makeText(Products_information_activity.this, " sss : " + id + idtk, Toast.LENGTH_SHORT).show();
        Anhxa();

        GetDataSP();
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products_information_activity.this,HomeActivity.class));
            }
        });
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                int SL = Integer.parseInt(editTextSL.getText().toString());

                if(idtk==2){
                    sanPhamDTO = SanPhamAdapter.sanPhamDTOList.get(id);
                }else
                {
                    sanPhamDTO = TimKiemAdapter.sanPhamDTOList.get(idtk);
                }


                if(LoginActivity.taiKhoanDTO.getMATK() == -1)
                {
                    Toast.makeText(Products_information_activity.this, "Bạn phải đăng nhập để mua hàng !", Toast.LENGTH_SHORT).show();
                }else if( SL > sanPhamDTO.getSl_SP() ){
                    Toast.makeText(Products_information_activity.this, "Hàng trong kho chỉ còn : " + (sanPhamDTO.getSl_SP()- 1) + " sản phẩm ", Toast.LENGTH_SHORT).show();

                }else if(  SL == 0 ){
                    Toast.makeText(Products_information_activity.this, " Số lượng không hợp lệ  " , Toast.LENGTH_SHORT).show();

                }
                else
                {
                    TrangChuFragment.database.SPGH(
                            LoginActivity.taiKhoanDTO.getMATK(),
                            hinhAnh,
                            sanPhamDTO.getMaSP(),
                            sanPhamDTO.getTenSP(),
                            SL,
                            SL * sanPhamDTO.getGiaSP()
                    );
                    Toast.makeText(getApplicationContext()," Đã thêm vào giỏ hàng !",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void Anhxa() {
        name = (TextView) findViewById(R.id.product_name_CT);
        price = (TextView) findViewById(R.id.product_content_CT);
        content = (TextView) findViewById(R.id.product_price_CT);
        imgHinh = (ImageView) findViewById(R.id.product_image_CT);
        btnaddcart= (Button) findViewById(R.id.btnadd_addtocart_CT);
        editTextSL = (EditText) findViewById(R.id.product_SL_CT);
        btn_quaylai = (ImageButton) findViewById(R.id.btn_quaylai);
    }

    private void GetDataSP() {
        //get data
        if(idtk==2){
            sanPhamDTO = SanPhamAdapter.sanPhamDTOList.get(id);
        }else
        {
            sanPhamDTO = TimKiemAdapter.sanPhamDTOList.get(idtk);
        }
        String ten = sanPhamDTO.getTenSP();
        String mota = sanPhamDTO.getMotaSP();
        name.setText(ten);
        content.setText(mota);
        price.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPhamDTO.getGiaSP()) + " VNĐ"));
        byte[] hinhAnh = sanPhamDTO.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);


    }
}