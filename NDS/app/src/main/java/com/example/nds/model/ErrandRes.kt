package com.example.nds.model

import com.google.gson.annotations.SerializedName

data class ErrandRes (
    @SerializedName("errand_status") val errandStatus: String,
    @SerializedName("errand_request_date") val errandRequestDate: String,
    @SerializedName("errand_content") val errandContent: String,
    @SerializedName("errand_total_price") val errandTotalPrice: String,
    @SerializedName("mem_nickname") val memNickname: String
)