package com.example.nds.api

import com.example.nds.model.ErrandReq
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ErrandReqService {
    @GET("/myPage/my_errand.nds")
    fun getErrandReq(
        @Query("mem_email") mem_email: String
    ): Call<List<ErrandReq>>
}