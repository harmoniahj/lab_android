package com.example.watchfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    boolean running = false;
    // onStop메소드가 호출되기 전에 스톱워치가 실행 중인지 알 수 있도록 새로운 변수가 필요
    // > 엑티비티가 다시 보였을 때 스톱워치의 상태로 설정해야 할지 알 수 있으므로...
    boolean wasRunning = false; // 새로운 변수에 onStop메소드가 호출된순간 스톱워치가 실행 중이었는지 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();

        Button btn_start = findViewById(R.id.btn_start);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            // Activity가 다시 생성되면 wasRunning변수의 상태를 복원함
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        Button btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        Button btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });
    }

    // 스톱웡치가 실행 중 이었다면 다시 실행하도록 함
    public void onStop() {
        super.onStop(); // onStop메소드가 호출되었을 때스톱워치가 실행 중 이었는지 저장
        if(wasRunning) { // 적재적소에 필요한 조건을 수렴할 때 변수 초기화 잘 하기!!
            running = true;
        }
    }

    public void onSaveInstanceState(Bundle saveInsatanceState) {
        super.onSaveInstanceState(saveInsatanceState);
        
        saveInsatanceState.putInt("seconds", seconds);
        saveInsatanceState.putBoolean("running", running);
        saveInsatanceState.putBoolean("wasRunning", wasRunning);
    }

    private void runTimer() {
        final TextView tv_time = findViewById(R.id.tv_time);
        // Handler사용하면 특정코드를 미래의 특정시점에 사용할 수 있음.
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours,minutes,secs);
                tv_time.setText(time);

                if(running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    } // runTimer
}