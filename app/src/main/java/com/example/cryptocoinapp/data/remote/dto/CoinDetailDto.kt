package com.example.cryptocoinapp.data.remote.dto

import com.example.cryptocoinapp.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName("data")
    val detailData: Map<Int, DetailItem>
)

data class DetailItem(

    val category: String,
    val date_added: String,
    val description: String,
    val id: Int,
    val is_hidden: Int,
    val logo: String,
    val name: String,
    val notice: String,
    val platform: Any,
    val slug: String,
    val subreddit: String,
    val symbol: String,

    @SerializedName("tag-groups")
    val tagGroups: List<String>,

    @SerializedName("tag-names")
    val tagNames: List<String>,
    val tags: List<String>,
    val twitter_username: String,
    val urls: Urls
)


fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = detailData.values.toTypedArray()[0].id,
        name = detailData.values.toTypedArray()[0].name,
        symbol = detailData.values.toTypedArray()[0].symbol,
        description = detailData.values.toTypedArray()[0].description,
        logo = detailData.values.toTypedArray()[0].logo,
        tagNames = detailData.values.toTypedArray()[0].tagNames,
        urls = detailData.values.toTypedArray()[0].urls
    )
}