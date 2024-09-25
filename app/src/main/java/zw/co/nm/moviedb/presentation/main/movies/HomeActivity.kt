package zw.co.nm.moviedb.presentation.main.movies

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Display
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
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
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityHomeBinding
import zw.co.nm.moviedb.presentation.main.tvshows.TVShowsActivity
import zw.co.nm.moviedb.presentation.movie.MovieGenresAdapter
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.settings.SettingsActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MovieGenresAdapter
    private lateinit var movieAdapter: MoviesAdapter
    private var toggle: ActionBarDrawerToggle? = null

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
                binding.drawerLayout,
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
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        when (val resultCode = result.resultCode) {
            Activity.RESULT_OK -> {
                Toast.makeText(this@HomeActivity,
                    "Update successful", Toast.LENGTH_SHORT).show()
            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this@HomeActivity,
                    "Update canceled", Toast.LENGTH_SHORT).show()
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

        binding = ActivityHomeBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        binding.shimmer.startShimmer()
        viewModel.getPopularMovies()
        viewModel.getPopularMovies.observe(this){

            when (it.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        viewModel.getPopularMovies()
                        viewModel.getMovieGenres()
                    }
                }
                else -> {
                    val data = it.body.results
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
                        viewModel.getPopularMovies()
                    }
                }
                else -> {
                    binding.recyclerView.visibility = VISIBLE
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = GONE
                    val data = it.body.genres
                    binding.recyclerView.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    adapter = MovieGenresAdapter(data)
                    binding.recyclerView.adapter = adapter
                }
            }

            binding.apply {
                toggle = ActionBarDrawerToggle(
                    this@HomeActivity,
                    drawerLayout,
                    R.string.open,
                    R.string.close
                )
                drawerLayout.addDrawerListener(toggle!!)
                toggle!!.syncState()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                navView.setNavigationItemSelectedListener {menu->
                    when (menu.itemId) {
                        R.id.drawer_settings -> {
                            startActivity(Intent(this@HomeActivity, SettingsActivity::class.java))
                        }

                        R.id.drawer_tv -> {
                            startActivity(Intent(this@HomeActivity, TVShowsActivity::class.java))
                        }

                        R.id.drawer_search -> {
                            startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                        }

                        R.id.drawer_movies -> {
                            startActivity(Intent(this@HomeActivity, MainListActivity::class.java))
                        }
                    }
                    true
                }

            }


        }

        binding.moreText.setOnClickListener { startActivity(Intent(this,MainListActivity::class.java)) }
        configurations()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle!!.onOptionsItemSelected(item)) {
         return true
        }

        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun configurations(){
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
        ConfigStore.saveIntConfig(this, Constants.DISPLAY_METRICS_WIDTH, displayMetrics!!.mode.physicalWidth)


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