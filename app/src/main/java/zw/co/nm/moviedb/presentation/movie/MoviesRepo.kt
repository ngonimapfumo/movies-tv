package zw.co.nm.moviedb.presentation.movie

import android.content.Context
import zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class MoviesRepo(private val context: Context) {

    suspend fun getPopularMovies(page: Int): Response<GetPopularMoviesListResponse> =
        apiCall { NetworkManager.movieService.getPopularMovies(page) }

    suspend fun getMovieDetails(movieId: Int): Response<GetMovieDetailResponse> =
        apiCall { NetworkManager.movieService.getMovieDetail(movieId) }


    suspend fun getSimilarMoviesList(movieId: Int): Response<GetSimilarMoviesResponse> =
        apiCall { NetworkManager.movieService.getSimilarMoviesList(movieId) }

    suspend fun getCredits(movieId: Int): Response<GetCreditsResponse> =
        apiCall { NetworkManager.movieService.getCredits(movieId) }


    suspend fun getMovieReleaseDates(movieId: Int): Response<GetReleaseDatesResponse> =
        apiCall { NetworkManager.movieService.getMovieReleaseDates(movieId) }
}