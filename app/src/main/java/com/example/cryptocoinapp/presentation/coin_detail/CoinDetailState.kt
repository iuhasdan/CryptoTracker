package com.example.cryptocoinapp.presentation.coin_detail

import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
