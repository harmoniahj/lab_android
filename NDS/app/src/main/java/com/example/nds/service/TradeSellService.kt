package com.example.nds.service

import com.example.nds.model.TradeSell
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TradeSellService {
    @GET("member/selectMyTradeAndroid.nds")
    fun getTradeSell(
        @Query("mem_nickname") mem_nickname: String
    ): Call<List<TradeSell>>
}