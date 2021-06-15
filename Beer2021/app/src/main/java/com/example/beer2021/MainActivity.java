package com.example.beer2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    // 버튼을 클릭하면 호출됨 
    public void findbeer(View v) {
        TextView brands = findViewById(R.id.brands); // 텍스트 부의 레퍼런스를 얻어옴\
        // 스피너의 레퍼런스를 얻어옴
        Spinner color = findViewById(R.id.color);
        // 스피너에서 선택한 항목을 얻음
        String beerType = String.valueOf(color.getSelectedItem());
        // 선택한 항목 표시
        List<String> brandList = expert.getBrands(beerType);
        StringBuilder sb = new StringBuilder();
        for(String brand:brandList) {
            sb.append(brand).append("\n");
        }
        brands.setText(sb.toString());
    }
}