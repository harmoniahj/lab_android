package com.example.tomcatconnect2021;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
 
/* 새로 만든 스레드에서 UI객체에 직점 접근하는 것은 불가함 > 핸들러 클래스를 사용하기도 하자만 핸들러를 사용하면 코드가 복잡해짐
 * 이러한 Background 작업을 심플하게 만들기 위해 AsyncTask 클래스를 사용
 * 스레드를 위한 동작 코드롸 UI접근 코드를 한꺼번에 넣을 수 있음 */
public class LoginLogic extends AsyncTask<String, Void, String> {
    String sendMsg = null; // 앱에서 입력한 아이디와 비밀번호를 담아서 톰캣 서버에 전달
    String receiveMsg = null; // 톰캣서보를 통해 처리된 결과를 받아서 담기

    // 반드시 재정의 할 메소드!!
    @Override // Background에서 실행할 코드를 포함하는 메소드
    public String doInBackground(String... strings) {
     //   String apiURL = "http:// 172.30.1.36:7000/android/androidOracleConnection";
        String apiURL = "http:// 172.30.1.36:7000/member/login";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Connection-Type", "application/x-www-form-urlendcoded");
            con.setRequestMethod("POST");
            Log.i("LoginLogic", "con : " + con.toString());

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());

            // 톰캣서버에 전송할 메시지 처리
            sendMsg = "mem_id=" + strings[0] + "mem_pw" + strings[1];
            osw.write(sendMsg);
            osw.flush(); // false면 갖고 있음(기억)
            int responseCode = con.getResponseCode(); // 200, 204, 404, 500 코드
            Log.i("LoginLogic", "responseCode : " + responseCode);

            BufferedReader br = null;
            if(responseCode == con.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine = null;
                StringBuilder sb_res = new StringBuilder();

                while((inputLine = br.readLine() )!= null) {
                    sb_res.append(inputLine);
                }
                receiveMsg = sb_res.toString();
            }
        } catch (Exception e){
            Log.i("LoginLogic", "Exception : " + e.toString());
        }
        return receiveMsg;
    }
}
