package com.example.watchfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int seconds = 0;
    boolean running = false;
    //onStop메소드를 호출하기 전에 스톱워치가 실행 중인지 알 수 있도록
    //warRunning이라는 새로운 변수가 필요함. 그래야 액티비티가 다시 보였을 때
    //스톱워치의 상태를 실행 상태로 설정해야 할 지 알수 있으니까....
    //wasRuning이라는 새로운 변수에 onStop메소드가 호출된 순간 스톱워치가
    //실행 중이었는지 저장함.
    boolean wasRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_start = findViewById(R.id.btn_start);
        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            //액티비티가 다시 생성되면 wasRunning변수의 상태를 복원
            wasRunning = savedInstanceState.getBoolean("warRunning");
        }
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                running = true;
            }
        });
        Button btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
        Button btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });
        runTimer();
    }
    //스톱워치가 실행 중 이었다면 스톱워치를 다시 실행하도록 onStart()를 구현해요.
    public void onStart(){
        super.onStart();
        //적재적소에 필요한 조건을 수렴할 때 변수 초기화 잘 하기
        if(wasRunning){
            running = true;
        }
    }
    public void onStop(){
        super.onStop();
        //onStop메소드가 호출되었을 때 스톱워치가 실행 중이었는지 저장해요
        wasRunning = running;
        running = false;
    }
    //이전 상태를 저장하는 코드가 필요해
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
        saveInstanceState.putBoolean("wasRunning", wasRunning);
    }
    private void runTimer(){
        final TextView tv_time = findViewById(R.id.tv_time);
        //Handler사용하면 특정코드를 미래의 특정시점에 사용할 수 있음.
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault()
                        , "%d:%02d:%02d",hours,minutes,secs);
                tv_time.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }//runTimer
}