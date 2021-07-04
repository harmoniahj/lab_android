package com.example.terrgym80;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(this.getClass().getName(),"onCreate");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
작업지시서
1. 툴바의 아이콘을 눌러 내비게이션 드로워를 열수 있도록 한다.
2. ActionBarDrawerToggle클래스의 새 인스턴스를 생성하고 드로워 레이아웃에
추가하는 코드를 액티비티의 onCreate()구현 드로워 토글을 생성함
@param1:현재 액티비티(this, getApplicationContext(), getContext())
@액티비티의 drawer
@액티비티의 툴바
@드로워열기, 드로워닫기
3. 드로워토글을 생성한 다음 DrawerLayout의 addDrawerListener()메소드의
파라미터로 전달해서 드로워 레이아웃으로 추가함.
4. 토글의 syncState()로 툴바의 아이콘과 드로워 상태 동기화 함.
 */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this
                        ,drawer
                        ,toolbar
                        ,R.string.nav_open_drawer
                        ,R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        //이벤트 소스와 이벤트 핸들러 연결
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getClass().getName(),"onStart");
    }
    public void resList(View v){
        Log.i(this.getClass().getName(),"resList:"+v);
        Intent intent = new Intent(this,ResListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //어떤 메뉴아이템을 선택했니?
        int id = item.getItemId();
        Log.i(this.getClass().getName(),"선택한 메뉴의 아이디 값은 ===> "+id);
        Fragment fragment = null;
        Activity activity = null;
        Intent intent = null;
        switch (id){
            case R.id.nav_pt:
                fragment = new PtFragment();
                break;
            case R.id.nav_yoga:
                fragment = new YogaFragment();
                break;
            case R.id.nav_spining:
                fragment = new SpinningFragment();
                break;
            case R.id.nav_login:
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_map:
                intent = new Intent(this, MapActivity.class);
                break;
            default: break;
        }
        if(fragment !=null){//선택한 메뉴아이템이 프래그먼트야?
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }else{//선택한 메뉴아이템이 액티비티인거야?
            startActivity(intent);
        }
        //사용자가 옵션 중 하나를 선택하면 화면을 닫아요.
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}