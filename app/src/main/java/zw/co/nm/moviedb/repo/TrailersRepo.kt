package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager

class TrailersRepo {


    suspend fun getTrailers(movieId: Int) = NetworkManager.apiService.getTrailers(movieId)

}