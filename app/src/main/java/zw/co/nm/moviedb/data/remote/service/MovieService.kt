package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse

interface MovieService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse>

    @GET("movie/{id}/similar")
    suspend fun getSimilarMoviesList(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse>

    @GET("person/{id}")
    suspend fun getPerson(
        @Path("id") personId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse>

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse>

    @GET("person/{id}/combined_credits")
    suspend fun getCombinedCredits(
        @Path("id") query: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse>

    @GET("movie/{id}/videos")
    suspend fun getTrailers(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") tvId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>

    @GET("movie/{id}/release_dates")
    suspend fun getMovieReleaseDates(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse>

    @GET("movie/{id}/images")
    suspend fun getMovieImages(@Path("id") movieId: Int): Response<GetMovieImagesResponse>

    @GET("movie/{id}/watch/providers")
    suspend fun getWatchProviders(@Path("id")movieId: Int): Response<GetWatchProvidersResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("language") language: String):Response<GetMovieGenres>

    @GET("")
    suspend fun get(@Query("page") page: Int,
                    @Query("language") language: String,
                    @Query("with_genres") with_genres: Int)

}