package com.example.nds.api

import com.example.nds.model.TradeSell
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TradeSellService {
    @GET("member/selectMyTrade.nds")
    fun getTradeSell(
        @Query("mem_email") mem_email: String
    ): Call<List<TradeSell>>
}