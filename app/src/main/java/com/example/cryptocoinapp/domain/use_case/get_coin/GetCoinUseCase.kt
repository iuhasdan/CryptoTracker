package com.example.cryptocoinapp.domain.use_case.get_coin

import com.example.cryptocoinapp.common.Resource
import com.example.cryptocoinapp.data.remote.dto.toCoinDetail
import com.example.cryptocoinapp.data.repository.CoinRepository
import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId)
            val coin2 = coin.toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin2))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}
