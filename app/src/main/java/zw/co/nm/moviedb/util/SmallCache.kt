package zw.co.nm.moviedb.util

import zw.co.nm.moviedb.data.remote.Response

object SmallCache {

    val cacheMap =
        mutableMapOf<Int, Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse>>()
}