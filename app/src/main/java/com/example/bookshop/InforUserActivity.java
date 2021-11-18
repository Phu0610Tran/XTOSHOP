package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bookshop.DTO.TaiKhoanDTO;
import com.example.bookshop.Fragment.TrangChuFragment;

public class InforUserActivity extends AppCompatActivity {

    EditText edt_Taikhoan, edt_Sdt, edt_Email, edt_Diachi;
    Button btn_Capnhat;
    ImageButton ibtn_Exit;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);

        Anhxa();
        GetData();

    }

    private void GetData() {
        //get data
        int id = LoginActivity.taiKhoanDTO.getMATK();
        TaiKhoanDTO taiKhoanDTO = TrangChuFragment.database.Load(id);
        String tentaikhoan = taiKhoanDTO.getTENTK();
        int sdt = taiKhoanDTO.getSDT();
        String email = taiKhoanDTO.getEMAIL();
        String diachi = taiKhoanDTO.getDIACHI();

        edt_Taikhoan.setText(tentaikhoan);
        edt_Sdt.setText(String.valueOf(sdt));
        edt_Email.setText(email);
        edt_Diachi.setText(diachi);

        if (LoginActivity.taiKhoanDTO.getMATK() == -1)
        {
            Toast.makeText(this, "Bạn chưa đăng nhập !", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void Anhxa() {
        edt_Taikhoan = findViewById(R.id.edtTaikhoan);
        edt_Sdt = findViewById(R.id.edtSdt);
        edt_Email = findViewById(R.id.edtEmail);
        edt_Diachi = findViewById(R.id.edtDiachi);

        ibtn_Exit = findViewById(R.id.ibtnExit);
        ibtn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iExit = new Intent(InforUserActivity.this, HomeActivity.class);
                iExit.putExtra("ExitUser", R.id.nav_profile);
                startActivity(iExit);
            }
        });

        btn_Capnhat = findViewById(R.id.btnCapnhat);
        btn_Capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrangChuFragment.database.UPDATE(
                        edt_Taikhoan.getText().toString().trim(),
                        Integer.parseInt(edt_Sdt.getText().toString().trim()),
                        edt_Email.getText().toString().trim(),
                        edt_Diachi.getText().toString().trim(),
                        LoginActivity.taiKhoanDTO.getMATK()
                );
                GetData();
                Toast.makeText(InforUserActivity.this,"Cập nhật thành công !", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(UserInformationActivity.this, Login.class));
            }
        });
    }
}