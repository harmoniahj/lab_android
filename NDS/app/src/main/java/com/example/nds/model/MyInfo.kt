package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class MyInfo (
    @SerializedName("MEM_IMG") val memImg: String,
    @SerializedName("MEM_STAR") val memStar: String,
    @SerializedName("MEM_EMAIL") val memEmail: String,
    @SerializedName("MEM_NICKNAME") val memNickname: String,
    @SerializedName("MEM_PW") val memPw: String
)