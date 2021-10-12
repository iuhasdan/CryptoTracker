package com.example.cryptocoinapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Coin(

    val id: Int,
    val name: String,
    val symbol: String,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double,
    @SerializedName("cmc_rank")
    val cmcRank: Int,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("max_supply")
    val maxSupply: Double,
    val tags: List<String>,
    val price: Double,
    val volume24h: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange90d: Double,
    val marketCap: Double
) : Parcelable