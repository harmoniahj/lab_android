package com.example.kosmo_erp80;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public String sname = null;
    private Intent intent = null;
    private Fragment fragment = null;
    private FragmentTransaction ft = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_resMake:
                        Toast.makeText(getApplicationContext(),"예약하기", Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, ResMakeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.item_resList:
                        Toast.makeText(getApplicationContext(),"예약목록", Toast.LENGTH_SHORT).show();
                        ft = getSupportFragmentManager().beginTransaction();//프래그먼트 추가
                        removeFragment();//프래그먼트 삭제
                        //fragment = new ResListFragment();
                        fragment = new WebViewFragment();
                        ft.add(R.id.frameLayout,fragment);
                        //ft.commitNow();
                        ft.commit();
                        return true;

                    case R.id.item_logout:
                        Toast.makeText(getApplicationContext(),"로그아웃", Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

    }

    public void removeFragment() {
        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
            if (fragment.isVisible()) {
                ft.remove(fragment).commitNow();
            }
        }
    }

}