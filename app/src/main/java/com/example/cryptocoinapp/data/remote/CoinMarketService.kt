package com.example.cryptocoinapp.data.remote


import com.example.cryptocoinapp.BuildConfig
import com.example.cryptocoinapp.data.remote.dto.CoinDetailDto
import com.example.cryptocoinapp.data.remote.dto.Data
import retrofit2.Response
import retrofit2.http.*

interface CoinMarketService {

    @Headers("X-CMC_PRO_API_KEY: ${BuildConfig.API_KEY}")
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoins(): Data

    @Headers("X-CMC_PRO_API_KEY: ${BuildConfig.API_KEY}")
    @GET("/v1/cryptocurrency/info?")
    suspend fun getCoinById(
        @Query("id") id: String
    ): CoinDetailDto
}