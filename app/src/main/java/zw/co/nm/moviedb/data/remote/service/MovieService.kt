package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetMovieDetailResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPopularMoviesListResponse>

    @GET("movie/{id}/similar")
    suspend fun getSimilarMoviesList(
        @Path("id") movieId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetSimilarMoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") movieId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCreditsResponse>

    @GET("person/{id}")
    suspend fun getPerson(
        @Path("id") personId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPersonResponse>

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.SearchMultiResponse>

    @GET("person/{id}/combined_credits")
    suspend fun getCombinedCredits(
        @Path("id") query: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCombinedCreditsResponse>

    @GET("movie/{id}/videos")
    suspend fun getTrailers(
        @Path("id") movieId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTrailersResponse>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") tvId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReviewsResponse>

    @GET("movie/{id}/release_dates")
    suspend fun getMovieReleaseDates(
        @Path("id") movieId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReleaseDatesResponse>

}