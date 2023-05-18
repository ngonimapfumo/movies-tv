package zw.co.nm.moviedb.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.network.NetworkManager

class GetMovieDetailRepo {
    fun getMovieDetails(movieId: Int): Flow<GetMovieDetailResponse?> =
        flow {
            emit(NetworkManager.apiService.getMovieDetail(movieId).body())
        }.flowOn(Dispatchers.IO)

}