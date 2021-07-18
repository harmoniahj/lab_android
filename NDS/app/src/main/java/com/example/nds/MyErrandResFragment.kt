package com.example.nds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nds.adapter.ErrandResAdapter
import com.example.nds.databinding.FragmentMyErrandResBinding
import com.example.nds.model.ErrandRes
import com.example.nds.service.ErrandResService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MyErrandResFragment : Fragment(R.layout.fragment_my_errand_res) {
    private lateinit var binding: FragmentMyErrandResBinding
    private lateinit var adapter: ErrandResAdapter
    private lateinit var errandResService: ErrandResService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentMyErrandResBinding.inflate(layoutInflater)

        adapter = ErrandResAdapter()
        binding.errandResRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.errandResRecyclerView.adapter = adapter

        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.24:9696")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        errandResService = retrofit.create(ErrandResService::class.java)

        val view = binding.root

        // 내가 수행한 심부름 목록 불러오기
        errandResService.getErrandRes("banana@good.com")
            .enqueue(object: Callback<List<ErrandRes>>{
                override fun onResponse(
                    call: Call<List<ErrandRes>>,
                    response: Response<List<ErrandRes>>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT!!! SUCCESS")
                        return;
                    }
                    Log.e(TAG, "성공!")
                    Log.e(TAG, "${response.body()}")
                    adapter.submitList(response.body()?.orEmpty())
                }

                override fun onFailure(call: Call<List<ErrandRes>>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, "실패...")
                    Log.e(TAG, t.toString())
                }
            })

        return view
    }
}