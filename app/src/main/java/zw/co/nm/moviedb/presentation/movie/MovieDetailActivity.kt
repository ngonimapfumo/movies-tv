package zw.co.nm.moviedb.presentation.movie

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.CastAdapter
import zw.co.nm.moviedb.adapters.SuggestedMoviesListAdapter
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.Constants.THEATRICAL
import zw.co.nm.moviedb.util.Constants.THEATRICAL_LIMITED
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate


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
        moviesViewModel.getSimilarMovies.observe(this) {
            if (it.body.results.isEmpty()) {
                binding.textView3.visibility = GONE
            } else {
                binding.textView3.visibility = VISIBLE
            }

            val adapter: SuggestedMoviesListAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = it.body.results
            adapter = SuggestedMoviesListAdapter(data)
            binding.recyclerView.adapter = adapter
        }

        moviesViewModel.getMovieDetail(movieId!!)
        moviesViewModel.getMovieDetail.observe(this) {
            val movie = it!!.body
            if (movie.belongsToCollection != null) {
                Picasso.get().load(IMAGE_BASE_URL + movie.belongsToCollection.backdropPath)
                    .into(binding.collectionImage)
                binding.collectionName.text = movie.belongsToCollection.name
                binding.collectionImage.setOnClickListener {
                    PageNavUtils.toCollectionPage(this, movie.belongsToCollection.id)
                }

            } else {
                binding.collectionLayout.visibility = GONE
            }
            if (movie == null) {
                Toast.makeText(
                    this@MovieDetailActivity,
                    "Mmmm null", Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            Picasso.get().load(IMAGE_BASE_URL + movie.posterPath)
                .resize(500, 750)
                .placeholder(R.drawable.sample_cover_large)
                .into(binding.backgroundImm)
            if (movie.tagline.isEmpty()) {
                binding.movieSummaryTxt.text = ""
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
                append((movie.runtime) / 60)
                append("hr ")
                append((movie.runtime) % 60)
                append("min")
            }

            when {
                movie.releaseDate.isEmpty() -> {
                    binding.yearTxt.text = ""
                }

                else -> {
                    val localDate = LocalDate.parse(movie.releaseDate)
                    binding.yearTxt.text = localDate.year.toString()
                }
            }
            movie.genres.forEach { genre ->
                genres!!.add(genre.name)
                binding.genre.text = genres.toString()
                    .replace("\\[".toRegex(), "").replace("\\]".toRegex(), "")

            }
            if (movie.productionCompanies.isEmpty()) {
                binding.prodCompany.text = "n/a"
            } else {
                movie.productionCompanies.forEach { productionCompany ->
                    productionCompanies!!.add(productionCompany.name)
                    binding.prodCompany.text = productionCompanies.toString()
                        .replace("\\[".toRegex(), "").replace("\\]".toRegex(), "")
                }
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
            val data = response.body.cast
            adapter = CastAdapter(data)
            binding.castRecyclerView.adapter = adapter
            if (response.body.cast.isEmpty()) {
                binding.textView8.visibility = GONE
            }

        }

        moviesViewModel.getMovieReleaseDates(movieId!!)
        moviesViewModel.getMovieReleaseDates.observe(this) { it ->
            it.body.results.forEach { result ->
                //todo: get this from interceptor
                if (result.iso31661 == "US") {
                    result.releaseDates.forEach { movie ->
                        //  Toast.makeText(this, movie.type.toString(), Toast.LENGTH_SHORT).show()
                        if (movie.type == THEATRICAL ||
                            movie.type == THEATRICAL_LIMITED
                        ) {
                            if (movie.certification.isEmpty()) {
                                binding.certifications.text = "n/a"
                            } else {
                                binding.certifications.text = movie.certification
                            }

                        }
                    }
                }
            }
        }

    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun setUpView() {

        binding.reviewsBtn.setOnClickListener {
            PageNavUtils.toReviewsPage(this, "movie", movieId!!)
        }
        binding.trailerBtn.setOnClickListener {
            PageNavUtils.toTrailersPage(this@MovieDetailActivity, "movie", movieId!!)
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