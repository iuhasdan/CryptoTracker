package com.example.cryptocoinapp.di

import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.data.remote.CoinStatsService
import com.example.cryptocoinapp.data.repository.HistoricalCoinDataRepository
import com.example.cryptocoinapp.data.repository.HistoricalCoinDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule2 {

    private const val BLOCKCHAIN_API_URL = "https://api.coinstats.app/"

    @Provides
    @Singleton
    fun proviceCoinStatsApi(): CoinStatsService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BLOCKCHAIN_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CoinStatsService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinStatsService): HistoricalCoinDataRepository {
        return HistoricalCoinDataRepositoryImpl(api)
    }
}