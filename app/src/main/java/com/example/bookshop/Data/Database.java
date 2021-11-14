package com.example.bookshop.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.Editable;

import androidx.annotation.Nullable;

import com.example.bookshop.DTO.SanPhamDTO;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Truy vấn không trả kết quả: INSERT, CREATE, UPDATE, DELETE, ...
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Truy vấn có trả kết quả: SELECT
    public Cursor Getdata(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public boolean SPChuaCoTrongGH(int IDTK,int IDSP){
        Cursor tro = Getdata("SELECT * FROM GIOHANG WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP );
        while (tro.moveToNext()) {
            return false;
        }
        return true;
    }

    //--------------------------------------------
    public void INSERT_DOAN(byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO GIOHANG VALUES(null,?,null,null,null,null,null,null)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.bindBlob(1,hinh);

        statement.executeInsert();
    }
    //--------------------------------------------
    public void SPGH(int IDTK,byte[] hinh, int IDSP, String TenSP, int Soluong, int thanhtien){
        if(SPChuaCoTrongGH(IDTK, IDSP)){
            QueryData("INSERT INTO " + CreateDatabase.tbl_GIOHANG +
                    " ( "
                    + CreateDatabase.tbl_GIOHANG_IDTK + " , "
                    + CreateDatabase.tbl_GIOHANG_HINHANH + " , "
                    + CreateDatabase.tbl_GIOHANG_IDSP + " , "
                    + CreateDatabase.tbl_GIOHANG_TENSANPHAM + " , "
                    + CreateDatabase.tbl_GIOHANG_SOLUONG + " , "
                    + CreateDatabase.tbl_GIOHANG_THANHTIEN
                    + " ) VALUES ( " + IDTK +" , " + null + " , " + IDSP+" , '" + TenSP + "' , " + Soluong + " , "
                    + thanhtien + " ) ");
            //------------------------------

            SQLiteDatabase database = getWritableDatabase();
            String sql = "UPDATE GIOHANG SET HinhAnh = ? WHERE IDTK="+ IDTK + " AND IDSP=" + IDSP ;
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindBlob(1,hinh);
            statement.executeInsert();



        }
        else {
            QueryData("UPDATE " + CreateDatabase.tbl_GIOHANG + " SET "
                    + CreateDatabase.tbl_GIOHANG_SOLUONG + " = "+CreateDatabase.tbl_GIOHANG_SOLUONG + " + " + Soluong + " , "
                    + CreateDatabase.tbl_GIOHANG_THANHTIEN + " = " + CreateDatabase.tbl_GIOHANG_THANHTIEN + " + " + thanhtien
                    + " WHERE " + CreateDatabase.tbl_GIOHANG_IDTK + " = " + IDTK+ " AND "
                    + CreateDatabase.tbl_GIOHANG_IDSP + " = " + IDSP)
                    ;
        }
    }
    public SanPhamDTO getSoLuong(int IDSP){
        Cursor cursor = Getdata("SELECT * FROM SANPHAM WHERE IDSP = " + IDSP );
        while (cursor.moveToNext()) {
            return new SanPhamDTO(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );

        }
        return null;
    }

    public void UPDATE_SOLUONG(int IDTK,int IDSP,int Soluong, int thanhtien)
    {
        QueryData("UPDATE " + CreateDatabase.tbl_GIOHANG + " SET "
                + CreateDatabase.tbl_GIOHANG_SOLUONG + " = "+ CreateDatabase.tbl_GIOHANG_SOLUONG + " + " + Soluong + " , "
                + CreateDatabase.tbl_GIOHANG_THANHTIEN + " = " + CreateDatabase.tbl_GIOHANG_THANHTIEN + " + " + thanhtien
                + " WHERE " + CreateDatabase.tbl_GIOHANG_IDTK + " = " + IDTK+ " AND "
                + CreateDatabase.tbl_GIOHANG_IDSP + " = " + IDSP)
        ;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
