package zw.co.nm.moviedb.presentation.movie.watchproviders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityWatchProvidersBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel

class WatchProvidersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchProvidersBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchProvidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("mId", 0)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.getWatchProviders(movieId!!)
        moviesViewModel.getWatchProviders.observe(this) {

//start from here

        }
    }
}