package com.example.cryptocoinapp.domain.use_case.get_historical_data

import com.example.cryptocoinapp.common.Resource
import com.example.cryptocoinapp.data.remote.dto.CoinHistoricalDataDto
import com.example.cryptocoinapp.data.remote.dto.toHistoricalData
import com.example.cryptocoinapp.data.repository.HistoricalCoinDataRepository
import com.example.cryptocoinapp.domain.model.HistoricalCoinData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHistoricalDataUseCase @Inject constructor(
    private val repository: HistoricalCoinDataRepository
) {
    operator fun invoke(period: String, coinId: String): Flow<Resource<HistoricalCoinData>> =
        flow {
            try {
                emit(Resource.Loading<HistoricalCoinData>())
                val coin = repository.getHistoricalData(period, coinId).toHistoricalData()
                emit(Resource.Success<HistoricalCoinData>(coin))
            } catch (e: HttpException) {
                emit(Resource.Error<HistoricalCoinData>(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error<HistoricalCoinData>("Couldn't reach server. Check your internet connection."))
            }
        }
}
