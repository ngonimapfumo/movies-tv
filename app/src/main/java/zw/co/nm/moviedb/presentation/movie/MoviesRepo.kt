package zw.co.nm.moviedb.presentation.movie

import zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class MoviesRepo {
    suspend fun getPopularMovies(
        page: Int,
        language: String
    ): Response<GetPopularMoviesListResponse> =
        apiCall {
            NetworkManager.movieService.getPopularMovies(page, language)
        }

    suspend fun getMovieDetails(movieId: Int, language: String): Response<GetMovieDetailResponse> =
        apiCall { NetworkManager.movieService.getMovieDetail(movieId, language) }


    suspend fun getSimilarMoviesList(
        movieId: Int,
        language: String
    ): Response<GetSimilarMoviesResponse> =
        apiCall { NetworkManager.movieService.getSimilarMoviesList(movieId, language) }

    suspend fun getCredits(movieId: Int, language: String): Response<GetCreditsResponse> =
        apiCall { NetworkManager.movieService.getCredits(movieId, language) }


    suspend fun getMovieReleaseDates(
        movieId: Int,
        language: String
    ): Response<GetReleaseDatesResponse> =
        apiCall { NetworkManager.movieService.getMovieReleaseDates(movieId, language) }

    suspend fun getMovieImages(movieId: Int): Response<GetMovieImagesResponse> = apiCall {
        NetworkManager.movieService.getMovieImages(movieId)
    }

    suspend fun getMovieGenres(language: String): Response<GetMovieGenres> =
        apiCall {
            NetworkManager.movieService.getMovieGenres(language)
        }

    suspend fun getMovieByGenreId(
        page: Int,
        language: String,
        genreId: Int
    ): Response<GetPopularMoviesListResponse> =
        apiCall {
            NetworkManager.movieService.getMoviesByGenreId(page, language, genreId)
        }
}