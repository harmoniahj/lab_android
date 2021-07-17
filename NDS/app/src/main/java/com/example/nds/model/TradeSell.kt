package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class TradeSell (
    @SerializedName("bm_no") val bmNo: String,
    @SerializedName("bi_file") val biFile: String,
    @SerializedName("bm_title") val bmTitle: String,
    @SerializedName("bm_date") val bmDate: String,
    @SerializedName("bm_price") val bmPrice: String,
)