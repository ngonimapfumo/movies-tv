package zw.co.nm.moviedb.util

import zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.util.Response

object SmallCache {

    val cacheMap =
        mutableMapOf<Int, Response<GetMovieDetailResponse>>()
}