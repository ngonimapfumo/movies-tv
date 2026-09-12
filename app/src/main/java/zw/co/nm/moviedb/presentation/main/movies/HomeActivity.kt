package zw.co.nm.moviedb.presentation.main.movies

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Display
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.databinding.ActivityHomeBinding
import zw.co.nm.moviedb.presentation.main.tvshows.TVShowsActivity
import zw.co.nm.moviedb.presentation.movie.MovieGenresAdapter
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.settings.SettingsActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.BACKDROP_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MovieGenresAdapter
    private lateinit var movieAdapter: MoviesAdapter

    private lateinit var appUpdateManager: AppUpdateManager
    private var updateAvailable = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var updateInfo: AppUpdateInfo? = null
    private var updateListener = InstallStateUpdatedListener { state: InstallState ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            updateStatusSnack()
        }
    }

    private fun updateStatusSnack() {
        try {
            Snackbar.make(
                binding.homeRoot,
                "An update has just been downloaded",
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction("RESTART") { appUpdateManager.completeUpdate() }
                .setActionTextColor(Color.parseColor("#ffff4444"))
                .show()
        } catch (e: Exception) {
            Log.e(getString(R.string.in_app_update_exception), "updateStatusSnack: ", e)
        }
    }

    private fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { update ->
            if (update.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                update.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                updateInfo = update
                updateAvailable.value = true
                startUpdate(updateInfo)
            }
        }
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            when (val resultCode = result.resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(
                        this@HomeActivity,
                        "Update successful", Toast.LENGTH_SHORT
                    ).show()
                }

                RESULT_CANCELED -> {
                    Toast.makeText(
                        this@HomeActivity,
                        "Update canceled", Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    Log.v("MyActivity", "Update flow failed with resultCode:$resultCode")
                }
            }
        }

    private fun startUpdate(updateInfo: AppUpdateInfo?) {
        appUpdateManager.startUpdateFlowForResult(
            updateInfo!!,
            activityResultLauncher,
            AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.homeRoot) { _, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            binding.s.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom + resources.getDimensionPixelSize(R.dimen.home_bottom_nav_content_inset)
            )
            val lp = binding.glassBottomNav.layoutParams as ConstraintLayout.LayoutParams
            lp.bottomMargin =
                bars.bottom + resources.getDimensionPixelSize(R.dimen.home_bottom_nav_margin)
            binding.glassBottomNav.layoutParams = lp
            insets
        }

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        binding.shimmer.startShimmer()
        setupGlassBottomNav()
        setupShelfMoreLinks()
        loadHomeShelves()
        configurations()
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNav.menu.findItem(R.id.nav_home)?.isChecked = true
    }

    private fun setupGlassBottomNav() {
        val rootView = binding.homeRoot as ViewGroup
        val windowBackground: Drawable? = window.decorView.background
        binding.glassBottomNav.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(18f)
            .setBlurAutoUpdate(true)
        binding.glassBottomNav.clipToOutline = true

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    binding.s.smoothScrollTo(0, 0)
                    true
                }

                R.id.nav_tv -> {
                    startActivity(Intent(this, TVShowsActivity::class.java))
                    false
                }

                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    false
                }

                R.id.nav_library -> {
                    openLibrary()
                    false
                }

                else -> false
            }
        }
        binding.bottomNav.selectedItemId = R.id.nav_home
    }

    private fun openLibrary() {
        if (ConfigStore.isLoggedIn(this)) {
            PageNavUtils.navWatchlistPage(this)
        } else {
            Toast.makeText(this, R.string.login_required_watchlist, Toast.LENGTH_SHORT).show()
            PageNavUtils.navLoginPage(this)
        }
    }

    private fun loadHomeShelves() {
        viewModel.page = 1
        viewModel.getPopularMovies()
        viewModel.getPopularMovies.observe(this) {
            when (it.data) {
                null -> {
                    binding.heroLayout.visibility = GONE
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        loadHomeShelves()
                    }
                }

                else -> {
                    val data = it.body.results
                    bindFeaturedHero(data)
                    binding.recyclerHome.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    movieAdapter = MoviesAdapter(data)
                    binding.recyclerHome.adapter = movieAdapter
                }
            }
        }

        viewModel.getMovieGenres()
        viewModel.getMovieGenres.observe(this) {
            when (it.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        viewModel.getMovieGenres()
                    }
                }

                else -> {
                    binding.recyclerView.visibility = VISIBLE
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = GONE
                    binding.recyclerView.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    adapter = MovieGenresAdapter(
                        it.body.genres.orEmpty()
                            .filterNotNull()
                            .filter { genre -> genre.id != null && !genre.name.isNullOrBlank() }
                    )
                    binding.recyclerView.adapter = adapter
                }
            }
        }

        viewModel.getTrending()
        viewModel.getTrending.observe(this) {
            if (it.data != null) {
                binding.recyclerTrending.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                binding.recyclerTrending.adapter = TrendingAdapter(it.body.results)
            }
        }

        viewModel.getNowPlaying()
        viewModel.getNowPlaying.observe(this) {
            if (it.data != null) {
                binding.recyclerNowPlaying.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                binding.recyclerNowPlaying.adapter = MoviesAdapter(it.body.results)
            }
        }

        viewModel.getUpcoming()
        viewModel.getUpcoming.observe(this) {
            if (it.data != null) {
                binding.recyclerUpcoming.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                binding.recyclerUpcoming.adapter = MoviesAdapter(it.body.results)
            }
        }
    }

    private fun setupShelfMoreLinks() {
        binding.trendingMore.setOnClickListener {
            openHub("trending", getString(R.string.trending_today))
        }
        binding.nowPlayingMore.setOnClickListener {
            openHub("now_playing", getString(R.string.now_playing))
        }
        binding.upcomingMore.setOnClickListener {
            openHub("upcoming", getString(R.string.upcoming))
        }
        binding.moreText.setOnClickListener {
            startActivity(Intent(this, MainListActivity::class.java))
        }
    }

    private fun openHub(identifier: String, title: String) {
        startActivity(
            Intent(this, MainListActivity::class.java).apply {
                putExtra("identifier", identifier)
                putExtra("title", title)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                true
            }

            R.id.app_bar_settings -> {
                startActivity(Intent(this@HomeActivity, SettingsActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindFeaturedHero(movies: List<GetPopularMoviesListResponse.Result>) {
        val featured = movies
            .filter { !it.backdropPath.isNullOrBlank() }
            .maxByOrNull { it.voteAverage }
            ?: movies.firstOrNull()

        if (featured == null) {
            binding.heroLayout.visibility = GONE
            return
        }

        binding.heroLayout.visibility = VISIBLE
        binding.heroTitle.text = featured.title.orEmpty()
        binding.heroOverview.text = featured.overview.orEmpty()
        binding.heroOverview.visibility =
            if (featured.overview.isNullOrBlank()) GONE else VISIBLE

        val year = try {
            val releaseDate = featured.releaseDate
            if (!releaseDate.isNullOrBlank()) {
                LocalDate.parse(releaseDate).year.toString()
            } else {
                ""
            }
        } catch (_: Exception) {
            ""
        }
        val rating = "${(featured.voteAverage * 10).toInt()}%"
        binding.heroMeta.text = listOf(year, rating)
            .filter { it.isNotBlank() }
            .joinToString(" · ")

        val imageUrl = when {
            !featured.backdropPath.isNullOrBlank() ->
                BACKDROP_IMAGE_BASE_URL + featured.backdropPath
            !featured.posterPath.isNullOrBlank() ->
                Constants.IMAGE_BASE_URL + featured.posterPath
            else -> null
        }
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.sample_cover_large_exp)
                .into(binding.heroImage)
        } else {
            binding.heroImage.setImageResource(R.drawable.sample_cover_large_exp)
        }

        val openFeatured = {
            PageNavUtils.navMovieDetailsPage(this@HomeActivity, featured.id)
        }
        binding.heroCta.setOnClickListener { openFeatured() }
        binding.heroLayout.setOnClickListener { openFeatured() }
    }

    private fun configurations() {
        AppCompatDelegate.setDefaultNightMode(ConfigStore.getThemeConfig(this, "THEME"))
        try {
            appUpdateManager = AppUpdateManagerFactory.create(this)
            appUpdateManager.registerListener(updateListener)
            checkForUpdate()
        } catch (e: Exception) {
            Log.e("In-app-update-exception", "OnCreate: ", e)
        }

        val displayMetrics =
            this.getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)
        ConfigStore.saveIntConfig(
            this,
            Constants.DISPLAY_METRICS_WIDTH,
            displayMetrics!!.mode.physicalWidth
        )

        val tm = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val countryIsoValue = tm.networkCountryIso
        ConfigStore.saveStringConfig(this, Constants.COUNTRY_ISO, countryIsoValue)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            appUpdateManager.unregisterListener(updateListener)
        } catch (e: java.lang.Exception) {
            Log.e("In-app-update-exception", "OnDestroy: ", e)
        }
    }
}
