package zw.co.nm.moviedb.utils

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
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