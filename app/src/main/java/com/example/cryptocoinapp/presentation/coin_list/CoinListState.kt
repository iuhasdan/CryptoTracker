package com.example.cryptocoinapp.presentation.coin_list

import com.example.cryptocoinapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
