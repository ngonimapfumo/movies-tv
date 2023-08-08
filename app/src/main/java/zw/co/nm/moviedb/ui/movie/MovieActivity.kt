package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import android.widget.Toast
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
import zw.co.nm.moviedb.utils.PageNavUtils


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
        moviesViewModel.getMovieDetail.observe(this) { movie ->

            if (movie == null) {
                Toast.makeText(
                    this@MovieActivity,
                    "Mmmm null", Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            Picasso.get().load(IMAGE_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.sample_cover_large).into(binding.backgroundImm)
            if (movie.tagline.isEmpty()) {
                binding.movieSummaryTxt.text = movie.overview
            } else {
                binding.movieSummaryTxt.text = movie.tagline
            }
            binding.detailedSummaryTxt.text = movie.overview
            binding.detailedSummaryTxt.setOnClickListener {
                AlertDialog.Builder(this@MovieActivity)
                    .setMessage(movie.overview)
                    .show()
            }
            binding.movieTitleTxt.text = movie.title
            binding.runtimeTxt.text = buildString {
                append(movie.runtime)
                append(" minutes")
            }

            when {
                movie.releaseDate.isEmpty() -> {
                    binding.yearTxt.text = "N/A"
                }

                else -> {
                    binding.yearTxt.text = movie.releaseDate.substring(0, 4)
                }
            }
            binding.genre.text = movie.genres.name
            binding.prodCompany.text = movie.productionCompanies.name
            binding.movieRatingTxt.text = buildString {
                append((movie.voteAverage * 10).toInt().toString())
                append("%")
                append(" (${movie.voteCount} votes)")
            }
            binding.statusTxt.text = movie.status
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
        binding.trailerBtn.setOnClickListener {
            PageNavUtils.toTrailersPage(this@MovieActivity, movieId!!)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}