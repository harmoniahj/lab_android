package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class ErrandReq (
    @SerializedName("MEM_EMAIL_REQ") val memEmail: String,
    @SerializedName("ERRAND_STATUS") val errandStatus: String,
    @SerializedName("ERRAND_REQUEST_DATE") val errandRequestDate: String,
    @SerializedName("ERRAND_ITEM") val errandItem: String,
    @SerializedName("ERRAND_TOTAL_PRICE") val errandTotalPrice: String
)