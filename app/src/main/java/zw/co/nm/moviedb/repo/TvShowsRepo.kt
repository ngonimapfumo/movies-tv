package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvShowDetailResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(page: Int): Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }

    suspend fun getTvShowDetails(showId: Int): Response<GetTvShowDetailResponse>? =
        apiCall { NetworkManager.tvShowService.getTvShowDetails(showId)}
}