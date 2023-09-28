package zw.co.nm.moviedb.presentation.tv

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(page: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }

    suspend fun getTvShowDetails(showId: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTVShowDetailResponse> =
        apiCall { NetworkManager.tvShowService.getTvShowDetails(showId) }

    suspend fun getTvCredits(path: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTVCreditsResponse> =
        apiCall { NetworkManager.tvShowService.getTvCredits(path) }

    suspend fun getEpisodeDetail(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetEpisodeDetailResponse> =
        apiCall {
            NetworkManager.tvShowService.getEpisodeDetails(
                seriesId,
                seasonNumber,
                episodeNumber
            )
        }
}