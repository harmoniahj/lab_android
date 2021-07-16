package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class CoinTrans (
    @SerializedName("TRANS_DATE") val transDate: String,
    @SerializedName("TRANS_CONTENT") val transContent: String,
    @SerializedName("TRANS_PRICE") val transPrice: String,
    @SerializedName("TRANS_REMAIN") val transRemain: String,
    @SerializedName("TRANS_IO") val transIo: String
)