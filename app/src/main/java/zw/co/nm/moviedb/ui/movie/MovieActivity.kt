package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.models.network.Cast
import zw.co.nm.moviedb.ui.adapters.CastAdapter
import zw.co.nm.moviedb.ui.adapters.SimilarMoviesListAdapter
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import kotlin.math.roundToInt


class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieViewModel: MovieViewModel
    private var movieId: Int? = null
    private lateinit var castList: ArrayList<Cast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        lifecycleScope.launch {
            movieViewModel.getSimilarMoviesList(movieId!!).collect { response ->
                val adapter: SimilarMoviesListAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(
                    this@MovieActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                val data = response.data!!.body()!!.results
                adapter = SimilarMoviesListAdapter(data)
                binding.recyclerView.adapter = adapter
            }
        }
        lifecycleScope.launch {
            movieViewModel.getMovieDetail(movieId!!).collect {
                val response = it.body
                Picasso.get().load(IMAGE_BASE_URL + response.posterPath)
                    .placeholder(R.drawable.sample_cover_large).into(binding.backgroundImm)
                binding.movieSummaryTxt.text = response.overview
                binding.movieTitleTxt.text = response.title
                binding.runtimeTxt.text = buildString {
                    append(response.runtime)
                    append(" minutes")
                }
                binding.yearTxt.text = response.releaseDate.substring(0, 4)
                for (i in response.genres.indices) {
                    binding.genre.text = response.genres[i].name
                }
                for (i in response.productionCompanies.indices) {
                    binding.prodCompany.text = response.productionCompanies[i].name
                }
                binding.movieRatingTxt.text = response.voteAverage.roundToInt().toString()
            }
        }
        lifecycleScope.launch {
            movieViewModel.getCredits(movieId!!).collect {
                response ->
                val adapter: CastAdapter
                binding.castRecyclerView.layoutManager = LinearLayoutManager(
                    this@MovieActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                val data = response.data!!.body()!!.cast
                adapter = CastAdapter(data)
                binding.castRecyclerView.adapter = adapter

            }
        }
    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun setUpView() {
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)

    }
}