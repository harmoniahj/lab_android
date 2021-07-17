package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class ErrandRes (
    @SerializedName("ERRAND_STATUS") val errandStatus: String,
    @SerializedName("ERRAND_REQUEST_DATE") val errandRequestDate: String,
    @SerializedName("ERRAND_CONTENT") val errandContent: String,
    @SerializedName("ERRAND_TOTAL_PRICE") val errandTotalPrice: String,
    @SerializedName("MEM_NICKNAME") val memNickname: String
)