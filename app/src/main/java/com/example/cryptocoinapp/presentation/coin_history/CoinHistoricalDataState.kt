package com.example.cryptocoinapp.presentation.coin_history

import com.github.mikephil.charting.data.Entry

data class CoinHistoricalDataState(
    val isLoading: Boolean = false,
    val entryList: List<Entry> = emptyList(),
    val error: String = "")