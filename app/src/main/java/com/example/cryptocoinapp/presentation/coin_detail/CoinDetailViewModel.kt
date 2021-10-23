package com.example.cryptocoinapp.presentation.coin_detail

import androidx.lifecycle.*
import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.common.Resource
import com.example.cryptocoinapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocoinapp.domain.use_case.get_historical_data.GetHistoricalDataUseCase
import com.example.cryptocoinapp.presentation.coin_history.CoinHistoricalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val getHistoricalDataUseCase: GetHistoricalDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _coinState = MutableLiveData<CoinDetailState>()
    private val _coinDataState = MutableLiveData<CoinHistoricalDataState>()

    fun getCoinViewStateLiveData(): LiveData<CoinDetailState> = _coinState

    fun getHistoricalDataViewStateLiveData(): LiveData<CoinHistoricalDataState> = _coinDataState


    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }

        savedStateHandle.get<String>(Constants.PARAM_COIN_PERIOD)?.let { coinId ->
            coinId.also {
                savedStateHandle.get<String>(Constants.PARAM_COIN_NAME)?.let { period ->
                    getHistoricalData(coinId,period)
                }
            }
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinState.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _coinState.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _coinState.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getHistoricalData(period: String, coinId: String) {
        getHistoricalDataUseCase(period,coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _coinDataState.value = result.data?.let { CoinHistoricalDataState(entryList = it.chart) }
                }
                is Resource.Error -> {
                    _coinDataState.value =
                        CoinHistoricalDataState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _coinDataState.value = CoinHistoricalDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}