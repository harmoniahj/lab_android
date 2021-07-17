package com.example.nds.service

import com.example.nds.model.TradeBuy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TradeBuyService {
    @GET("member/selectMyTradeAndroid.nds")
    fun getTradeBuy(
        @Query("mem_nickname") mem_nickname: String
    ): Call<List<TradeBuy>>
}