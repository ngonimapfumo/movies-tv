package zw.co.nm.moviedb.presentation.movie.posters

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityPostersImagesBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.util.GeneralUtil

class PostersImagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostersImagesBinding
    private lateinit var adapter: PosterAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostersImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID, 0)
        moviesViewModel.getMovieImages(movieId!!)
        moviesViewModel.getMovieImages.observe(this) { movie ->
            binding.progressBar.visibility = View.GONE
            when (movie!!.data) {
                null -> {
                    GeneralUtil.actionSnack(binding.root, "Error getting data", "Retry") {
                        binding.progressBar.visibility = View.VISIBLE
                        moviesViewModel.getMovieImages(movieId!!)
                    }
                }

                else -> {
                    //    Toast.makeText(this, movie.body.posters[0].iso6391, Toast.LENGTH_SHORT).show()

                    val data = movie.body.posters
                    adapter = PosterAdapter(data)
                    binding.postersRecycler.adapter = adapter
                }
            }

        }
    }


    companion object {
        const val MOVIE_ID: String = "movieId"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

}