package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.models.Movie
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.ui.adapters.MovieListAdapter
import zw.co.nm.moviedb.ui.search.SearchActivity
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MovieListAdapter
    private lateinit var mainViewModel: MainViewModel
    lateinit var movieList: ArrayList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        lifecycleScope.launch {
            mainViewModel.getPopularMovies(1).collect(::process)
        }

    }

    private fun setUpView() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    private fun process(response: GetPopularMoviesListResponse?) {
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
        movieList = arrayListOf()
        for (i in response?.results!!.indices) {
            val fullPosterPath = IMAGE_BASE_URL + response.results[i].posterPath
            val movies = Movie(
                fullPosterPath, response.results[i].title,
                response.results[i].id
            )
            movieList.add(movies)
        }
        adapter = MovieListAdapter(movieList)
        binding.recyclerView.adapter = adapter

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