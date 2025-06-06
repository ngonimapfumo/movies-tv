package zw.co.nm.moviedb.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityPostersImagesBinding
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
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) {
                view, insets, ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            binding.main.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }


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