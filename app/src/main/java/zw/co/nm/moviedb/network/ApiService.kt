package zw.co.nm.moviedb.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import zw.co.nm.moviedb.models.GetPopularMoviesListResponse

interface ApiService {

    @GET("movie/")
    suspend fun getMovieDetail()

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("language") lang:String,
        @Query("page") page:Int
    ): Response<GetPopularMoviesListResponse>
}