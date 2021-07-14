 package com.example.lottojhj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.core.view.isVisible

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

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.tv_choice1),
            findViewById(R.id.tv_choice2),
            findViewById(R.id.tv_choice3),
            findViewById(R.id.tv_choice4),
            findViewById(R.id.tv_choice5),
            findViewById(R.id.tv_choice6)
        )
    }

    // 이미 자동생성 시작이라는 걸 눌러서 번호를 추가할 수 없는 생태일 수 있으니까 > 예외처리
    private var didRun = false
    
    // 1 ~ 45 중복되지 않게
    private val pckNumberSet = hashSetOf<Int>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        // 자동채번 버튼 구현
        initRandomButton()
        initAddButton()
    }

    private initClearButton() {
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
        }
    }
    
    private fun initAddButton() {
        addButton.setOnClickListener {
            // 이미 자동 초기화를 눌렀을 경우에는 초기화 후에 다시 시작해 주도록 해줌
            if(didRun) {
                Toast.makeText(this, "초기화 후에 시도해 주세요.", Toast.LENGTH_SHORT).show()
                // 밖에 있는 initAddButton을 리턴할 지 모르니까
                return@setOnClickListener
            }

            if(pickNumberSet.size >= 6) {
                Toast.makeText(this, "번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 이미 선택한 번호를 또 선택하는 예외가 있을 수 있으므로
            if(pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택한 번호 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // insert here > TextView에 선택한 숫자 출력하기
            // numberTextViewList.get()으로 사져
        }
    }

    private fun initRunButton() {
        var rbtn = it.id
        Log.i("MainActivity", "버튼 주소번지 : ${rbtn}")
        val list = getRandomNumber()
        Log.d("MainActivity4", list.toString())

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