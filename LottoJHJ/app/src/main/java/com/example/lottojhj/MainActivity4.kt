package com.example.lottojhj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import androidx.annotation.IntegerRes

class MainActivity4 : AppCompatActivity() {
    // 선언뒤에 lazy가 오면 activity_main4.xml을 스캔할 때 까지 기다림
    /*$(document).ready(function() {
        $("#dg_dept").datagrid ({
            url: "XXX.jsp"
        })
     * } */
    private val runButton: Button by lazy { // Null허용하면 Button?
        findViewById(R.id.runButton)
    }

    // NumberPicker의 범위를 정해줌 > 지금은 0만 보임(움직이지 X)
    private val numberPicker: NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        // 자동채번 버튼 구현
        initRandomButton()
    }

    private fun initRandomButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()
            Log.d("MainActivity4", list.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        // 실행이 되었다가(BackStack에 머물러 있다가) 다시 소환될때 onCreate를 타느게 아니라 여기로 옴
    }

    // 채번 알고리즘
    private fun getRandomNumber(): List<Int> {
/*        String names[] = new String[3]; ↙ it
        names = new String[] {"이순신", "강감찬", "홍길동"} > apply()
        List<Integer> numberList2 =  new ArrayList<>(); > JAVA에서    */
        // 선언과 동시에 초기화
        val numberList = mutableListOf<Int>().apply {
            for(i in 1..45) {
                this.add(i)
            }
        } // end of initialized
        numberList.shuffle()
        val newList = numberList.subList(0,6)

        return newList
    } // end of getRandomNumber
}