package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class CoinTrans (
    @SerializedName("transNo") val transNo: Int,
    @SerializedName("transDate") val transDate: String,
    @SerializedName("transContent") val transContent: String,
    @SerializedName("transPrice") val transPrice: Int,
    @SerializedName("transRemain") val transRemain: Int,
    @SerializedName("transIo") val transIo: String
)