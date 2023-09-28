package zw.co.nm.moviedb.presentation.tv

import zw.co.nm.moviedb.data.remote.model.response.GetEpisodeDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(page: Int): Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page) }

    suspend fun getTvShowDetails(showId: Int): Response<GetTVShowDetailResponse> =
        apiCall { NetworkManager.tvShowService.getTvShowDetails(showId) }

    suspend fun getTvCredits(path: Int): Response<GetTVCreditsResponse> =
        apiCall { NetworkManager.tvShowService.getTvCredits(path) }

    suspend fun getEpisodeDetail(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Response<GetEpisodeDetailResponse> =
        apiCall {
            NetworkManager.tvShowService.getEpisodeDetails(
                seriesId,
                seasonNumber,
                episodeNumber
            )
        }
}