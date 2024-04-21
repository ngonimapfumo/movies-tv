package zw.co.nm.moviedb.presentation.tv

import zw.co.nm.moviedb.data.remote.model.response.GetEpisodeDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class TvShowsRepo {
    suspend fun getPopularTvShows(
        page: Int,
        language: String
    ): Response<GetPopularTVSeriesListResponse> =
        apiCall { NetworkManager.tvShowService.getPopularTvShows(page, language) }

    suspend fun getTvShowDetails(showId: Int, language: String): Response<GetTVShowDetailResponse> =
        apiCall { NetworkManager.tvShowService.getTvShowDetails(showId, language) }

    suspend fun getTvCredits(path: Int, language: String): Response<GetTVCreditsResponse> =
        apiCall { NetworkManager.tvShowService.getTvCredits(path, language) }

    suspend fun getEpisodeDetail(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int, language: String
    ): Response<GetEpisodeDetailResponse> =
        apiCall {
            NetworkManager.tvShowService.getEpisodeDetails(
                seriesId = seriesId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                language = language
            )
        }

    suspend fun getTvImages(seriesId: Int): Response<GetTVImagesResponse> =
        apiCall { NetworkManager.tvShowService.getTvImages(seriesId) }
}