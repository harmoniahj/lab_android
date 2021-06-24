package com.example.tomcatconnect2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_receive = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_receive = findViewById(R.id.tv_receive);
    }

    public void loginAction(View v) {
        println("요청 보냄");
        EditText et_id = findViewById(R.id.et_id);
        String id = et_id.getText().toString();

        EditText et_pw= findViewById(R.id.et_pw);
        String pw = et_pw.getText().toString();

        // Tomcat 서버에서 전송한 문자열을 받는 변수(Text, JSON)
        String receive = null;
        try {
            LoginLogic loginLogic = new LoginLogic();
            receive = loginLogic.execute(id, pw).get();
            tv_receive.setText(receive);
        } catch(Exception e) {
            Log.i(this.getClass().getName(), "Exception : " + e.toString());
        }
        Log.i(this.getClass().getName(), "Tomcat 서버에서 전송한 문자열 : " + receive);
    }

    public void println(String data) {
        Log.i("MainActivity", data + "\n");
    }
}