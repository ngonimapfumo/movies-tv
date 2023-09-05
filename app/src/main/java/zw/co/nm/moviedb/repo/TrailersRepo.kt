package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager

class TrailersRepo {

    suspend fun getTrailers(movieId: Int) = NetworkManager.movieService.getTrailers(movieId)
    suspend fun getTVTrailers(tvShowId: Int) = NetworkManager.tvShowService.getTrailers(tvShowId)

}