package com.example.terrgym80;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LoginFragment extends Fragment {
    private TextView tv_msg = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(this.getClass().getName(),"onCreateView");
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Log.i(this.getClass().getName(),"onViewCreated");
        tv_msg = view.findViewById(R.id.tv_msg);
        Log.i(this.getClass().getName(),tv_msg.getText().toString());
    }
}