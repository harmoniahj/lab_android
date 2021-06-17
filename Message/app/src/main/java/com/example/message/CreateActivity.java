package com.example.message80;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Intent intent2 = new Intent(this, MainActivity.class);
        Intent intent = Intent.createChooser(intent2, "Send Message via...");
        startActivity(intent);
    }
}