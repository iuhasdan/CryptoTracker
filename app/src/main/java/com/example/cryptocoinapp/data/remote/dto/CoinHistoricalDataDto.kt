package com.example.cryptocoinapp.data.remote.dto

import com.example.cryptocoinapp.domain.model.HistoricalCoinData
import com.github.mikephil.charting.data.Entry

data class CoinHistoricalDataDto(
    val chart: List<List<Double>>
)

fun CoinHistoricalDataDto.toHistoricalData(): HistoricalCoinData {

    var index = 0
    val listChart: List<Entry> = chart.map { it ->
        index++
        Entry(index.toFloat(), it[1].toFloat())
    }

    return HistoricalCoinData(listChart)
}


