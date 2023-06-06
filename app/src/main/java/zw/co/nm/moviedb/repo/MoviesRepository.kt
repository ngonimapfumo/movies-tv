package zw.co.nm.moviedb.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import zw.co.nm.moviedb.data.api.NetworkManager
import zw.co.nm.moviedb.model.GetCreditsResponse
import zw.co.nm.moviedb.model.GetMovieDetailResponse
import zw.co.nm.moviedb.model.GetPopularMoviesListResponse
import zw.co.nm.moviedb.model.GetSimilarMoviesResponse
import zw.co.nm.moviedb.model.SearchMovieResponse
import zw.co.nm.moviedb.model.SearchMultiResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class MoviesRepository() {

    fun getPopularMovies(page: Int): Flow<zw.co.nm.moviedb.data.api.Response<GetPopularMoviesListResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getPopularMovies(page) })
        }.flowOn(Dispatchers.IO)

    fun getMovieDetails(movieId: Int): Flow<zw.co.nm.moviedb.data.api.Response<GetMovieDetailResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getMovieDetail(movieId) })
        }.flowOn(Dispatchers.IO)

    fun getSimilarMoviesList(movieId: Int): Flow<zw.co.nm.moviedb.data.api.Response<GetSimilarMoviesResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getSimilarMoviesList(movieId) })
        }.flowOn(Dispatchers.IO)

    fun getCredits(movieId: Int): Flow<zw.co.nm.moviedb.data.api.Response<GetCreditsResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getCredits(movieId) })
        }.flowOn(Dispatchers.IO)

    fun searchMovie(query: String): Flow<zw.co.nm.moviedb.data.api.Response<SearchMovieResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.searchMovie(query, true) })
        }

    fun searchMulti(query: String): Flow<zw.co.nm.moviedb.data.api.Response<SearchMultiResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.searchMulti(query, true) })
        }


}