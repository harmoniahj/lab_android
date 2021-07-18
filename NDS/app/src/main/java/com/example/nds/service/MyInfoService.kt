package com.example.nds.service

import com.example.nds.model.MyInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyInfoService {
    @GET("member/jsonSelectMember.nds")
    fun getMyInfo(
        @Query("mem_email") mem_email: String
    ): Call<MyInfo>
}