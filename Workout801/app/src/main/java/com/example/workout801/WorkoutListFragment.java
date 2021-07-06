package com.example.workout801;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.ListFragment;
/*
프래그먼트의 생명 주기
onAttache() - onCreate() - onCreateView() - onActivityCreated()
onStart() - onResume() - onPause() - onStop() - onDestroyView() - onDestroy() - onDetach()
 */
public class WorkoutListFragment extends ListFragment {
    interface Listener{
        void itemClicked(long id);//추상메소드
    }
    private Listener listener = null;
    public WorkoutListFragment() {
        // Required empty public constructor
    }
    //두번째 리스너 등록하기
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener = (Listener)context;
    }
    //운동제목 클릭에 응답하기
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if(listener!=null){
            listener.itemClicked(id);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] names = new String[Workout.workouts.length];//array
        for(int i=0;i<names.length;i++){
            names[i] = Workout.workouts[i].getName();
        }
        //배열 어댑터를 생성해서 배열 어댑터를 리스트뷰 연결함.
        //Fragment는 Context의 하위 클래스가 아니다.
        //this로 현재 컨텍스트를 배열 어댑터에 전달할 수 없다.
        //LayoutInflater의 getContext()로 컨텍스트 얻을 수 있다.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(inflater.getContext()
                        , android.R.layout.simple_list_item_1
                        , names);//가슴운동, 등운동, 하체운동
        //배열 어댑터를 리스트 뷰에 연결해요.
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }
}

