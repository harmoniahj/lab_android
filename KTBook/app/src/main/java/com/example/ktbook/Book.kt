package com.example.ktbook

import com.google.gson.annotations.SerializedName

// data클래스를 사용하면 toString과 같이 재정의 하지 않아도 됨 + 자동으로 get, set 해주기 때문에 만들지 않아도 됨
data class Book (
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String
)