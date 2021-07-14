package com.example.nds.api

import com.example.nds.model.CoinTrans
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinTransService {
    @GET("192.168.0.62:9696/myPage/my_wallet.nds")
    fun getcoinTrans(
        @Query("mem_email") mem_email: String
    ): Call<CoinTrans>
}