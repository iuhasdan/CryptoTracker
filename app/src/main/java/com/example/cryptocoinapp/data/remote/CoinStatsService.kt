package com.example.cryptocoinapp.data.remote

import com.example.cryptocoinapp.data.remote.dto.CoinHistoricalDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinStatsService {

    @GET("public/v1/charts")
    suspend fun getHistoricalData(
        @Query("period") period: String,
        @Query("coinId") coinId: String
    ): CoinHistoricalDataDto
}