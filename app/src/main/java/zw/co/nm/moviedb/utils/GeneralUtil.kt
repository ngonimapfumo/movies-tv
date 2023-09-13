package zw.co.nm.moviedb.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response

object GeneralUtil {

    inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.data.remote.Response<T> {
        return try {
            zw.co.nm.moviedb.data.remote.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.data.remote.Response.failure(e)
        }
    }

    fun generalAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveDialogInterface: DialogInterface.OnClickListener?,
        negativeDialogInterface: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText, positiveDialogInterface)
            .setNegativeButton(negativeButtonText, negativeDialogInterface)
            .show()
    }

    fun displayGenToast(view: View, message: String) {

    }


}