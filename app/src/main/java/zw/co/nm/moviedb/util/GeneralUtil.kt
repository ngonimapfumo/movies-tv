package zw.co.nm.moviedb.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

    fun actionSnack(view: View, msg: String, actionMsg: String, listener: OnClickListener) {
        val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction(actionMsg) {
                listener.onClick(it)
            }
        // Snackbar is added to the window content, outside the padded page.
        // Samsung often reports the nav bar only via navigationBars / gesture
        // insets, so Material's systemWindowInsetBottom stays 0 and the bar
        // draws underneath the 3-button or gesture navigation bar.
        ViewCompat.setOnApplyWindowInsetsListener(snackbar.view) { snackbarView, insets ->
            liftSnackBarAboveSystemBars(snackbarView, insets)
            insets
        }
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onShown(transientBottomBar: Snackbar) {
                val insets = ViewCompat.getRootWindowInsets(transientBottomBar.view) ?: return
                liftSnackBarAboveSystemBars(transientBottomBar.view, insets)
            }
        })
        snackbar.show()
    }

    private fun liftSnackBarAboveSystemBars(snackbarView: View, insets: WindowInsetsCompat) {
        val bottom = maxOf(
            insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom,
            insets.getInsets(WindowInsetsCompat.Type.systemGestures()).bottom,
            insets.getInsets(WindowInsetsCompat.Type.mandatorySystemGestures()).bottom,
            insets.getInsets(WindowInsetsCompat.Type.tappableElement()).bottom
        )
        val params = snackbarView.layoutParams as? ViewGroup.MarginLayoutParams ?: return
        val gap = (8 * snackbarView.resources.displayMetrics.density).toInt()
        val margin = bottom + gap
        if (params.bottomMargin != margin) {
            params.bottomMargin = margin
            snackbarView.layoutParams = params
        }
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