package com.example.ktbook

import com.google.gson.annotations.SerializedName

data class BestSellerDTO (
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>
)