package com.example.bookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mbottomNavigationView;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CreateDatabase createDatabase = new CreateDatabase(this);
//        createDatabase.open();

        AnhXa();
        SetupViewPager();

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_bell:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_user:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void AnhXa(){
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void SetupViewPager() {
        ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.action_bell).setChecked(true);
                        break;
                    case 3:
                        mbottomNavigationView.getMenu().findItem(R.id.action_user).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}