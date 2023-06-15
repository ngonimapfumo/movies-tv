package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.ui.adapters.MovieListAdapter
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel
import zw.co.nm.moviedb.ui.search.SearchActivity
import zw.co.nm.moviedb.ui.settings.SettingsActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieListAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        moviesViewModel.getPopularMovies()
        moviesViewModel.getPopularMovies.observe(this) { response ->
            if (response.isSuccessful) {
                val data = response.body()!!.results
                adapter = MovieListAdapter(data)
                binding.recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@MainActivity, "Mmmm... Network?", Toast.LENGTH_SHORT).show()
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