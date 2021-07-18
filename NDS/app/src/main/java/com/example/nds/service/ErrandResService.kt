package com.example.nds.service

import com.example.nds.model.ErrandRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ErrandResService {
    @GET("errand/selectErrandRecordAndroid.nds")
    fun getErrandRes(
        @Query("mem_email") mem_email: String
    ): Call<List<ErrandRes>>
}