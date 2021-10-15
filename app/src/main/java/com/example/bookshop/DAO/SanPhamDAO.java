package com.example.bookshop.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookshop.Data.CreateDatabase;

public class SanPhamDAO {

    SQLiteDatabase database;

    public SanPhamDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

}
