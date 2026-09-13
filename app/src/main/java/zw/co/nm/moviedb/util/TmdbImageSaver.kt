package zw.co.nm.moviedb.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import zw.co.nm.moviedb.R
import java.io.OutputStream

/**
 * Saves TMDB artwork to the device gallery for personal use.
 *
 * TMDB API ToS allow using images in the app with attribution; they do not prohibit
 * a user-initiated personal save. Images remain copyright of their owners — not for
 * redistribution. Cache of API content must not exceed 6 months per TMDB terms.
 */
object TmdbImageSaver {

    fun savePosterFromPath(
        context: Context,
        filePath: String?,
        displayName: String = "tmdb_poster_${System.currentTimeMillis()}.jpg"
    ) {
        if (filePath.isNullOrBlank()) {
            Toast.makeText(context, R.string.poster_download_unavailable, Toast.LENGTH_SHORT).show()
            return
        }
        val url = Constants.IMAGE_BASE_URL + filePath
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bitmap = Picasso.get().load(url).get()
                val ok = writeBitmap(context, bitmap, displayName)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        if (ok) R.string.poster_download_success else R.string.poster_download_failed,
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (_: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, R.string.poster_download_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun saveFromImageView(
        context: Context,
        imageView: ImageView,
        displayName: String = "tmdb_image_${System.currentTimeMillis()}.jpg"
    ) {
        val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
        if (bitmap == null) {
            Toast.makeText(context, R.string.poster_download_unavailable, Toast.LENGTH_SHORT).show()
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            val ok = writeBitmap(context, bitmap, displayName)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    if (ok) R.string.poster_download_success else R.string.poster_download_failed,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun writeBitmap(context: Context, bitmap: Bitmap, displayName: String): Boolean {
        val resolver = context.contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/TVFilmReviews")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) ?: return false
        return try {
            resolver.openOutputStream(uri)?.use { stream: OutputStream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 92, stream)) return false
            } ?: return false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                resolver.update(uri, values, null, null)
            }
            true
        } catch (_: Exception) {
            resolver.delete(uri, null, null)
            false
        }
    }
}
