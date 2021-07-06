package com.example.workout801;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetailFragment extends Fragment {
    private long workoutId;//운동 ID를 담을 변수
    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }
    public WorkoutDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //프래그먼트가 어떤 레이아웃을 사용하는지 안드로이드에 알려줘요.
        //fragment_workout_detail.xml
        //Container파라미터는 프래그먼트 레이아웃을 추가할 액티비티의 ViewGroup가리킨다.
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }
    //프래그먼트의 생명주기 콜백 메소드 중 onStart()오버라이드해야 됨.
    //프래그먼트가 보이는 상태가 되기 직전에 호출됨.
    public void onStart(){
        //생명주기 콜백메소드 구현시 반드시 상위 콜백메소드를 호출할것. -주의
        super.onStart();
        //getView()는 프래그먼트의 루트 뷰를 반환해줌.
        //이 뷰를 이용 운동제목과 설명을 텍스트뷰의 레퍼런스를 얻을 수 있음.
        View view = getView();
        if(view !=null){
            TextView tv_title = view.findViewById(R.id.tv_title);
            Workout workout = Workout.workouts[(int)workoutId];
            tv_title.setText(workout.getName());
            TextView tv_description = view.findViewById(R.id.tv_description);
            tv_description.setText(workout.getDescription());
        }
    }///////////////end of onStart

}