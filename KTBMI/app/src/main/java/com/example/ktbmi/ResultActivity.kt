package com.example.ktbmi

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

/* new 예약어 X, 함수 뒤에 제네릭 사용
 * 객체생성 후 즉시 초기화 할때 apply() {} let()... RecyclerView, XXXAdapter, ViewHolder
 * $XXX변수 호출
 * getIntent().getIntExtra, getIntent().getStringExtra
 * switch문 대신 when!!, for문 > 프로시저 or VisualBasic문법임 */
class ResultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // xml, RecyclerView root, LayoutManager
        setContentView(R.layout.activity_result)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)
        Log.d("ResultActivity", "height: $height, weight: $weight")

        val bmi = weight / (height / 100.0).pow(2.0)
        val resultText = when {
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        val bmiResultTextView = findViewById<TextView>(R.id.bmiResultTextView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        bmiResultTextView.text = bmi.toString()
        resultTextView.text = resultText
    }
}