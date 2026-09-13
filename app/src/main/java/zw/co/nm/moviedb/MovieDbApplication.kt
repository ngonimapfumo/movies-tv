package zw.co.nm.moviedb

import android.app.Application
import android.content.ComponentCallbacks2
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.BuildConfig

class MovieDbApplication : Application() {

    private lateinit var picassoMemoryCache: LruCache

    override fun onCreate() {
        super.onCreate()
        picassoMemoryCache = LruCache(calculatePicassoCacheBytes())
        Picasso.setSingletonInstance(
            Picasso.Builder(this)
                .memoryCache(picassoMemoryCache)
                .loggingEnabled(BuildConfig.DEBUG)
                .build()
        )
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (::picassoMemoryCache.isInitialized &&
            level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN
        ) {
            picassoMemoryCache.clear()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (::picassoMemoryCache.isInitialized) {
            picassoMemoryCache.clear()
        }
    }

    private fun calculatePicassoCacheBytes(): Int {
        val maxHeap = Runtime.getRuntime().maxMemory()
        // ~8% of heap, clamped so list posters fit without starving the app.
        return (maxHeap / 12).toInt().coerceIn(4 * 1024 * 1024, 12 * 1024 * 1024)
    }
}
