package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class Mylike (
    @SerializedName("BM_NO") val bmNo: String,
    @SerializedName("BI_FILE") val biFile: String,
    @SerializedName("BM_TITLE") val bmTitle: String,
    @SerializedName("BM_DATE") val bmDate: String,
    @SerializedName("BM_PRICE") val bmPrice: String,
)