package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailBinding
    lateinit var movieDetailViewModel: MovieDetailViewModel
    var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        println(movieId)
        lifecycleScope.launch {
            movieDetailViewModel.getMovieDetail(movieId!!).collect {
                Picasso.get().load(IMAGE_BASE_URL + it!!.posterPath).
                placeholder(R.drawable.sample_cover_large).into(binding.backgroundImm)
                binding.movieSummaryTxt.text = it.overview
                binding.movieTitleTxt.text = it.title

            }
        }
    }


    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun setUpView() {
        movieDetailViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)

    }
}