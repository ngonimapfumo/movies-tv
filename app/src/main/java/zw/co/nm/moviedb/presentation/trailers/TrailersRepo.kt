package zw.co.nm.moviedb.presentation.trailers

import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class TrailersRepo {

    suspend fun getTrailers(movieId: Int, language: String) =
        apiCall { NetworkManager.movieService.getTrailers(movieId, language) }

    suspend fun getTVTrailers(tvShowId: Int, language: String) =
        apiCall { NetworkManager.tvShowService.getTrailers(tvShowId, language) }

}