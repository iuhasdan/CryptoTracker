package com.example.cryptocoinapp.data

data class Data(val data: List<Currencies>)

data class Currencies(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String
)