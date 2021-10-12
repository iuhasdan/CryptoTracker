package com.example.cryptocoinapp.domain.model

import com.example.cryptocoinapp.data.remote.dto.Urls
import com.google.gson.annotations.SerializedName

data class CoinDetail(
    val id: Int,
    val name: String,
    val symbol: String,
    val description: String,
    val logo: String,
    @SerializedName("tag-names")
    val tagNames: List<String>,
    val urls: Urls
)