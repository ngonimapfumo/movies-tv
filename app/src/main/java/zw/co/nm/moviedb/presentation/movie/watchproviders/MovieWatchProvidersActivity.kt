package zw.co.nm.moviedb.presentation.movie.watchproviders

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toLowerCase
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.databinding.ActivityMovieWatchProvidersBinding
import zw.co.nm.moviedb.presentation.main.movies.MoviesAdapter
import zw.co.nm.moviedb.presentation.movie.MovieDetailActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants.COUNTRY_ISO
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY

class MovieWatchProvidersActivity : AppCompatActivity() {

    private var movieId: Int? = null
    private lateinit var binding: ActivityMovieWatchProvidersBinding
    private lateinit var viewModel: MovieWatchProvidersViewModel
    private lateinit var adapter: ProvidersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovieWatchProvidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[MovieWatchProvidersViewModel::class.java]

        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        viewModel.getMovieWatchProviders(movieId!!)
        viewModel.getMovieWatchProviders.observe(this){

            val exStr = ConfigStore.getStringLang(this,COUNTRY_ISO)
            Toast.makeText(this, exStr!!.formatSpecialCapitalization(), Toast.LENGTH_SHORT).show()
                 when {
                it.body.results.toString().contains(exStr) -> {

                }
                else -> {
                    Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "id"
    }

    private fun String.formatSpecialCapitalization(): String {
        return this.lowercase().let { str ->
            str[0].lowercase() + str.substring(1).uppercase()
        }
    }
}