package com.example.lottojhj

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity2 : AppCompatActivity() {
    private val clearButton: Button by lazy {
        findViewById(R.id.clearButton)
    }

    private val addButton: Button by lazy {
        findViewById(R.id.addButton)
    }

    // NumberPicker의 범위를 정해둠 지금은 0만 보임(움직이지도 않음)
    private val numberPicker: NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        // 자동채번 버튼 구현
        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
        }
    }

    private fun initAddButton(){
        addButton.setOnClickListener {
            // 이미 자동 초기화를 눌렀을 경우에는 초기화 후에 다시 시작해 주도록 해줌
            if(didRun){
                Toast.makeText(this, "초기화 후에 시도해 주세요", Toast.LENGTH_SHORT).show()
                // @setOnClickListener를 붙이는 이유는 안에 있는 setOnClickListener를 리턴할지
                // 밖에 있는 initAddButton을 리턴할지 모르니 알아보기 쉽게 파랑으로
                return@setOnClickListener
            }
            if(pickNumberSet.size >=6 ){
                Toast.makeText(this, "번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 이미 선택한 번호를 또 선택하는 예외가 있을 수 있으므로
            if(pickNumberSet.contains(numberPicker.value) ){
                Toast.makeText(this, "이미 선택한 번호 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //i nsert here - TextView에 선택한 숫자 출력하기
            // numberTextViewList.get()으로 가져올 수도 있지만 배열 처럼 가져올 수도 있다.
            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            // findViewById(R.id.tv).setText(numberPicker.value.toString())
            textView.text = numberPicker.value.toString()
            pickNumberSet.add(numberPicker.value)
        }
    }

    // 자동 생성 시작 버튼을 눌렀을 때
    // 기존에 추첨한 숫자가 있을 경우 그 나머지만 숫자를 계속 바꿔서 출력해야 한다.
    private fun initRunButton(){
        runButton.setOnClickListener {
            // var rbtn = it.id
            // Log.i("MainActivity","버튼 주소번지:${rbtn}")
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true
                setNumberBackground(number, textView)
            }
            // Log.d("MainActivity4", list.toString())
        }
    }

    private fun setNumberBackground(number:Int, textView: TextView) {
        when(number) {
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_pink)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_purple)
        }
    }

    override fun onStart(){
        super.onStart()
        // 실행이 되었다가 (백스택에 머물러 있다가) 다시 소환될때 onCreate를 타는게 아니라 여기로 옴
        // initRunButton()
    }

    // 채번 알고리즘
    private fun getRandomNumber():List<Int>{
        // List<Integer> numberList2 = new ArrayList<>();
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45) {
                    //이미 추첨한 숫자가 있으면 그것만큼 제외시킴
                    if(pickNumberSet.contains(i)) continue
                    this.add(i)
                } // end of for
            } // end of initialized
        numberList.shuffle()
        val newList = pickNumberSet.toList() + numberList.subList(0,6 - pickNumberSet.size)
        return newList.sorted()
    } // end of getRandomNumber

}