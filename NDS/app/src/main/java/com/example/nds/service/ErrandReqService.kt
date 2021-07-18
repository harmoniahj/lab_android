package com.example.nds.service

import com.example.nds.model.ErrandReq
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ErrandReqService {
    @GET("errand/selectErrandRecordAndroid.nds")
    fun getErrandReq(
        @Query("mem_email") mem_email: String
    ): Call<List<ErrandReq>>
}