package com.example.nds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nds.adapter.ErrandReqAdapter
import com.example.nds.databinding.FragmentMyErrandReqBinding
import com.example.nds.model.ErrandReq
import com.example.nds.model.ErrandRes
import com.example.nds.service.ErrandReqService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyErrandReqFragment : Fragment(R.layout.fragment_my_errand_req) {
    private lateinit var binding: FragmentMyErrandReqBinding
    private lateinit var adapter: ErrandReqAdapter
    private lateinit var errnadReqService: ErrandReqService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyErrandReqBinding.inflate(layoutInflater)

        adapter = ErrandReqAdapter()
        binding.errandReqRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.errandReqRecyclerView.adapter = adapter

        var retrofit = Retrofit.Builder()
            .baseUrl("http://172.30.1.36:9696")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        errnadReqService = retrofit.create(ErrandReqService::class.java)

        val view = binding.root

        errnadReqService.getErrandReq("banana@good.com")
            .enqueue(object: Callback<List<ErrandReq>> {
                override fun onResponse(
                    call: Call<List<ErrandReq>>,
                    response: Response<List<ErrandReq>>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(ConstraintLayoutStates.TAG, "NOT!!! SUCCESS")
                        return;
                    }
                }

                override fun onFailure(call: Call<List<ErrandReq>>, t: Throwable) {
                    // 실패처리
                    Log.e(ConstraintLayoutStates.TAG, "실패...")
                    Log.e(ConstraintLayoutStates.TAG, t.toString())
                }
            })

        return view
    }
}