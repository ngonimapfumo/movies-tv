package zw.co.nm.moviedb.utils

import retrofit2.Response

object GeneralUtil {


   inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.data.api.Response<T> {
        return try {
            zw.co.nm.moviedb.data.api.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.data.api.Response.failure(e)
        }
    }
}