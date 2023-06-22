package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.ui.adapters.CastAdapter
import zw.co.nm.moviedb.ui.adapters.SimilarMoviesListAdapter
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import kotlin.math.roundToInt


class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()

        moviesViewModel.getSimilarMoviesList(movieId!!)
        moviesViewModel.getSimilarMovies.observe(this) { response ->
            val adapter: SimilarMoviesListAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(
                this@MovieActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = response.body()!!.results
            adapter = SimilarMoviesListAdapter(data)
            binding.recyclerView.adapter = adapter
        }

        moviesViewModel.getMovieDetail(movieId!!)
        moviesViewModel.getMovieDetail.observe(this) {
            val response = it.body()!!
            Picasso.get().load(IMAGE_BASE_URL + response.posterPath)
                .placeholder(R.drawable.sample_cover_large).into(binding.backgroundImm)
            binding.movieSummaryTxt.text = response.overview
            binding.detailedSummaryTxt.text = response.overview
            binding.detailedSummaryTxt.setOnClickListener {
                AlertDialog.Builder(this@MovieActivity)
                    .setMessage(response.overview)
                    .show()
            }
            binding.movieTitleTxt.text = response.title
            binding.runtimeTxt.text = buildString {
                append(response.runtime)
                append(" minutes")
            }

            when {
                response.releaseDate.isEmpty() -> {
                    binding.yearTxt.text = "N/A"
                }
                else -> {
                    binding.yearTxt.text = response.releaseDate.substring(0, 4)
                }
            }
            for (i in response.genres.indices) {
                binding.genre.text = response.genres[i].name
            }
            for (i in response.productionCompanies.indices) {
                binding.prodCompany.text = response.productionCompanies[i].name
            }
            binding.movieRatingTxt.text = response.voteAverage.roundToInt().toString()
        }

        moviesViewModel.getCredits(movieId!!)
        moviesViewModel.getMovieCredits.observe(this) { response ->
            val adapter: CastAdapter
            binding.castRecyclerView.layoutManager = LinearLayoutManager(
                this@MovieActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = response.body()!!.cast
            adapter = CastAdapter(data)
            binding.castRecyclerView.adapter = adapter
        }
    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun setUpView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}