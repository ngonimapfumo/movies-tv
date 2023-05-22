package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.models.Movie
import zw.co.nm.moviedb.models.network.Cast
import zw.co.nm.moviedb.ui.adapters.MovieListAdapter
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.Constants.LOW_RES_IMAGE_BASE_URL
import kotlin.math.roundToInt


class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieViewModel: MovieViewModel
    private var movieId: Int? = null
    private lateinit var movieList: ArrayList<Movie>
    private lateinit var adapter: MovieListAdapter
    private lateinit var castList: ArrayList<Cast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        lifecycleScope.launch {
            movieViewModel.getSimilarMoviesList(movieId!!).collect { response ->

                binding.recyclerView.layoutManager = LinearLayoutManager(
                    this@MovieActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                binding.recyclerView.hasFixedSize()
                movieList = arrayListOf()
                for (i in response.body.results.indices) {
                    val fullPosterPath =
                        LOW_RES_IMAGE_BASE_URL + response.body.results[i].posterPath
                    val movies = Movie(
                        fullPosterPath, response.body.results[i].title,
                        response.body.results[i].id
                    )
                    movieList.add(movies)
                }
                adapter = MovieListAdapter(movieList)
                binding.recyclerView.adapter = adapter


            }
        }
        lifecycleScope.launch {
            movieViewModel.getMovieDetail(movieId!!).collect {
                var response = it.body
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
            castList = arrayListOf()

            movieViewModel.getCredits(movieId!!).collect {
                /*for (i in it.body.cast.indices) {
                    val cast = Cast(it.body.cast[i].name,
                        it.body.cast[i].character)
                    castList.add(cast)

                }*/

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