package zw.co.nm.moviedb.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.models.Movie
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
            mainViewModel.getPopularMovies(1).collect { response ->
                if (response.isSuccessful) {
                    binding.recyclerView.hasFixedSize()
                    binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    movieList = arrayListOf()
                    for (i in response.body.results.indices) {
                        val fullPosterPath = IMAGE_BASE_URL + response.body.results[i].posterPath
                        val movies = Movie(
                            fullPosterPath, response.body.results[i].title,
                            response.body.results[i].id
                        )
                        movieList.add(movies)
                    }
                    adapter = MovieListAdapter(movieList)
                    binding.recyclerView.adapter = adapter
                }else{
                    Toast.makeText(this@MainActivity, "Mmmm... Network?", Toast.LENGTH_SHORT).show()}
            }
        }

    }

    private fun setUpView() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

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