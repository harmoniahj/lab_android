package com.example.terrgym80;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private Map<String,String> pmap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginInit(View v) {
        EditText etm_id = findViewById(R.id.etm_id);
        EditText etm_pw = findViewById(R.id.etm_pw);
        String id = etm_id.getText().toString();
        String pw = etm_pw.getText().toString();
        pmap.put("mem_id", id);
        pmap.put("mem_pw", pw);
        Log.i(TAG, "사용자가 입력한 값 ===> " + pmap);
        loginProcess(pmap);
    }
    public void loginProcess(Map<String,String> pmap){
        Log.i(TAG, "loginProcess ===> "+pmap);
        VolleyQueueProvider.initRequestQueue(this);
        VolleyQueueProvider.openQueue();
        VolleyQueueProvider.callbackVolley(new VolleyCallback() {
            @Override
            public void onResponse(String response) {
                //[{"mem_id":"test","mem_name":"김유신"}]
                Log.i(this.getClass().getName(),response);
                List<Map<String,Object>> resultList = new Gson().fromJson(response,List.class);
                if(resultList.size() == 0){
                    Toast.makeText(getApplicationContext()
                            , "아이디가 존재하지 않습니다."
                            , Toast.LENGTH_LONG).show();
                }else if(resultList.get(0).get("mem_name").equals("-1")){
                    Toast.makeText(getApplicationContext()
                            , "비밀번호가 일치하지 않습니다."
                            , Toast.LENGTH_LONG).show();
                }else{
                    for(Map.Entry dtoTOMap : resultList.get(0).entrySet()){
                        if( dtoTOMap.getKey().equals("mem_name")){
                            MemberDTO.getInstance().setMem_name(dtoTOMap.getValue().toString());continue;
                        }
                    }///////////////end of for
                }/////////////end of else
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.toString());
            }
        }, "login/jsonLogin", pmap);
        Toast.makeText(this
                ,MemberDTO.getInstance().getMem_name()+"님 환영합니다."
                , Toast.LENGTH_LONG).show();
        this.finish();
    }///////////////////////end of loginProcess
}