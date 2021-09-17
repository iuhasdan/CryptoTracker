package com.example.cryptocoinapp.network

import com.example.cryptocoinapp.data.Currencies
import com.example.cryptocoinapp.data.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIResponse{

    @GET("v2/assets")
    suspend fun getAssets() : Response<Data>
}