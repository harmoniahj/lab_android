package com.example.nds.api

import com.example.nds.model.ErrandRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ErrandResService {
    @GET("myPage/my_errand.nds")
    fun getErrandRes(
        @Query("mem_email") mem_email: String
    ): Call<List<ErrandRes>>
}