package com.example.cryptocoinapp.data.repository

import com.example.cryptocoinapp.data.remote.CoinMarketService
import com.example.cryptocoinapp.data.remote.dto.CoinDetailDto
import com.example.cryptocoinapp.data.remote.dto.Data
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinMarketService
) : CoinRepository {
    override suspend fun getCoins(): Data {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}