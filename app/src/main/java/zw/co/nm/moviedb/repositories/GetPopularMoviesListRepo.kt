package zw.co.nm.moviedb.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.network.NetworkManager

class GetPopularMoviesListRepo() {

    fun getPopularMovies(page: Int): Flow<GetPopularMoviesListResponse?> = flow {
        emit(NetworkManager.apiService.getPopularMovies(page).body())
    }.flowOn(Dispatchers.IO)
}