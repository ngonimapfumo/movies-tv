package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.domain.mappers.TVMapper
import zw.co.nm.moviedb.data.domain.models.TV
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(page: Int): Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }

    suspend fun getTvShowDetails(showId: Int): TV? {
        val req = apiCall { NetworkManager.tvShowService.getTvShowDetails(showId) }
        return if (req.isSuccessful) {
            println("ahhhhhhhhh"+req.body)
            TVMapper.buildFrom(req.body)
        } else null
    }
}