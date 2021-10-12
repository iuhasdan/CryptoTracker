package com.example.cryptocoinapp.data.remote


import com.example.cryptocoinapp.common.Constants.API_KEY
import com.example.cryptocoinapp.data.remote.dto.CoinDetailDto
import com.example.cryptocoinapp.data.remote.dto.Data
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinMarketCapApi {

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoins(): Data

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("/v1/cryptocurrency/info?")
    suspend fun getCoinById(
        @Query("id") id: String
    ): CoinDetailDto
}