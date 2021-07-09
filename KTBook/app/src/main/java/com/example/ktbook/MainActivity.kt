package com.example.ktbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 래트로핏 구현체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("9EE4D2FB11296A53D46A7756CF021C5623A5CEB69AE7CDA236A82F183EA3C009")
            .enqueue(object: Callback<BestSellerDTO> {
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT!!! SUCCESS")
                        return;
                    }
                    response.body()?.let{
                        Log.d(TAG,"body에서 꺼내오기"+it.toString())
                        it.books.forEach{book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    //실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}