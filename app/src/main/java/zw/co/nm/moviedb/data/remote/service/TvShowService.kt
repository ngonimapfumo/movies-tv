package zw.co.nm.moviedb.data.remote.service

import retrofit2.http.GET

interface TvShowService {

    @GET()
    suspend fun getPopularTvShows()

}