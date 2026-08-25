package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.request.AddFavoriteRequest
import zw.co.nm.moviedb.data.remote.model.request.AddWatchlistRequest
import zw.co.nm.moviedb.data.remote.model.response.GetAccountResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse

interface AccountService {

    @GET("account")
    suspend fun getAccount(
        @Query("session_id") sessionId: String
    ): Response<GetAccountResponse>

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getWatchlistMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("account/{account_id}/watchlist/tv")
    suspend fun getWatchlistTv(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularTVSeriesListResponse>

    @POST("account/{account_id}/watchlist")
    suspend fun addToWatchlist(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body body: AddWatchlistRequest
    ): Response<StatusResponse>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavoriteTv(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularTVSeriesListResponse>

    @POST("account/{account_id}/favorite")
    suspend fun addToFavorites(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body body: AddFavoriteRequest
    ): Response<StatusResponse>

    @GET("account/{account_id}/rated/movies")
    suspend fun getRatedMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularMoviesListResponse>

    @GET("account/{account_id}/rated/tv")
    suspend fun getRatedTv(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<GetPopularTVSeriesListResponse>

    @GET("account/{account_id}/lists")
    suspend fun getAccountLists(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetAccountListsResponse>
}
