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

import com.example.bookshop.DAO.GioHangAdapter;
import com.example.bookshop.DTO.GioHang;
import com.example.bookshop.DTO.TaiKhoanDTO;
import com.example.bookshop.Fragment.GioHangFragment;
import com.example.bookshop.Fragment.CFragment;
import com.example.bookshop.Fragment.GopYFragment;
import com.example.bookshop.Fragment.JavaFragment;
import com.example.bookshop.Fragment.PythonFragment;
import com.example.bookshop.Fragment.WebFragment;
import com.example.bookshop.Fragment.AndroidFragment;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Fragment.UserFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_ANDROID = 2;
    private static final int FRAGMENT_CONTACT = 3;
    private static final int FRAGMENT_FEEDBACK = 4;

    private static final int FRAGMENT_C = 5;
    private static final int FRAGMENT_WED = 6;
    private static final int FRAGMENT_JAVA = 7;
    private static final int FRAGMENT_PYTHON = 8;
    private static final int FRAGMENT_NGUOIDUNG = 9;
    private static final int FRAGMENT_GIOHANG = 10;


    private int currentFragment = FRAGMENT_HOME;

    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Drawer

    TextView txt_TenTaiKhoan,txt_count;


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
        txt_TenTaiKhoan.setText(LoginActivity.taiKhoanDTO.getTENTK());
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
        }else if (id == R.id.nav_contact) {
            if (FRAGMENT_CONTACT != currentFragment) {
                replaceFragment(new TrangChuFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_Feedback) {
            if (FRAGMENT_FEEDBACK != currentFragment) {
                replaceFragment(new GopYFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_Android) {
            if (FRAGMENT_ANDROID != currentFragment) {
                replaceFragment(new AndroidFragment());
                currentFragment = FRAGMENT_ANDROID;
            }
        }else if (id == R.id.nav_NgonnguC) {
            if (FRAGMENT_C != currentFragment) {
                replaceFragment(new CFragment());
                currentFragment = FRAGMENT_C;
            }
        }else if (id == R.id.nav_Web) {
            if (FRAGMENT_WED != currentFragment) {
                replaceFragment(new WebFragment());
                currentFragment = FRAGMENT_WED;
            }
        }else if (id == R.id.nav_Java) {
            if (FRAGMENT_JAVA != currentFragment) {
                replaceFragment(new WebFragment());
                currentFragment = FRAGMENT_JAVA;
            }

        }else if (id == R.id.nav_Python) {
            if (FRAGMENT_PYTHON != currentFragment) {
                replaceFragment(new PythonFragment());
                currentFragment = FRAGMENT_PYTHON;
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