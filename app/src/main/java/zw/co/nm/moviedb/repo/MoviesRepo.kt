package zw.co.nm.moviedb.repo

import retrofit2.Response
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.model.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.model.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.model.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.model.SearchMultiResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class MoviesRepo {
    suspend fun getPopularMovies(page: Int):zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse> =
       apiCall {NetworkManager.movieService.getPopularMovies(page)}

    suspend fun getMovieDetails(movieId: Int): Response<GetMovieDetailResponse> =
        NetworkManager.movieService.getMovieDetail(movieId)


    suspend fun getSimilarMoviesList(movieId: Int): Response<GetSimilarMoviesResponse> =
        NetworkManager.movieService.getSimilarMoviesList(movieId)

    suspend fun getCredits(movieId: Int): Response<GetCreditsResponse> =
        NetworkManager.movieService.getCredits(movieId)

    suspend fun searchMulti(query: String): Response<SearchMultiResponse> =
        NetworkManager.movieService.searchMulti(query, true)


}