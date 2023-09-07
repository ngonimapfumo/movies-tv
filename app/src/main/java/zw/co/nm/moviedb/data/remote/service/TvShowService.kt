package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTrailersResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvSeasonDetail
import zw.co.nm.moviedb.data.remote.networkmodel.GetReviewsResponse

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): Response<GetPopularTVSeriesListResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") tvShowId: Int
    ): Response<GetTVShowDetailResponse>


    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getTvSeasonDetails(
        @Path("series_id") tvShowId: Int,
        @Path("season_number") season: Int
    ): Response<GetTvSeasonDetail>


    @GET("tv/{id}/credits")
    suspend fun getTvCredits(
        @Path("id") tvId: Int
    ): Response<GetTVCreditsResponse>

    @GET("tv/{series_id}/reviews")
    suspend fun getTVReviews(
        @Path("series_id") tvId: Int
    ): Response<GetReviewsResponse>

    @GET("tv/{id}/videos")
    suspend fun getTrailers(
        @Path("id") tvShowId: Int
    ): Response<GetTrailersResponse>


}