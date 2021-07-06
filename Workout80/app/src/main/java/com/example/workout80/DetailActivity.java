package com.example.workout80;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*
액티비티는 액티비티 스택에 관리
액티비티 라이프사이클 관리
이벤트처리 방법- onClickListener, onTouchListener, onKeyListener
익명클래스 처리, 내부클래스 처리, 람다식 활용(함수형 표현식 이슈)
상태변화에 따른 값을 유지 하기 -  Bundle
액티비티에 할당된 layout 리소스에 Fragment 정적처리하기
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //상속 후에 반드시 상위 오버라이드 메소드 호출 할 것.
        super.onCreate(savedInstanceState);
        //setContentView(LinearLayout 클래스 적용);
        setContentView(R.layout.activity_detail);
    }
}