package com.example.cryptocoinapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.common.Resource
import com.example.cryptocoinapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocoinapp.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocoinapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    //    private val _state = mutableStateOf(CoinDetailState())
    val state = MutableLiveData<CoinDetailState>()


    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    state.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    state.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}