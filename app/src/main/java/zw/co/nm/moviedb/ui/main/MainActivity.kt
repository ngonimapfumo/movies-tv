package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.ui.adapters.MovieListAdapter
import zw.co.nm.moviedb.ui.search.SearchActivity
import zw.co.nm.moviedb.ui.search.SearchOptionsActivity
import zw.co.nm.moviedb.ui.settings.SettingsActivity
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieListAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        moviesViewModel.getPopularMovies()
        moviesViewModel.getPopularMovies.observe(this) { response ->
            val data = response.body.results
            adapter = MovieListAdapter(data)
            binding.recyclerView.adapter = adapter

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
                    R.id.drawer_version -> {
                        Toast.makeText(
                            this@MainActivity,
                            BuildConfig.VERSION_NAME,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                    R.id.drawer_search -> {
                        startActivity(Intent(this@MainActivity,SearchOptionsActivity::class.java))
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

            R.id.app_bar_settings -> {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}