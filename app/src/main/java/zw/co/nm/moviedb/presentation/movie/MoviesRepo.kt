package zw.co.nm.moviedb.presentation.movie

import android.content.Context
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
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