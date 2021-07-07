package com.example.fcmpushnotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private TextView resultTextView() {
        return findViewById(R.id.resultTextView);
    }

    private TextView firebaseToken() {
        return findViewById(R.id.firebaseTokenText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirebase();
    }

    // 토큰 값을 얻어옴
    public void initFirebase() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    // Get new FCM registration token
                    String token = task.getResult();
                    firebaseToken().setText(token);
                }
            }
        });
    }
}