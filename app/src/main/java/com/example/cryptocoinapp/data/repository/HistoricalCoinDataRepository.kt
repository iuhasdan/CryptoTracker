package com.example.cryptocoinapp.data.repository

import com.example.cryptocoinapp.data.remote.dto.CoinHistoricalDataDto

interface HistoricalCoinDataRepository {

    suspend fun getHistoricalData(period: String, coinId: String): CoinHistoricalDataDto
}