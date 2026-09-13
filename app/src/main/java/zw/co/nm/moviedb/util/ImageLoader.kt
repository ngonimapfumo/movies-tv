package zw.co.nm.moviedb.util

import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R

/**
 * Shared Picasso helpers that always decode bitmaps at target size.
 * Prefer smaller TMDB widths + `.resize()` + `.onlyScaleDown()` to cut RAM.
 *
 * Resize targets are taken from the ImageView when it has a measured size so
 * wrap_content / fixed-dp shelves keep their layout size.
 */
object ImageLoader {

    private const val MAX_BACKDROP_WIDTH = 1080
    private const val MAX_DETAIL_POSTER_WIDTH = 600
    private const val MAX_DETAIL_POSTER_HEIGHT = 900

    /** Previous shelf poster decode size (~270x400 on xxhdpi). */
    private const val DEFAULT_SHELF_POSTER_WIDTH_DP = 112
    private const val DEFAULT_SHELF_POSTER_HEIGHT_DP = 168

    /** Previous posters gallery size (~500x750). */
    private const val DEFAULT_GALLERY_POSTER_WIDTH_DP = 160
    private const val DEFAULT_GALLERY_POSTER_HEIGHT_DP = 240

    fun loadPoster(
        imageView: ImageView,
        path: String?,
        widthPx: Int = 0,
        heightPx: Int = 0,
        placeholder: Int = R.drawable.sample_cover_small
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val (w, h) = resolveSize(
            imageView,
            widthPx,
            heightPx,
            DEFAULT_GALLERY_POSTER_WIDTH_DP,
            DEFAULT_GALLERY_POSTER_HEIGHT_DP
        )
        Picasso.get()
            .load(Constants.IMAGE_BASE_URL + path)
            .resize(w, h)
            .onlyScaleDown()
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadLowResPoster(
        imageView: ImageView,
        path: String?,
        widthPx: Int = 0,
        heightPx: Int = 0,
        placeholder: Int = R.drawable.sample_cover_small
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val (w, h) = resolveSize(
            imageView,
            widthPx,
            heightPx,
            DEFAULT_SHELF_POSTER_WIDTH_DP,
            DEFAULT_SHELF_POSTER_HEIGHT_DP
        )
        Picasso.get()
            .load(Constants.MED_RES_IMAGE_BASE_URL + path)
            .resize(w, h)
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadDetailPoster(
        imageView: ImageView,
        path: String?,
        widthPx: Int = MAX_DETAIL_POSTER_WIDTH,
        heightPx: Int = MAX_DETAIL_POSTER_HEIGHT,
        placeholder: Int = R.drawable.sample_cover_large_exp
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val w = widthPx.coerceAtMost(MAX_DETAIL_POSTER_WIDTH)
        val h = heightPx.coerceAtMost(MAX_DETAIL_POSTER_HEIGHT)
        Picasso.get()
            .load(Constants.MED_RES_IMAGE_BASE_URL + path)
            .resize(w, h)
            .onlyScaleDown()
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadCast(
        imageView: ImageView,
        path: String?,
        placeholder: Int = R.drawable.sample_cover_small
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        Picasso.get()
            .load(Constants.LOW_RES_IMAGE_BASE_URL + path)
            .resize(160, 240)
            .onlyScaleDown()
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadBackdrop(
        imageView: ImageView,
        path: String?,
        widthPx: Int,
        heightPx: Int,
        placeholder: Int = R.drawable.sample_cover_large_exp,
        skipMemoryCache: Boolean = true
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val w = widthPx.coerceAtMost(MAX_BACKDROP_WIDTH)
        val h = heightPx.coerceAtMost((w * 9f / 16f).toInt().coerceAtLeast(1))
        var request = Picasso.get()
            .load(Constants.BACKDROP_IMAGE_BASE_URL + path)
            .resize(w, h)
            .onlyScaleDown()
            .centerCrop()
            .placeholder(placeholder)
        if (skipMemoryCache) {
            request = request.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
        }
        request.into(imageView)
    }

    fun loadStill(
        imageView: ImageView,
        path: String?,
        widthPx: Int = 640,
        heightPx: Int = 360,
        placeholder: Int = R.drawable.sample_episode_exp
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val (w, h) = resolveSize(imageView, widthPx, heightPx, 160, 90)
        Picasso.get()
            .load(Constants.MED_RES_IMAGE_BASE_URL + path)
            .resize(w, h)
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadLandscapeThumb(
        imageView: ImageView,
        path: String?,
        placeholder: Int = R.drawable.sample_episode_exp
    ) {
        if (path.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        val (w, h) = resolveSize(imageView, 0, 0, 160, 90)
        Picasso.get()
            .load(Constants.BACKDROP_IMAGE_BASE_URL + path)
            .resize(w, h)
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
    }

    fun loadLogo(
        imageView: ImageView,
        url: String?,
        widthPx: Int = 400,
        heightPx: Int = 160,
        placeholder: Int = R.drawable.sample_cover_small
    ) {
        if (url.isNullOrBlank()) {
            imageView.setImageResource(placeholder)
            return
        }
        Picasso.get()
            .load(url)
            .resize(widthPx, heightPx)
            .onlyScaleDown()
            .centerInside()
            .into(imageView)
    }

    fun loadYoutubeThumb(
        imageView: ImageView,
        videoKey: String,
        widthPx: Int = 320,
        heightPx: Int = 180
    ) {
        Picasso.get()
            .load("https://img.youtube.com/vi/$videoKey/mqdefault.jpg")
            .resize(widthPx, heightPx)
            .onlyScaleDown()
            .centerCrop()
            .into(imageView)
    }

    fun cancel(imageView: ImageView) {
        Picasso.get().cancelRequest(imageView)
        imageView.setImageDrawable(null)
    }

    private fun resolveSize(
        imageView: ImageView,
        widthPx: Int,
        heightPx: Int,
        fallbackWidthDp: Int,
        fallbackHeightDp: Int
    ): Pair<Int, Int> {
        val density = imageView.resources.displayMetrics.density
        val measuredW = imageView.width.takeIf { it > 0 }
            ?: imageView.layoutParams?.width?.takeIf { it > 0 }
        val measuredH = imageView.height.takeIf { it > 0 }
            ?: imageView.layoutParams?.height?.takeIf { it > 0 }
        val w = when {
            widthPx > 0 -> widthPx
            measuredW != null -> measuredW
            else -> (fallbackWidthDp * density).toInt()
        }
        val h = when {
            heightPx > 0 -> heightPx
            measuredH != null -> measuredH
            else -> (fallbackHeightDp * density).toInt()
        }
        return w.coerceAtLeast(1) to h.coerceAtLeast(1)
    }
}
