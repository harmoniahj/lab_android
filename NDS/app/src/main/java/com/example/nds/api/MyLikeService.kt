package com.example.nds.api

import com.example.nds.model.Mylike
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyLikeService {
    @GET("myPage/my_likeAndroid.nds")
    fun getMyLike(
        @Query("mem_email") mem_email: String
    ): Call<List<Mylike>>
}