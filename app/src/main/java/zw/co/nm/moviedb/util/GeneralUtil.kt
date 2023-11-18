package zw.co.nm.moviedb.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.view.View
import android.view.View.OnClickListener
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    fun actionSnack(view: View, msg: String, actionMsg: String, listener: OnClickListener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction(actionMsg) {
                listener.onClick(it)
            }.show()
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    @Composable
    fun gradientBackground(isverticalGrad: Boolean, colors: List<Color>): Brush {
        val endOffset = if (isverticalGrad) {
            Offset(0f, Float.POSITIVE_INFINITY)
        } else {
            Offset(Float.POSITIVE_INFINITY, 0f)
        }
        return Brush.linearGradient(
            colors = colors,
            start = Offset.Zero,
            end = endOffset
        )
    }

}