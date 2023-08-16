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
import zw.co.nm.moviedb.ui.adapters.SuggestedMoviesListAdapter
import zw.co.nm.moviedb.ui.viewmodel.MoviesViewModel
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.PageNavUtils


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null
    private var genres: ArrayList<String>? = arrayListOf()
    private var productionCompanies: ArrayList<String>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()

        moviesViewModel.getSimilarMoviesList(movieId!!)
        moviesViewModel.getSimilarMovies.observe(this) { response ->
            val adapter: SuggestedMoviesListAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = response.body()!!.results
            adapter = SuggestedMoviesListAdapter(data)
            binding.recyclerView.adapter = adapter
        }

        moviesViewModel.getMovieDetail(movieId!!)
        moviesViewModel.getMovieDetail.observe(this) {
            val movie = it!!.body
            if (movie.belongsToCollection != null) {
                Toast.makeText(
                    this,
                    "this belongs to a collection",
                    Toast.LENGTH_SHORT
                ).show()


            }
            if (movie == null) {
                Toast.makeText(
                    this@MovieDetailActivity,
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
                AlertDialog.Builder(this@MovieDetailActivity)
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
            movie.genres.forEach { genre ->
                genres!!.add(genre.name)
                binding.genre.text = genres.toString().replace("[", "")
                    .replace("]", "")

            }
            movie.productionCompanies.forEach { productionCompany ->
                productionCompanies!!.add(productionCompany.name)
                binding.prodCompany.text = productionCompanies.toString().replace("[", "")
                    .replace("]", "")
            }
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
                this@MovieDetailActivity,
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
            PageNavUtils.toTrailersPage(this@MovieDetailActivity, movieId!!)
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