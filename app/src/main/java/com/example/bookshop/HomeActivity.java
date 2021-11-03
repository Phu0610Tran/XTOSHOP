package com.example.bookshop;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookshop.Fragment.GioHangFragment;
import com.example.bookshop.Fragment.SachKhoaHocFragment;
import com.example.bookshop.Fragment.SachMoiFragment;
import com.example.bookshop.Fragment.SachNguoiLonFragment;
import com.example.bookshop.Fragment.SachVanHocFragment;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Fragment.UserFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_SACHVANHOC = 2;
    private static final int FRAGMENT_SACHKHOAHOC = 3;
    private static final int FRAGMENT_SACHNGUOILON = 4;
    private static final int FRAGMENT_SACHMOI = 5;
    private static final int FRAGMENT_NGUOIDUNG = 6;
    private static final int FRAGMENT_GIOHANG = 7;

    private int currentFragment = FRAGMENT_HOME;

    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Drawer

    TextView txt_TenTaiKhoan;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AnhXa();
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

    private void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        // Drawer

        replaceFragment(new TrangChuFragment());
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
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Remember", "Failed");
            editor.putString("token", null);
            editor.apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (id == R.id.nav_home) {
            if (FRAGMENT_HOME != currentFragment) {
                replaceFragment(new TrangChuFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_Vanhoc) {
            if (FRAGMENT_SACHVANHOC != currentFragment) {
                replaceFragment(new SachVanHocFragment());
                currentFragment = FRAGMENT_SACHVANHOC;
            }
        }else if (id == R.id.nav_Khoahoc) {
            if (FRAGMENT_SACHKHOAHOC != currentFragment) {
                replaceFragment(new SachKhoaHocFragment());
                currentFragment = FRAGMENT_SACHKHOAHOC;
            }
        }else if (id == R.id.nav_Nguoilon) {
            if (FRAGMENT_SACHNGUOILON != currentFragment) {
                replaceFragment(new SachNguoiLonFragment());
                currentFragment = FRAGMENT_SACHNGUOILON;
            }
        }else if (id == R.id.nav_Moi) {
            if (FRAGMENT_SACHMOI != currentFragment) {
                replaceFragment(new SachMoiFragment());
                currentFragment = FRAGMENT_SACHMOI;
            }
        }else if (id == R.id.nav_profile) {
            if (FRAGMENT_NGUOIDUNG != currentFragment) {
                replaceFragment(new UserFragment());
                currentFragment = FRAGMENT_NGUOIDUNG;
            }
        }else if (id == R.id.nav_cart) {
            if (FRAGMENT_GIOHANG != currentFragment) {
                replaceFragment(new GioHangFragment());
                currentFragment = FRAGMENT_GIOHANG;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void  replaceFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}