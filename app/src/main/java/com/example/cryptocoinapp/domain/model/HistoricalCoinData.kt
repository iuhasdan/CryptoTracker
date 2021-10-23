package com.example.cryptocoinapp.domain.model

import com.github.mikephil.charting.data.Entry

data class HistoricalCoinData(
    val chart: List<Entry>
)