package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.ui.adapters.MoviesAdapter
import zw.co.nm.moviedb.ui.movie.MoviesViewModel
import zw.co.nm.moviedb.ui.search.SearchActivity
import zw.co.nm.moviedb.ui.settings.SettingsActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var toggle: ActionBarDrawerToggle
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
                    Snackbar.make(binding.root, "Error getting data", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") {
                            moviesViewModel.getPopularMovies()
                        }.show()
                }

                else -> {
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
                }
                true
            }

        }
    }

    private fun setUpView() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
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
}