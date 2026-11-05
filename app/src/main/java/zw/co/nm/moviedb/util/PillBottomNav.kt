package zw.co.nm.moviedb.util

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ViewPillBottomNavBinding
import zw.co.nm.moviedb.presentation.main.movies.HomeActivity
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.settings.SettingsActivity

object PillBottomNav {

    fun setup(activity: AppCompatActivity, nav: ViewPillBottomNavBinding) {
        setupBlur(activity, nav.pillBottomNav)
        nav.navHome.setOnClickListener {
            activity.startActivity(
                Intent(activity, HomeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
            )
        }
        nav.navWatchlist.setOnClickListener {
            if (ConfigStore.isLoggedIn(activity)) {
                PageNavUtils.navWatchlistPage(activity)
            } else {
                Toast.makeText(
                    activity,
                    R.string.login_required_watchlist,
                    Toast.LENGTH_SHORT
                ).show()
                PageNavUtils.navLoginPage(activity)
            }
        }
        nav.navSearch.setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }
        nav.navSettings.setOnClickListener {
            activity.startActivity(Intent(activity, SettingsActivity::class.java))
        }
    }

    @Suppress("DEPRECATION")
    private fun setupBlur(activity: AppCompatActivity, blurView: BlurView) {
        val rootView = activity.window.decorView.findViewById<android.view.ViewGroup>(android.R.id.content)
        val algorithm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RenderEffectBlur()
        } else {
            RenderScriptBlur(activity)
        }
        blurView.setupWith(rootView, algorithm)
            .setFrameClearDrawable(activity.window.decorView.background)
            .setBlurRadius(18f)
            .setOverlayColor(ContextCompat.getColor(activity, R.color.pill_nav_overlay))
    }
}
