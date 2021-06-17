package com.example.message80;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("message");
        TextView tv_msg = findViewById(R.id.tv_msg);;
        tv_msg.setText(msg);
    }
}