package com.example.ktfinal801

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.ktfinal801.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //파이어베이스 인스턴스화
        auth = Firebase.auth
        var emailEditText = binding.emailEditText
        var passwordEditText = binding.passwordEditText

        initLoginButton()
        initSignUpButton()
        //이메일과 비번이 비워졌을 때
        initEmailAndPasswordEditText()

    }
    //회원가입 구현
    private fun initSignUpButton() {
        val signUpButton = binding.signUpButton
        signUpButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()
            Log.i("LoginActivity","initSignUpButton:${email},${password}")
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(
                            this,
                            "회원가입을 성공했습니다. 로그인 버튼을 눌러 로그인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }/////////////////end of setOnClickListener
    }
    //로그인 구현
    private fun initLoginButton() {
        var loginButton = binding.loginButton
        loginButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()
            Log.i(TAG,"initLoginButton:${email},${password}")
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        successLogin()
                    } else {
                        Toast.makeText(
                            this,
                            "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    //사용자로 부터 이메일 정보 가져오기
    private fun getInputEmail(): String {
        return binding.emailEditText.text.toString()
    }
    //사용자로 부터 비번 정보 가져오기
    private fun getInputPassword(): String {
        return binding.passwordEditText.text.toString()
    }
    private fun initEmailAndPasswordEditText(){
        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val loginButton = binding.loginButton
        val signUpButton = binding.signUpButton
        //이메일란에 입력을 할 때마다 아래이벤트가 감지함.
        emailEditText.addTextChangedListener{
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }
        passwordEditText.addTextChangedListener{
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }
    }//////////////end of initEmailAndPasswordEditText


    private fun successLogin() {
        if (auth.currentUser == null) {//널체크를 해줌.
            Toast.makeText(this, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        val userId: String = auth.currentUser!!.uid
        //파이어베이스 그레이들에 firebase-database-ktx등록
        //json형식으로 저장된다. child.child로 가져올 수 있다.
        val currentUserDb = Firebase.database.reference.child("Users").child(userId)
        val user = mutableMapOf<String, Any>()
        user["userId"] = userId
        //상위 Users안에 userId가 생성되어 추가함.
        currentUserDb.updateChildren(user)
        //타이틀바에 값 변경하기
        //supportActionBar?.title = userId
        //LoginActivity를 종료한다.
        finish()

    }/////////////////////end of successLogin

    companion object {
        private const val TAG = "LoginActivity"
    }

}