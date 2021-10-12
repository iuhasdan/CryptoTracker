package com.example.cryptocoinapp.data.repository

import com.example.cryptocoinapp.data.remote.dto.CoinDetailDto
import com.example.cryptocoinapp.data.remote.dto.Data

interface CoinRepository {

    suspend fun getCoins(): Data

    suspend fun getCoinById(coinId: String) : CoinDetailDto
}