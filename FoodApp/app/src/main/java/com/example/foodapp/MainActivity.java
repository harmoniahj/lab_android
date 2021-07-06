package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }
    
    /* 뷰 페이지를 정의 하면서 pager라는 ID 할당 > 각 뷰 페이지 Activity에서 참조할 수 있도록 반드시 ID가져야 함
     * ID가 없으면 뷰 페이지의 각 페이지에 어떤 Fragment를 표시할지 지정 지정할수 X
     * Fragment 페이저 Adapter를 사용하도록 페이지 설정하기 */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return new StoreFragment();
            }

            return null;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Home";
                case 1:
                    return "Pizza";
                case 2:
                    return "Pasta";
                case 3:
                    return "Store";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}