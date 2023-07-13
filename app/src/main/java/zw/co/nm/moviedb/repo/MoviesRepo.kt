package zw.co.nm.moviedb.repo

import android.content.Context
import retrofit2.Response
import zw.co.nm.moviedb.data.domain.mappers.MovieMapper
import zw.co.nm.moviedb.data.domain.models.Movie
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse
import zw.co.nm.moviedb.utils.ConfigStore
import zw.co.nm.moviedb.utils.ConfigStore.SEARCH_CONFIG_KEY
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class MoviesRepo(private val context: Context) {

    suspend fun getPopularMovies(page: Int): zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse> =
        apiCall { NetworkManager.movieService.getPopularMovies(page) }

    suspend fun getMovieDetails(movieId: Int): Movie? {
        val req = apiCall { NetworkManager.movieService.getMovieDetail(movieId) }
        return if (req.isSuccessful) {
            MovieMapper.buildFrom(req.body)
        }
        else null


    }


    suspend fun getSimilarMoviesList(movieId: Int): Response<GetSimilarMoviesResponse> =
        NetworkManager.movieService.getSimilarMoviesList(movieId)

    suspend fun getCredits(movieId: Int): Response<GetCreditsResponse> =
        NetworkManager.movieService.getCredits(movieId)

    suspend fun searchMulti(
        query: String,
        page: Int
    ): zw.co.nm.moviedb.data.remote.Response<SearchMultiResponse> {
        return apiCall {
            NetworkManager.movieService.searchMulti(
                query,
                page,
                ConfigStore.getBool(context, SEARCH_CONFIG_KEY)
            )
        }
    }

}