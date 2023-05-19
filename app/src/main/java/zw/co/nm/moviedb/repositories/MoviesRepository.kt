package zw.co.nm.moviedb.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.models.network.GetSimilarMoviesResponse
import zw.co.nm.moviedb.network.NetworkManager

class MoviesRepository() {

    fun getPopularMovies(page: Int): Flow<zw.co.nm.moviedb.network.Response<GetPopularMoviesListResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getPopularMovies(page) })
        }.flowOn(Dispatchers.IO)

    fun getMovieDetails(movieId: Int): Flow<zw.co.nm.moviedb.network.Response<GetMovieDetailResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getMovieDetail(movieId) })
        }.flowOn(Dispatchers.IO)

    fun getSimilarMoviesList(movieId: Int): Flow<zw.co.nm.moviedb.network.Response<GetSimilarMoviesResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getSimilarMoviesList(movieId) })
        }.flowOn(Dispatchers.IO)

    private inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.network.Response<T> {
        return try {
            zw.co.nm.moviedb.network.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.network.Response.failure(e)
        }
    }

}