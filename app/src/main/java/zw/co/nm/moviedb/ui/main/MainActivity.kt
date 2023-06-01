package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.ui.adapters.MovieListAdapter
import zw.co.nm.moviedb.ui.search.SearchActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieListAdapter
    private lateinit var mainViewModel: MainViewModel
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.getPopularMovies(page).collect(::process)
            }
        }

    }

    private fun process(response: Response<GetPopularMoviesListResponse>) {
        if (response.isSuccessful) {
            val data = response.data!!.body()!!.results
            adapter = MovieListAdapter(data)
            binding.recyclerView.adapter = adapter
        } else {
            Toast.makeText(this@MainActivity, "Mmmm... Network?", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpView() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.nextB.setOnClickListener {
            page++
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    mainViewModel.getPopularMovies(page).collect(::process)
                }
            }
            binding.prevB.setOnClickListener {
                if (page != 1) {
                    page--
                    lifecycleScope.launch {
                        mainViewModel.getPopularMovies(page).collect(::process)
                    }
                } else {
                    return@setOnClickListener
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.app_bar_search) {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}