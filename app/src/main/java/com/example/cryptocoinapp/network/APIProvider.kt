package com.example.cryptocoinapp.network

import android.util.Log
import com.example.cryptocoinapp.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

val TAG = "ASSETS"

class APIProvider {

    companion object {
        fun buildRetrofitClient() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

            val client: OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build()
        }
    }
}