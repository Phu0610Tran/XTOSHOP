package com.example.bookshop.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookshop.Models.TaiKhoanDTO;

public class TaiKhoanDAO {
    SQLiteDatabase database;

    public TaiKhoanDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemTaiKhoan(TaiKhoanDTO taiKhoanDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN,taiKhoanDTO.getTENTK());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_MATKHAU,taiKhoanDTO.getMATKHAU());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_SDT,taiKhoanDTO.getSDT());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_EMAIL,taiKhoanDTO.getEMAIL());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_NGAYSINH,taiKhoanDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_LOAITK,1);
        long kiemtra = database.insert(CreateDatabase.tbl_TAIKHOAN, null, contentValues);
        return kiemtra;
    }


    public TaiKhoanDTO KiemTraDangNhap(String tendangnhap, String matkhau){
        String truyvan = "SELECT * FROM " + CreateDatabase.tbl_TAIKHOAN + " WHERE " + CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN + " = '" + tendangnhap
                + "' AND " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + matkhau + "'";

        Cursor cursor = database.rawQuery(truyvan, null);
        while(cursor.moveToNext()){
            return new TaiKhoanDTO(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8)

            );
        }
        return null;
//        if (cursor.getCount() != 0){
//            return true;
//        } else {
//            return false;
//        }
    }

}
