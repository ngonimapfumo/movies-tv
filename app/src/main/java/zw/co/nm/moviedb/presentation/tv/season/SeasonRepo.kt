package zw.co.nm.moviedb.presentation.tv.season

import zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil

class SeasonRepo {
    suspend fun getSeasonDetail(
        tvShowId: Int,
        season: Int,
        language: String
    ): Response<GetTvSeasonDetail> =
        GeneralUtil.apiCall {
            NetworkManager.tvShowService.getTvSeasonDetails(
                tvShowId,
                season,
                language
            )
        }

}