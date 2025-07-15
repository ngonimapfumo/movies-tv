package zw.co.nm.moviedb.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.CastAdapter
import zw.co.nm.moviedb.adapters.SuggestedMoviesListAdapter
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY
import zw.co.nm.moviedb.util.Constants.NETWORK_ERROR_MSG
import zw.co.nm.moviedb.util.Constants.THEATRICAL
import zw.co.nm.moviedb.util.Constants.THEATRICAL_LIMITED
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null
    private var genres: ArrayList<String>? = arrayListOf()
    private var logos: ArrayList<String>? = arrayListOf()
    private var posterList: ArrayList<String>? = arrayListOf()
    private var productionCompanies: ArrayList<String>? = arrayListOf()
    private var iso6391: String? = null
    private var iso31661: String? = null
    private var displayMetricsWidth: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setUpView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) {
                view, insets, ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            binding.mainLayout.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }

        moviesViewModel.getMovieImages(movieId!!)
        moviesViewModel.getMovieImages.observe(this) { posters ->

            posters.body.posters.forEach { imagesResponse ->
                when (imagesResponse.iso6391) {
                    iso6391 -> {
                        posterList!!.add(IMAGE_BASE_URL+imagesResponse.filePath)

                        //   println(posterList!!.random())
                        Picasso.get().load(posterList!!.random())
                            .resize(500, 0)
                            .placeholder(R.drawable.sample_cover_large_exp)
                            .into(binding.backgroundImm)


                    }

                }
            }
        }


        moviesViewModel.getMovieDetail(movieId!!)
        moviesViewModel.getMovieDetail.observe(this) {
            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieImages(movieId!!)
                        moviesViewModel.getMovieDetail(movieId!!)
                        moviesViewModel.getSimilarMoviesList(movieId!!)
                        moviesViewModel.getCredits(movieId!!)
                        moviesViewModel.getMovieReleaseDates(movieId!!)
                    }
                }

                else -> {
                    val movie = it.body
                    supportActionBar?.title = movie.title
                    Picasso.get().load(IMAGE_BASE_URL+movie.posterPath)
                        .resize(500, 0)
                        .placeholder(R.drawable.sample_cover_large_exp)
                        .into(binding.backgroundImm)

                    if (movie.belongsToCollection != null) {
                        if (movie.belongsToCollection.backdropPath == null) {
                            when {
                                displayMetricsWidth!! >= 1080 -> {
                                    Picasso.get().load(R.drawable.sample_episode_exp)
                                        .resize(500, 0)
                                        .into(binding.collectionImage)
                                }

                                else -> {
                                    Picasso.get().load(R.drawable.sample_episode_exp)
                                        .resize(350, 0)
                                        .into(binding.collectionImage)
                                }
                            }
                        } else {
                            val picasso = Picasso.get()
                                .load(IMAGE_BASE_URL + movie.belongsToCollection.backdropPath)
                            when {
                                displayMetricsWidth!! >= 1080 -> {
                                    picasso.resize(500, 0)
                                }

                                else -> {
                                    picasso.resize(350, 0)
                                }
                            }

                            picasso.into(binding.collectionImage)
                        }
                        binding.collectionName.text = movie.belongsToCollection.name
                        binding.collectionImage.setOnClickListener {
                            PageNavUtils.navCollectionPage(this, movie.belongsToCollection.id)
                        }

                    } else {
                        binding.collectionLayout.visibility = GONE
                    }

                    if (movie.tagline.isEmpty()) {
                        binding.movieSummaryTxt.text = ""
                    } else {
                        binding.movieSummaryTxt.text = movie.tagline
                    }
                    if (movie.overview.isEmpty()) {
                        binding.aboutCard.visibility = GONE
                    }
                    binding.detailedSummaryTxt.text = movie.overview
                    binding.detailedSummaryTxt.setOnClickListener {
                        showGenericDialog(
                            this@MovieDetailActivity,
                            movie.overview, "OKAY"
                        )

                    }
                    binding.movieTitleTxt.text = movie.title
                    binding.runtimeTxt.text = buildString {
                        append((movie.runtime) / 60)
                        append("hr ")
                        append((movie.runtime) % 60)
                        append("min")
                    }
                    binding.postersName.text = getString(R.string.poster_collection)

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
                                .replace("\\[".toRegex(), "").replace("]".toRegex(), "")
                        }
                    }

                    binding.movieRatingTxt.text = buildString {
                        append((movie.voteAverage * 10).toInt().toString())
                        append("%")
                        append(" (${movie.voteCount} votes)")
                    }
                    binding.statusTxt.text = movie.status

                    if (movie.backdropPath == null) {
                        when {
                            displayMetricsWidth!! >= 1080 -> {
                                Picasso.get().load(R.drawable.sample_episode_exp)
                                    .resize(500, 0)
                                    .into(binding.postersImage)
                            }

                            else -> {
                                Picasso.get().load(R.drawable.sample_episode_exp)
                                    .resize(350, 0)
                                    .into(binding.postersImage)
                            }
                        }

                    } else {
                        val picasso = Picasso.get().load(IMAGE_BASE_URL + movie.backdropPath)
                        if (displayMetricsWidth!! >= 1080) {
                            picasso.resize(500, 0)
                        } else {
                            picasso.resize(350, 0)
                        }
                        picasso.into(binding.postersImage)

                    }
                    binding.postersCard.setOnClickListener {
                        PageNavUtils.navMoviePostersPage(this, movieId!!)
                    }

                }
            }

        }

        moviesViewModel.getMovieImages(movieId!!)
        moviesViewModel.getMovieImages.observe(this) { movie ->

            when (movie.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieImages(movieId!!)
                    }
                }

                else -> {

                    movie.body.logos.forEach {
                        when (it.iso6391) {
                            iso6391 -> {
                                binding.movieLogo.visibility = VISIBLE
                                binding.movieTitleTxt.visibility = GONE
                                logos!!.add(IMAGE_BASE_URL + it.filePath)
                            }
                        }
                    }

                    when (logos!!.size) {
                        0 -> {
                            binding.movieTitleTxt.visibility = VISIBLE
                        }

                        else -> Picasso.get().load(logos!![0]).into(binding.movieLogo)
                    }
                }
            }


        }

        moviesViewModel.getSimilarMoviesList(movieId!!)
        moviesViewModel.getSimilarMovies.observe(this) {

            when (it.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieDetail(movieId!!)
                        moviesViewModel.getSimilarMoviesList(movieId!!)
                        moviesViewModel.getCredits(movieId!!)
                        moviesViewModel.getMovieReleaseDates(movieId!!)
                    }
                }

                else -> {
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
            }

        }

        moviesViewModel.getCredits(movieId!!)
        moviesViewModel.getMovieCredits.observe(this) { response ->

            when (response.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieDetail(movieId!!)
                        moviesViewModel.getSimilarMoviesList(movieId!!)
                        moviesViewModel.getCredits(movieId!!)
                        moviesViewModel.getMovieReleaseDates(movieId!!)
                    }

                }

                else -> {

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
                    response.body.crew.forEach { crew ->
                        when (crew.job) {
                            "Director" -> {

                                binding.director.text = crew.name
                            }
                        }
                    }

                }
            }


        }

        moviesViewModel.getMovieReleaseDates(movieId!!)
        moviesViewModel.getMovieReleaseDates.observe(this) {
            when (it.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieDetail(movieId!!)
                        moviesViewModel.getSimilarMoviesList(movieId!!)
                        moviesViewModel.getCredits(movieId!!)
                        moviesViewModel.getMovieReleaseDates(movieId!!)
                    }
                }

                else -> {
                    it.body.results.forEach { result ->
                        if (result.iso31661 == iso31661!!.uppercase()) {
                            result.releaseDates.forEach { movie ->
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

        }


    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun setUpView() {
        displayMetricsWidth = ConfigStore.getInt(this, Constants.DISPLAY_METRICS_WIDTH)
        iso6391 = ConfigStore.getStringLang(this, LANGUAGE_KEY)!!.substring(0, 2)
        iso31661 = ConfigStore.getStringLang(this, LANGUAGE_KEY)!!.substring(3)
        binding.reviewsBtn.setOnClickListener {
            PageNavUtils.navReviewsPage(this, "movie", movieId!!)
        }
        binding.trailerBtn.setOnClickListener {
            PageNavUtils.navTrailersPage(this@MovieDetailActivity, "movie", movieId!!)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}