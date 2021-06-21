package com.example.ch07fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment {

    // fragment 레이아웃이 필요할 때 안드로이드가 호출하는 메소드
    // 아래 메소드는 선택사항이지만 레이아웃을 포람라는 Fragment에서는 이 메소드 구현해야 함!!
    // fragment 사용자의 인텨페이스를 가리키는 View객체를 반환함
    
    /* @param1 > LayoutInflater : fragment 레이아웃을 인플레이트 하는데 사용, xml 뷰를 자바객체로 변환함
     * @param2 > ViewGroup : fragment를 포함할 Activity의 레이아웃을 가리킴
     * @param3 > Bundle : fragment 상태 저장했다가 다시 살려낼떄 사용
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragment가 어떤 레이아웃을 사용하는지 안드로이드에게 알려줌
        return inflater.inflate(R.layout.fragment_a, container, false);
    }
}