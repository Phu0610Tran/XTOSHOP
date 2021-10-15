package com.example.bookshop;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView homeRecyclerView;
    private Map<String, Integer> hashMap;
    private static int countTotalProducts;

    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Drawer

    private ProgressDialog loadingBar;
    TextView txt_TenTaiKhoan;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        countTotalProducts = 0 ;

        hashMap = new HashMap<>();

        homeRecyclerView = findViewById(R.id.home_recycler_view);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

//        loadingBar = new ProgressDialog(this);
//        loadingBar.setMessage("Loading products");
//        loadingBar.setCanceledOnTouchOutside(false);
//        loadingBar.show();


        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        // Drawer

        HienThiTen();
    }

    private void HienThiTen() {
        View view = navigationView.inflateHeaderView(R.layout.header);
        txt_TenTaiKhoan = view.findViewById(R.id.txtTennguoidung);

        Intent intent = getIntent();
        String TenTaiKhoan = intent.getStringExtra("TenTaiKhoan");
        txt_TenTaiKhoan.setText(TenTaiKhoan);
        txt_TenTaiKhoan.setTextColor(Color.WHITE);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_logout:
                SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Remember", "Thất bại");
                editor.putString("token", null);
                editor.apply();

                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_Vanhoc:
                Intent menIntent = new Intent(this, SubCategoryActivity.class);
                menIntent.putExtra("CategoryName", "Sách Văn Học");
                startActivity(menIntent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_Khoahoc:
                Intent womenIntent = new Intent(this, SubCategoryActivity.class);
                womenIntent.putExtra("CategoryName", "Sách Khoa Học");
                startActivity(womenIntent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;


            case R.id.nav_Nguoilon:
                Intent PeopleIntent = new Intent(this, SubCategoryActivity.class);
                PeopleIntent.putExtra("CategoryName", "Sách Người Lớn");
                startActivity(PeopleIntent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_Moi:
                Intent newBornIntent = new Intent(this, SubCategoryActivity.class);
                newBornIntent.putExtra("CategoryName", "Sách Mới");
                startActivity(newBornIntent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

//            case R.id.nav_profile:
//                Intent newProfileIntent = new Intent(this, UserFragment.class);
//                startActivity(newProfileIntent);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                break;

            case R.id.nav_cart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}