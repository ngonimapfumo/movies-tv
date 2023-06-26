package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.model.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class TvShowsRepo {


    suspend fun getPopularTvShows(page: Int): zw.co.nm.moviedb.data.remote.Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }
}