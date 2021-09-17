package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocoinapp.Constants
import com.example.cryptocoinapp.data.Currencies
import com.example.cryptocoinapp.data.Data
import com.example.cryptocoinapp.network.APIResponse
import com.example.cryptocoinapp.network.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class   MainActivityViewModel : ViewModel() {

    var coinsListLiveData: MutableLiveData<Data>

    init {
        coinsListLiveData = MutableLiveData()
    }

    fun getCoinsListObserver(): MutableLiveData<Data> {
        return coinsListLiveData
    }

    fun makeApiCall() {

        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIResponse::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getAssets()
            if (response.isSuccessful) {
                coinsListLiveData.postValue(response.body())
            } else {
                Log.d("error", response.message())
            }
        }
    }
}