package com.example.bookshop.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.bookshop.Fragment_Admin.QLGopyFragment;
import com.example.bookshop.Fragment_Admin.QLHoadonFragment;
import com.example.bookshop.Fragment_Admin.QLSanphamFragment;
import com.example.bookshop.Fragment_Admin.QLTaikhoanFragment;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.R;
import com.google.android.material.navigation.NavigationView;

public class HomeAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int FRAGMENT_QLSANPHAM = 1;
    private static final int FRAGMENT_QLHOADON = 2;
    private static final int FRAGMENT_QLTAIKHOAN = 3;
    private static final int FRAGMENT_QLGOPY = 4;

    private int currentFragment = FRAGMENT_QLSANPHAM;

    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Drawer

    TextView txt_TenTaiKhoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        AnhXa();
        HienThiTen();
    }

    private void HienThiTen() {
        View view = navigationView.inflateHeaderView(R.layout.header);
        txt_TenTaiKhoan = view.findViewById(R.id.txtTennguoidung);

        Intent intent = getIntent();
        txt_TenTaiKhoan.setText(LoginActivity.taiKhoanDTO.getTENTK());
    }

    private void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layoutadmin);
        navigationView = findViewById(R.id.home_nav_viewadmin);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        // Drawer

        replaceFragment(new QLSanphamFragment());
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
        }else if (id == R.id.nav_ql_sanpham) {
            if (FRAGMENT_QLSANPHAM != currentFragment) {
                replaceFragment(new QLSanphamFragment());
                currentFragment = FRAGMENT_QLSANPHAM;
            }
        }else if (id == R.id.nav_ql_taikhoan) {
            if (FRAGMENT_QLTAIKHOAN != currentFragment) {
                replaceFragment(new QLTaikhoanFragment());
                currentFragment = FRAGMENT_QLTAIKHOAN;
            }
        }else if (id == R.id.nav_ql_hoadon) {
            if (FRAGMENT_QLHOADON != currentFragment) {
                replaceFragment(new QLHoadonFragment());
                currentFragment = FRAGMENT_QLHOADON;
            }
        }else if (id == R.id.nav_ql_gopy) {
            if (FRAGMENT_QLGOPY != currentFragment) {
                replaceFragment(new QLGopyFragment());
                currentFragment = FRAGMENT_QLGOPY;
            }
        }else if (id == R.id.nav_ql_themsanpham) {
            startActivity(new Intent(HomeAdmin.this,QL_ThemSanPham.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}

