package com.example.workout80;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
/*
    프래그먼트를 사용하는 액티비티가 있을 경우 서로 의사소통하는 방법

 */
public class WorkoutDetailFragment extends Fragment {
    /*********************************************************************
     *  프래드먼트 레이아웃이 필요할 때 마다 안드로이드가 호출하는  onCreateView구현
     * @param inflater : XML뷰를 자바 객체로 변환
     * @param container : ViewGroup 은 프래그먼트를 포함한 액티비티의 레이아웃을
     *                  가리킴
     *  this, getApplicationContext(), getContext()
     * @param savedInstanceState :  프래그먼트의 상태를 저장했다가 다시 살려낼때 사용
     * @return View: 프래그먼트의 사용자 인터페이스를 가리키는 View객체를 반환함.
     *
     **********************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater
                           , ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_workout_detail,container,false);
        return view;
    }
}
