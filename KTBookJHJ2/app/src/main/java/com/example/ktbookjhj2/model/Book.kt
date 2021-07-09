package com.example.ktbookjhj2.model

import com.google.gson.annotations.SerializedName

data class Book (
    // 매핑해서 값을 가져오기 위해 @SerializedName
    // 파라미터 value는 json파일에서 가져온 값
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coveerSmallUrl") val coveerSmallUrl: String
)