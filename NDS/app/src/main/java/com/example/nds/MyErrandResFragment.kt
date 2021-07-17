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
import com.example.nds.api.ErrandResService
import com.example.nds.databinding.ErrandResBinding
import com.example.nds.databinding.FragmentMyErrandResBinding
import com.example.nds.model.ErrandRes
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MyErrandResFragment : Fragment() {
    private lateinit var binding: FragmentMyErrandResBinding
    private lateinit var adapter: ErrandResAdapter
    private lateinit var errnadResService: ErrandResService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyErrandResBinding.inflate(layoutInflater)

        adapter = ErrandResAdapter()
        binding.errandResRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.errandResRecyclerView.adapter = adapter

        var retrofit = Retrofit.Builder()
            .baseUrl("http://172.30.1.36:9696")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        errnadResService = retrofit.create(ErrandResService::class.java)

        errnadResService.getErrandRes("banana@good.com")
            .enqueue(object: Callback<List<ErrandRes>> {
                override fun onResponse(
                    call: Call<List<ErrandRes>>,
                    response: Response<List<ErrandRes>>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT!!! SUCCESS")
                        return;
                    }
                }

                override fun onFailure(call: Call<List<ErrandRes>>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, "실패...")
                    Log.e(TAG, t.toString())
                }
            })

        return inflater.inflate(R.layout.fragment_my_errand_res, container, false)
    }
}