package com.example.cryptocoinapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocoinapp.common.Resource
import com.example.cryptocoinapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocoinapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    val state = MutableLiveData<CoinListState>()

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}