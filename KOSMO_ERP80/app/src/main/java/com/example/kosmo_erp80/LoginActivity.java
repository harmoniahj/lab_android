package com.example.kosmo_erp80;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kosmo_erp80.dto.MemberDTO;
import com.example.kosmo_erp80.util.VolleyCallback;
import com.example.kosmo_erp80.util.VolleyQueueProvider;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    //로그인 성공시 톰캣 서버로 부터 받아올 이름 담기
    private static String sname = null;
    EditText et_id = null;
    EditText et_pw = null;
    private Map<String, String> pmap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(),"로그인 버튼 호출 성공");
              //  login();
                loginProcess(pmap);
            }
        });
    }

    public void onStart() {
        super.onStart();
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        pmap.put("mem_id", isDestroyed());
        pmap.put("mem_pw", isDestroyed());
    }

    // 사용자로부터 받아온 값 파라미터로 넘겨 받음
    public void loginProcess(Map<String, String> pmap) {
        VolleyQueueProvider.initRequestQueue(this);
        VolleyQueueProvider.openQueue();
        VolleyQueueProvider.callbackVolley(new VolleyCallback() {
            @Override
            public void onResponse(String response) {
                // [{"mem_id":"test", "mem_name":"김유신"}]
               List<Map<String, Object>> resultList = new Gson().fromJson(response, List.class);
               if(resultList.size()  == 0) {
                   Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
               } else if(resultList.get(0).getMem_name().equals("-1")) {
                   Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
               } else {
                   for(Map.Entry dtoTOMap : resultList.get(0).entrySet()) {
                       if(dtoTOMap.getKey().equals("mem_name")) {
                           MemberDTO.getInstance().setMem_name(dtoTOMap.getValue().toString());
                           continue;
                       }
                   }
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   intent.putExtra("sname", MemberDTO.getInstance().getMem_name());
                   startActivity(intent);
               }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.toString());
            }
        }, "login/jsonLogin", pmap);
    }

    public void login(){
        Log.i(this.getClass().getName(),"login() 호출 성공");
        EditText et_id = findViewById(R.id.et_id);
        EditText et_pw = findViewById(R.id.et_pw);
        final String id = et_id.getText().toString();
        final String pw = et_pw.getText().toString();
        //println("id:"+et_id.getText().toString()+", pw:"+ et_pw.getText().toString());
        String apiURL = "http://192.168.0.244:7000/login/postLogin";

        try {
            StringRequest request = new StringRequest(Request.Method.POST, apiURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    sname = response;
                    println(sname);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    println("에러 : "+error.getMessage());
                }
            }){
                //사용자로 부터 입력 받은 아이디와 비번을 넘김
                public Map<String, String> getParams() throws AuthFailureError{
                    Map<String,String> params = new HashMap<>();
                    params.put("mem_id",id);
                    params.put("mem_pw",pw);
                    return params;
                }
            };
            request.setShouldCache(false);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        } catch (Exception e){
            Log.i("Volley", e.toString());
        }
        println("요청 보내고 받아 옴."+sname);
        //로그인 처리가 성공하면
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("sname", sname);
        startActivity(intent);
    }
    public void println(String data){
        Log.i(this.getClass().getName(),data+"\n");
    }
}