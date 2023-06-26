package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.GetPopularTVSeriesListResponse

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("page") page:Int):Response<GetPopularTVSeriesListResponse>

}