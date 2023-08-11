package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(page: Int): Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }

    suspend fun getTvShowDetails(showId: Int): Response<GetTVShowDetailResponse> =
        apiCall { NetworkManager.tvShowService.getTvShowDetails(showId) }

    suspend fun getTvCredits(path: Int): Response<GetTVCreditsResponse> =
        apiCall { NetworkManager.tvShowService.getTvCredits(path) }
}