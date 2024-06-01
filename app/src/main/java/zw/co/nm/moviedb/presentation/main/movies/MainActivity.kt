package zw.co.nm.moviedb.presentation.main.movies


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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
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
import zw.co.nm.moviedb.presentation.movie.MovieGenresAdapter
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.settings.SettingsActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.getThemeConfig
import zw.co.nm.moviedb.util.Constants.COUNTRY_ISO
import zw.co.nm.moviedb.util.Constants.DISPLAY_METRICS_WIDTH
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var genreId: Int? = null
    private var identifier: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpView()
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val bundle = intent.extras
        genreId = bundle!!.getInt("genre_id")
        identifier = bundle.getString("identifier")


        when {
            identifier!!.equals("from_genre", true) -> {
                moviesViewModel.getMoviesByGenreId(genreId!!)
                moviesViewModel.getMovieByGenreId.observe(this) {
                    binding.progressBar.visibility = GONE

                    when(it.data){
                        null->{
                            actionSnack(binding.root,"Error getting data","Retry"){
                                binding.progressBar.visibility = VISIBLE
                                moviesViewModel.getMoviesByGenreId(genreId!!)
                            }
                        }else->{
                        binding.prevB.isEnabled = it.body.page != 1
                        val data = it.body.results
                        adapter = MoviesAdapter(data)
                        binding.recyclerView.adapter = adapter
                        }
                    }

                }


            }

            else -> {
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

            }
        }

    }

    private fun setUpView() {
        AppCompatDelegate.setDefaultNightMode(getThemeConfig(this, "THEME"))
        binding.nextB.setOnClickListener {
            moviesViewModel.page++
            when {
                identifier!!.equals("from_genre",true) -> {
                    moviesViewModel.getMoviesByGenreId(genreId!!)
                }
                else -> {moviesViewModel.getPopularMovies()}
            }

        }
        binding.prevB.setOnClickListener {
            if (moviesViewModel.page != 1) {
                moviesViewModel.page--
                if (identifier!!.equals("from_genre",true)){moviesViewModel.getMoviesByGenreId(genreId!!)}
                else{moviesViewModel.getPopularMovies()}

            } else {
                return@setOnClickListener
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}