package zw.co.nm.moviedb.utils

import retrofit2.Response

object GeneralUtil {


   inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.network.Response<T> {
        return try {
            zw.co.nm.moviedb.network.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.network.Response.failure(e)
        }
    }
}