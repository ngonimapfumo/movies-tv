package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.request.RateMediaRequest
import zw.co.nm.moviedb.data.remote.model.response.AccountStatesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieKeywordsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTrendingResponse
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse

interface MovieService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse>

    @GET("movie/{movie_id}/account_states")
    suspend fun getMovieAccountStates(
        @Path("movie_id") movieId: Int,
        @Query("session_id") sessionId: String
    ): Response<AccountStatesResponse>

    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path("movie_id") movieId: Int,
        @Query("session_id") sessionId: String,
        @Body body: RateMediaRequest
    ): Response<StatusResponse>

    @DELETE("movie/{movie_id}/rating")
    suspend fun deleteMovieRating(
        @Path("movie_id") movieId: Int,
        @Query("session_id") sessionId: String
    ): Response<StatusResponse>

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

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse>

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

    @GET("discover/movie")
    suspend fun getMoviesByGenreId(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("with_genres") with_genres: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("discover/movie")
    suspend fun getMoviesByKeywordId(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("with_keywords") with_keywords: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("trending/all/day")
    suspend fun getTrending(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<GetTrendingResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<GetPopularMoviesListResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<GetPopularMoviesListResponse>

    @GET("movie/{id}/keywords")
    suspend fun getMovieKeywords(@Path("id") movieId: Int): Response<GetMovieKeywordsResponse>

}