package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): Response<GetPopularTVSeriesListResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") tvShowId: Int
    ): Response<GetTVShowDetailResponse>

}