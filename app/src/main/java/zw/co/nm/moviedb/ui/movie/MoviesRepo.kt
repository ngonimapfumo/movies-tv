package zw.co.nm.moviedb.ui.movie

import android.content.Context
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse
import zw.co.nm.moviedb.utils.ConfigStore
import zw.co.nm.moviedb.utils.ConfigStore.SEARCH_CONFIG_KEY
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class MoviesRepo(private val context: Context) {

    suspend fun getPopularMovies(page: Int): zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse> =
        apiCall { NetworkManager.movieService.getPopularMovies(page) }

    suspend fun getMovieDetails(movieId: Int): zw.co.nm.moviedb.data.remote.Response<GetMovieDetailResponse> =
        apiCall { NetworkManager.movieService.getMovieDetail(movieId) }


    suspend fun getSimilarMoviesList(movieId: Int): zw.co.nm.moviedb.data.remote.Response<GetSimilarMoviesResponse> =
        apiCall { NetworkManager.movieService.getSimilarMoviesList(movieId) }

    suspend fun getCredits(movieId: Int): zw.co.nm.moviedb.data.remote.Response<GetCreditsResponse> =
        apiCall { NetworkManager.movieService.getCredits(movieId) }

    suspend fun searchMulti(
        query: String,
        page: Int
    ): zw.co.nm.moviedb.data.remote.Response<SearchMultiResponse> = apiCall {
        NetworkManager.movieService.searchMulti(
            query,
            page,
            ConfigStore.getBool(context, SEARCH_CONFIG_KEY)
        )
    }
}