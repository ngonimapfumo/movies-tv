package zw.co.nm.moviedb.presentation.tv.season

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil

class SeasonRepo {
    suspend fun getSeasonDetail(
        tvShowId: Int,
        season: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTvSeasonDetail> =
        GeneralUtil.apiCall { NetworkManager.tvShowService.getTvSeasonDetails(tvShowId, season) }

}