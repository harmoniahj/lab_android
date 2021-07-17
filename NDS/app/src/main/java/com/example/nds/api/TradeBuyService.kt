package com.example.nds.api

import com.example.nds.model.TradeBuy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TradeBuyService {
    @GET("member/selectMyTrade.nds")
    fun getTradeBuy(
        @Query("mem_email") mem_email: String
    ): Call<List<TradeBuy>>
}