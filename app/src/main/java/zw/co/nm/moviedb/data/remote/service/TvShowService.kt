package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPopularTVSeriesListResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") tvShowId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTVShowDetailResponse>


    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getTvSeasonDetails(
        @Path("series_id") tvShowId: Int,
        @Path("season_number") season: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTvSeasonDetail>


    @GET("tv/{id}/credits")
    suspend fun getTvCredits(
        @Path("id") tvId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTVCreditsResponse>

    @GET("tv/{series_id}/reviews")
    suspend fun getTVReviews(
        @Path("series_id") tvId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReviewsResponse>

    @GET("tv/{id}/videos")
    suspend fun getTrailers(
        @Path("id") tvShowId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTrailersResponse>

    @GET("tv/{seriesId}/season/{seasonNumber}/episode/{episodeNumber}")
    suspend fun getEpisodeDetails(
        @Path("seriesId") seriesId: Int,
        @Path("seasonNumber") seasonNumber: Int,
        @Path("episodeNumber") episodeNumber: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetEpisodeDetailResponse>

}