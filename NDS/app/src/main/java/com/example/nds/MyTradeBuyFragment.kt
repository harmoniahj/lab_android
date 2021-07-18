package com.example.nds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nds.adapter.TradeBuyAdapter
import com.example.nds.databinding.FragmentMyTradeBuyBinding
import com.example.nds.model.TradeBuy
import com.example.nds.service.TradeBuyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyTradeBuyFragment : Fragment(R.layout.fragment_my_trade_buy) {
    private lateinit var binding: FragmentMyTradeBuyBinding
    private lateinit var adapter: TradeBuyAdapter
    private lateinit var tradeBuyService: TradeBuyService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentMyTradeBuyBinding.inflate(layoutInflater)

        adapter = TradeBuyAdapter()
        binding.tradeBuyRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.tradeBuyRecyclerView.adapter = adapter

        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.166:9696")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        tradeBuyService = retrofit.create(TradeBuyService::class.java)

        val view = binding.root

        // 구매내역 불러오기
        tradeBuyService.getTradeBuy("바나나")
            .enqueue(object: Callback<List<TradeBuy>> {
                override fun onResponse(
                    call: Call<List<TradeBuy>>,
                    response: Response<List<TradeBuy>>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(ConstraintLayoutStates.TAG, "NOT!!! SUCCESS")
                        return;
                    }
                    Log.e(ConstraintLayoutStates.TAG, "성공!")
                    Log.e(ConstraintLayoutStates.TAG, "${response.body()}")
                    adapter.submitList(response.body()?.orEmpty())
                }

                override fun onFailure(call: Call<List<TradeBuy>>, t: Throwable) {
                    // 실패처리
                    Log.e(ConstraintLayoutStates.TAG, "실패...")
                    Log.e(ConstraintLayoutStates.TAG, t.toString())
                }
            })

        return view
    }
}