package zw.co.nm.moviedb.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.view.View
import android.view.View.OnClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response


object GeneralUtil {

    inline fun <T> apiCall(apiCall: () -> Response<T>): zw.co.nm.moviedb.data.remote.util.Response<T> {
        return try {
            zw.co.nm.moviedb.data.remote.util.Response.success(apiCall.invoke())
        } catch (e: Exception) {
            zw.co.nm.moviedb.data.remote.util.Response.failure(e)
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
    fun actionDialog(context: Context,
                     positiveDialogInterface: DialogInterface.OnClickListener?){
        MaterialAlertDialogBuilder(context)
            .setTitle("Alert")
            .setMessage("Error getting data, Please try again")
            .setCancelable(false)
            .setPositiveButton("Retry", positiveDialogInterface)
            .show()


    }


    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun showGenericDialog(context: Context, message: String, positiveButtonText: String) {
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(positiveButtonText, null)
            .setMessage(message)
            .show()
    }

}