package com.example.workout80;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDetail(View v){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("sname","이순신");
        startActivity(intent);
    }

}