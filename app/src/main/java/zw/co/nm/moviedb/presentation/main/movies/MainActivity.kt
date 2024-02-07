package zw.co.nm.moviedb.presentation.main.movies


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.presentation.main.tvshows.TVShowsActivity
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.settings.SettingsActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.getThemeConfig
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var toggle: ActionBarDrawerToggle

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

    private fun startUpdate(updateInfo: AppUpdateInfo?) {
        appUpdateManager.startUpdateFlowForResult(updateInfo!!, AppUpdateType.FLEXIBLE, this, 1101)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        moviesViewModel.getPopularMovies()
        moviesViewModel.getPopularMovies.observe(this) { response ->
            binding.progressBar.visibility = GONE
            when (response.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        binding.progressBar.visibility = VISIBLE
                        moviesViewModel.getPopularMovies()
                    }
                }

                else -> {
                    binding.prevB.isEnabled = response.body.page != 1
                    val data = response.body.results
                    adapter = MoviesAdapter(data)
                    binding.recyclerView.adapter = adapter
                }
            }
        }

        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.drawer_settings -> {
                        startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                    }

                    R.id.drawer_tv -> {
                        startActivity(Intent(this@MainActivity, TVShowsActivity::class.java))
                    }

                    R.id.drawer_search -> {
                        startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                    }

                    R.id.drawer_notice -> {
                        AlertDialog.Builder(this@MainActivity).apply {
                            setMessage(R.string.notice_nthis_product_uses_the_tmdb_api_but_is_not_endorsed_or_certified_by_tmdb)
                            setPositiveButton("OKAY", null)
                            show()
                        }
                    }
                }
                true
            }

        }

        try {
            appUpdateManager = AppUpdateManagerFactory.create(this)
            appUpdateManager.registerListener(updateListener)
            checkForUpdate()
        } catch (e: Exception) {
            Log.e("In-app-update-exception", "OnCreate: ", e)
        }
    }

    private fun setUpView() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        AppCompatDelegate.setDefaultNightMode(getThemeConfig(this, "THEME"))
        val tm = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        ConfigStore.saveStringConfig(
            this, "COUNTRY_ISO",
            tm.networkCountryIso.uppercase(Locale.ROOT)
        )

        binding.nextB.setOnClickListener {
            moviesViewModel.page++
            moviesViewModel.getPopularMovies()
        }

        binding.prevB.setOnClickListener {
            if (moviesViewModel.page != 1) {
                moviesViewModel.page--
                moviesViewModel.getPopularMovies()
            } else {
                return@setOnClickListener
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            true
        }

        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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