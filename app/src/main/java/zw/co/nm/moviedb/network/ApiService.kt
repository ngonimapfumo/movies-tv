package zw.co.nm.moviedb.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.models.network.GetCreditsResponse
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.models.network.GetSimilarMoviesResponse
import zw.co.nm.moviedb.models.network.SearchMovieResponse

interface ApiService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int
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

    @GET("movie/{id}/similar")
    suspend fun getSimilarMoviesList(
        @Path("id") movieId: Int
    ): Response<GetSimilarMoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") movieId: Int
    ): Response<GetCreditsResponse>

}