package zw.co.nm.moviedb.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import zw.co.nm.moviedb.models.GetMovieDetailResponse
import zw.co.nm.moviedb.models.GetPopularMoviesListResponse
import zw.co.nm.moviedb.models.SearchMovieResponse

interface ApiService {

    @GET("movie/")
    suspend fun getMovieDetail(
        @Query("movie_id") movieId: String
    ): Response<GetMovieDetailResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("include_adult") include_adult: Boolean
    ): Response<SearchMovieResponse>
}