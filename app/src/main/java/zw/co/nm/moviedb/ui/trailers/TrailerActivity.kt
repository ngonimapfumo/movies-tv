package zw.co.nm.moviedb.ui.trailers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityTrailerBinding
import zw.co.nm.moviedb.ui.adapters.TrailersAdapter
import zw.co.nm.moviedb.ui.viewmodels.TrailersViewModel

class TrailerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTrailerBinding
    lateinit var trailersViewModel: TrailersViewModel
    private lateinit var adapter: TrailersAdapter
    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        trailersViewModel = ViewModelProvider(this)[TrailersViewModel::class.java]
        trailersViewModel.getTrailers(movieId!!)
        trailersViewModel.getTrailers.observe(this) { response ->
            val data = response.body()!!.results
            adapter = TrailersAdapter(data)
            binding.trailerRecycler.adapter = adapter

        }

    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }
}