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
import zw.co.nm.moviedb.data.remote.model.response.GetTVImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") tvShowId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse>

    @GET("tv/{series_id}/account_states")
    suspend fun getTvAccountStates(
        @Path("series_id") seriesId: Int,
        @Query("session_id") sessionId: String
    ): Response<AccountStatesResponse>

    @POST("tv/{series_id}/rating")
    suspend fun rateTvShow(
        @Path("series_id") seriesId: Int,
        @Query("session_id") sessionId: String,
        @Body body: RateMediaRequest
    ): Response<StatusResponse>

    @DELETE("tv/{series_id}/rating")
    suspend fun deleteTvRating(
        @Path("series_id") seriesId: Int,
        @Query("session_id") sessionId: String
    ): Response<StatusResponse>


    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getTvSeasonDetails(
        @Path("series_id") tvShowId: Int,
        @Path("season_number") season: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail>


    @GET("tv/{id}/credits")
    suspend fun getTvCredits(
        @Path("id") tvId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse>

    @GET("tv/{series_id}/reviews")
    suspend fun getTVReviews(
        @Path("series_id") tvId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>

    @GET("tv/{id}/videos")
    suspend fun getTrailers(
        @Path("id") tvShowId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>

    @GET("tv/{seriesId}/season/{seasonNumber}/episode/{episodeNumber}")
    suspend fun getEpisodeDetails(
        @Path("seriesId") seriesId: Int,
        @Path("seasonNumber") seasonNumber: Int,
        @Path("episodeNumber") episodeNumber: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetEpisodeDetailResponse>

    @GET("tv/{id}/images")
    suspend fun getTvImages(@Path("id") seriesId: Int): Response<GetTVImagesResponse>

    @GET("tv/{id}/watch/providers")
    suspend fun getWatchProviders(@Path("id") tvShowId: Int): Response<GetWatchProvidersResponse>

}