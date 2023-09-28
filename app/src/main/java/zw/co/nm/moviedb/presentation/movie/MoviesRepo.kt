package zw.co.nm.moviedb.presentation.movie

import android.content.Context
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class MoviesRepo(private val context: Context) {

    suspend fun getPopularMovies(page: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse> =
        apiCall { NetworkManager.movieService.getPopularMovies(page) }

    suspend fun getMovieDetails(movieId: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse> =
        apiCall { NetworkManager.movieService.getMovieDetail(movieId) }


    suspend fun getSimilarMoviesList(movieId: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse> =
        apiCall { NetworkManager.movieService.getSimilarMoviesList(movieId) }

    suspend fun getCredits(movieId: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse> =
        apiCall { NetworkManager.movieService.getCredits(movieId) }


    suspend fun getMovieReleaseDates(movieId: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse> =
        apiCall { NetworkManager.movieService.getMovieReleaseDates(movieId) }
}