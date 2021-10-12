package com.example.cryptocoinapp.data.remote.dto

import com.example.cryptocoinapp.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int,
    @SerializedName("date_added")
    val dateAdded: String,
    val tags: List<String>,
    @SerializedName("max_supply")
    val maxSupply: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double,
    val platform: Any,
    @SerializedName("cmc_rank")
    val cmcRank: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    val quote: Quote
)

data class Quote(
    val USD: USD
)

data class USD(
    val price: Double,
    @SerializedName("volume_24h")
    val volume24h: Double,
    @SerializedName("percent_change_1h")
    val percentChange1h: Double,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double,
    @SerializedName("percent_change_30d")
    val percentChange30d: Double,
    @SerializedName("percent_change_60d")
    val percentChange60d: Double,
    @SerializedName("percent_change_7d")
    val percentChange7d: Double,
    @SerializedName("percent_change_90d")
    val percentChange90d: Double,
    @SerializedName("market_cap")
    val marketCap: Double,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double,
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double,
    @SerializedName("last_updated")
    val lastUpdated: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        circulatingSupply = circulatingSupply,
        totalSupply = totalSupply,
        cmcRank = cmcRank,
        dateAdded = dateAdded,
        lastUpdated = lastUpdated,
        maxSupply = maxSupply,
        tags = tags,
        price = quote.USD.price,
        volume24h = quote.USD.volume24h,
        percentChange1h = quote.USD.percentChange1h,
        percentChange24h = quote.USD.percentChange24h,
        percentChange30d = quote.USD.percentChange30d,
        percentChange60d = quote.USD.percentChange60d,
        percentChange90d = quote.USD.percentChange90d,
        marketCap = quote.USD.marketCap
    )
}