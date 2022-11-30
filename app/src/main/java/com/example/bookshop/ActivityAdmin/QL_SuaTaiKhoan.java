package com.example.bookshop.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookshop.Adapter.TaiKhoanAdminAdapter;
import com.example.bookshop.Models.TaiKhoanDTO;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class QL_SuaTaiKhoan extends AppCompatActivity {
    EditText edt_TenTaiKhoan_QLTK,edt_MatKhau_QLTK,edt_SDT_QLTK,edt_Email_QLTK,edt_NgaySinh_QLTK,
            edt_LoaiTK_QLTK,edt_DiaChi_QLTK;
    ImageView imageViewHinh_QLTK,quaylai_qltk;
    Button buttonAdd_QLTK,buttonHuy_QLTK;
    ImageButton imageButtonCamera_QLTK,imageButtonFolder_QLTK;
    TaiKhoanDTO taiKhoanDTO;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;
    int id,MATK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_them_tai_khoan);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1123);
        Anhxa();
        Getdata();
        quaylai_qltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonAdd_QLTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyen data image view -> mang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewHinh_QLTK.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();

                TrangChuFragment.database.UPDATE_TAIKHOAN(
                        MATK,
                        edt_TenTaiKhoan_QLTK.getText().toString().trim(),
                        edt_MatKhau_QLTK.getText().toString().trim(),
                        Integer.parseInt(edt_SDT_QLTK.getText().toString().trim()) ,
                        edt_Email_QLTK.getText().toString().trim(),
                        edt_NgaySinh_QLTK.getText().toString().trim(),
                        Integer.parseInt(edt_LoaiTK_QLTK.getText().toString().trim()),
                        edt_DiaChi_QLTK.getText().toString().trim(),
                        hinhAnh
                );

                Toast.makeText(QL_SuaTaiKhoan.this," Sửa thành công ",Toast.LENGTH_LONG).show();
                onBackPressed();

            }
        });

        imageButtonCamera_QLTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        QL_SuaTaiKhoan.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );

            }
        });

        imageButtonFolder_QLTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        QL_SuaTaiKhoan.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });

        buttonHuy_QLTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_CAMERA);
                }else
                {
                    Toast.makeText(QL_SuaTaiKhoan.this," Ban khong cho phep mo camera", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else
                {
                    Toast.makeText(QL_SuaTaiKhoan.this," Ban khong cho phep mo folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageViewHinh_QLTK.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewHinh_QLTK.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void Getdata() {
        taiKhoanDTO = TaiKhoanAdminAdapter.taiKhoanDTOList.get(id);
        MATK = taiKhoanDTO.getMATK();
        edt_TenTaiKhoan_QLTK.setText(taiKhoanDTO.getTENTK());
        edt_MatKhau_QLTK.setText(taiKhoanDTO.getMATKHAU());
        edt_SDT_QLTK.setText(String.valueOf(taiKhoanDTO.getSDT()));
        edt_Email_QLTK.setText(taiKhoanDTO.getEMAIL());
        edt_NgaySinh_QLTK.setText(taiKhoanDTO.getNGAYSINH());
        edt_LoaiTK_QLTK.setText(String.valueOf(taiKhoanDTO.getMAQUYEN()));
        edt_DiaChi_QLTK.setText(taiKhoanDTO.getDIACHI());


        if(taiKhoanDTO.getHINHANH() == null)
        {
            imageViewHinh_QLTK.setImageResource(R.drawable.user);
        }
        else
        {
            byte[] hinhAnh = taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
            imageViewHinh_QLTK.setImageBitmap(bitmap);
        }

    }
    private void Anhxa() {
        imageButtonCamera_QLTK = findViewById(R.id.imageButtonCamera_QLTK);
        quaylai_qltk = findViewById(R.id.quaylai_qltk);
        imageButtonFolder_QLTK = findViewById(R.id.imageButtonFolder_QLTK);
        edt_TenTaiKhoan_QLTK = findViewById(R.id.edt_TenTaiKhoan_QLTK);
        edt_MatKhau_QLTK = findViewById(R.id.edt_MatKhau_QLTK);
        edt_SDT_QLTK = findViewById(R.id.edt_SDT_QLTK);
        edt_Email_QLTK = findViewById(R.id.edt_Email_QLTK);
        edt_NgaySinh_QLTK = findViewById(R.id.edt_NgaySinh_QLTK);
        edt_LoaiTK_QLTK = findViewById(R.id.edt_LoaiTK_QLTK);
        edt_DiaChi_QLTK = findViewById(R.id.edt_DiaChi_QLTK);
        imageViewHinh_QLTK = findViewById(R.id.imageViewHinh_QLTK);
        buttonAdd_QLTK = findViewById(R.id.buttonAdd_QLTK);
        buttonHuy_QLTK = findViewById(R.id.buttonHuy_QLTK);

    }
}