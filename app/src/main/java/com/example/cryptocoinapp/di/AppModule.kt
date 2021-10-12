package com.example.cryptocoinapp.di

import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.data.remote.CoinMarketCapApi
import com.example.cryptocoinapp.data.repository.CoinRepository
import com.example.cryptocoinapp.data.repository.CoinRepositoryImpl
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
object AppModule {

    @Provides
    @Singleton
    fun provideCoinMarketCapApi(): CoinMarketCapApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CoinMarketCapApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinMarketCapApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}