package com.example.cryptocoinapp.data.repository

import com.example.cryptocoinapp.data.remote.CoinStatsService
import com.example.cryptocoinapp.data.remote.dto.CoinHistoricalDataDto
import javax.inject.Inject

class HistoricalCoinDataRepositoryImpl @Inject constructor(
    private val api : CoinStatsService
) : HistoricalCoinDataRepository{

    override suspend fun getHistoricalData(period: String, coinId: String): CoinHistoricalDataDto {
        return api.getHistoricalData(period, coinId)
    }
}