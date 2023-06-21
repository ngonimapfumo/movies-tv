package zw.co.nm.moviedb.utils

import retrofit2.Response
import zw.co.nm.moviedb.data.remote.*

object GeneralUtil {

    inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.data.remote.Response<T> {
        return try {
            zw.co.nm.moviedb.data.remote.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.data.remote.Response.failure(e)
        }
    }


}