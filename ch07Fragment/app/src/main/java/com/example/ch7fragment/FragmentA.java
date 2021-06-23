package com.example.ch7fragment;

import android.os.Bundle;
//안드로이드의 지원 라이브러리의 Fragment를 사용함.
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment {
    //프래그먼트 레이아웃이 필요할 때 안드로이드가 호출하는 메소드
    //아래 메소드는 선택사항이지만 레이아웃을 포함하는 Fragment에서는
    //이 메소드를 구현해야 함.
    //프래그먼트 사용자의 인터페이스를 가리키는 View객체를 반환함.
    /*
    @param1:LayoutInflater는 프래그먼트 레이아웃을 인플레이트 하는데 사용함
    XML뷰를 자바 객체로 변환함.
    @param2:ViewGroup은  프래그먼트를 포함할 액티비티의 레이아웃을 가리킴
    @param3:Bundle은 프래그먼트 상태를 저장했다가 다시 살려낼 때 사용함.

     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //프래그먼트가 어떤 레이아웃을 사용하는지 안드로이드에 알려줌.
        return inflater.inflate(R.layout.fragment_a, container, false);
    }
}