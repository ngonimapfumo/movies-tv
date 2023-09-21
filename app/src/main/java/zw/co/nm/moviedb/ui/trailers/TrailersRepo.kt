package zw.co.nm.moviedb.ui.trailers

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class TrailersRepo {

    suspend fun getTrailers(movieId: Int) =
        apiCall { NetworkManager.movieService.getTrailers(movieId) }

    suspend fun getTVTrailers(tvShowId: Int) =
        apiCall { NetworkManager.tvShowService.getTrailers(tvShowId) }

}