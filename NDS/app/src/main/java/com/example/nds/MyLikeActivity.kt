package com.example.nds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import com.example.nds.adapter.MyLikeAdapter
import com.example.nds.service.MyLikeService
import com.example.nds.databinding.ActivityMyLikeBinding
import com.example.nds.model.Mylike
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MyLikeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyLikeBinding
    private lateinit var adapter: MyLikeAdapter
    private lateinit var myLikeService: MyLikeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLikeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyLikeAdapter()
        binding.myLikeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myLikeRecyclerView.adapter = adapter

        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.24:9696")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myLikeService = retrofit.create(MyLikeService::class.java)

        // 내 찜 목록 불러오기
        myLikeService.getMyLike("water@good.com")
            .enqueue(object: Callback<List<Mylike>>{
                override fun onResponse(
                    call: Call<List<Mylike>>,
                    response: Response<List<Mylike>>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT!!! SUCCESS")
                        return;
                    }
                    Log.e(TAG, "성공!")
                    Log.e(TAG, "${response.body()}")
                    adapter.submitList(response.body()?.orEmpty())
                }

                override fun onFailure(call: Call<List<Mylike>>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, "실패...")
                    Log.e(TAG, t.toString())
                }
            })
    }
}