package zw.co.nm.moviedb.presentation.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.CastAdapter
import zw.co.nm.moviedb.adapters.SuggestedMoviesListAdapter
import zw.co.nm.moviedb.adapters.WatchProvidersAdapter
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding
import zw.co.nm.moviedb.presentation.auth.AuthViewModel
import zw.co.nm.moviedb.presentation.auth.LoginActivity
import zw.co.nm.moviedb.presentation.config.ConfigViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.util.AddToListPicker
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY
import zw.co.nm.moviedb.util.Constants.MED_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.Constants.NETWORK_ERROR_MSG
import zw.co.nm.moviedb.util.Constants.THEATRICAL
import zw.co.nm.moviedb.util.Constants.THEATRICAL_LIMITED
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils
import zw.co.nm.moviedb.util.RateMediaDialog
import zw.co.nm.moviedb.util.WatchProvidersUtils
import zw.co.nm.moviedb.util.WatchRegionPicker
import java.time.LocalDate


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var authViewModel: AuthViewModel
    private var movieId: Int? = null
    private var genres: ArrayList<String>? = arrayListOf()
    private var logos: ArrayList<String>? = arrayListOf()
    private var productionCompanies: ArrayList<String>? = arrayListOf()
    private var iso6391: String? = null
    private var iso31661: String? = null
    private var displayMetricsWidth: Int? = null
    private var pendingAuthAction: PendingAuthAction = PendingAuthAction.WATCHLIST
    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val id = movieId ?: return@registerForActivityResult
            if (id == 0) return@registerForActivityResult
            authViewModel.checkAccountStates(id, Constants.MEDIA_TYPE_MOVIE)
            when (pendingAuthAction) {
                PendingAuthAction.WATCHLIST ->
                    authViewModel.setWatchlist(id, true, Constants.MEDIA_TYPE_MOVIE)

                PendingAuthAction.FAVORITE ->
                    authViewModel.setFavorite(id, true, Constants.MEDIA_TYPE_MOVIE)

                PendingAuthAction.RATE -> showRateDialog()
            }
        }
    }

    private enum class PendingAuthAction { WATCHLIST, FAVORITE, RATE }


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

        moviesViewModel.getMovieDetail(movieId!!)
        moviesViewModel.getWatchProviders(movieId!!)
        moviesViewModel.getMovieKeywords(movieId!!)
        observeWatchProviders()
        observeKeywords()
        moviesViewModel.getMovieDetail.observe(this) {
            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, NETWORK_ERROR_MSG, "Retry") {
                        moviesViewModel.getMovieDetail(movieId!!)
                        moviesViewModel.getWatchProviders(movieId!!)
                        moviesViewModel.getMovieKeywords(movieId!!)
                        moviesViewModel.getSimilarMoviesList(movieId!!)
                        moviesViewModel.getCredits(movieId!!)
                        moviesViewModel.getMovieReleaseDates(movieId!!)
                        moviesViewModel.getMovieImages(movieId!!)
                    }
                }

                else -> {
                    val movie = it.body
                    supportActionBar?.title = movie.title
                    ImageLoader.loadDetailPoster(
                        binding.backgroundImm,
                        movie.posterPath,
                        placeholder = R.drawable.sample_cover_large_exp
                    )

                    if (movie.belongsToCollection != null) {
                        if (movie.belongsToCollection.backdropPath == null) {
                            binding.collectionImage.setImageResource(R.drawable.sample_episode_exp)
                        } else {
                            ImageLoader.loadStill(
                                binding.collectionImage,
                                movie.belongsToCollection.backdropPath,
                                widthPx = 500,
                                heightPx = 280,
                                placeholder = R.drawable.sample_episode_exp
                            )
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
                        binding.postersImage.setImageResource(R.drawable.sample_episode_exp)
                    } else {
                        ImageLoader.loadStill(
                            binding.postersImage,
                            movie.backdropPath,
                            widthPx = 500,
                            heightPx = 280,
                            placeholder = R.drawable.sample_episode_exp
                        )
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
                                logos!!.add(MED_RES_IMAGE_BASE_URL + it.filePath)
                            }
                        }
                    }

                    when (logos!!.size) {
                        0 -> {
                            binding.movieTitleTxt.visibility = VISIBLE
                        }

                        else -> ImageLoader.loadLogo(binding.movieLogo, logos!![0])
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

    private fun observeWatchProviders() {
        moviesViewModel.getWatchProviders.observe(this) { response ->
            bindWatchProviders(response)
        }
    }

    private fun observeKeywords() {
        moviesViewModel.getMovieKeywords.observe(this) { response ->
            if (!response.isSuccessful || response.data == null) {
                binding.keywordsSection.visibility = GONE
                return@observe
            }
            val keywords = response.body.keywords
                .filter { it.name.isNotBlank() }
                .take(12)
            if (keywords.isEmpty()) {
                binding.keywordsSection.visibility = GONE
                return@observe
            }
            binding.keywordsSection.visibility = VISIBLE
            binding.recyclerKeywords.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.recyclerKeywords.adapter = KeywordChipsAdapter(keywords)
        }
    }

    private fun bindWatchProviders(response: Response<GetWatchProvidersResponse>) {
        val section = binding.watchProvidersSection
        if (!response.isSuccessful || response.data == null) {
            section.root.visibility = GONE
            return
        }

        val preferredCountry = ConfigStore.getPreferredWatchRegion(this)
        val resolved = WatchProvidersUtils.resolveForCountry(
            response = response.body,
            preferredCountry = preferredCountry,
            language = ConfigStore.getStringLang(this, LANGUAGE_KEY)
        )

        if (resolved == null) {
            section.root.visibility = GONE
            return
        }

        val (region, countryProviders) = resolved
        val items = WatchProvidersUtils.toItems(countryProviders)

        section.root.visibility = VISIBLE
        section.watchProvidersRegion.text = getString(R.string.watch_region_chip, region)
        section.watchProvidersRegion.setOnClickListener { openWatchRegionPicker() }

        if (items.isEmpty()) {
            section.watchProvidersRecycler.visibility = GONE
            section.watchProvidersEmpty.visibility = VISIBLE
            section.justWatchAttribution.visibility = GONE
        } else {
            section.watchProvidersEmpty.visibility = GONE
            section.watchProvidersRecycler.visibility = VISIBLE
            section.justWatchAttribution.visibility = VISIBLE
            section.watchProvidersRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            section.watchProvidersRecycler.adapter = WatchProvidersAdapter(items)
        }
    }

    private fun openWatchRegionPicker() {
        val configViewModel = ViewModelProvider(this)[ConfigViewModel::class.java]
        val cached = configViewModel.getCountries.value
        if (cached != null && cached.isSuccessful) {
            WatchRegionPicker.show(this, cached.body) {
                moviesViewModel.getWatchProviders.value?.let { bindWatchProviders(it) }
            }
            return
        }
        val observer = object : Observer<Response<zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse>> {
            override fun onChanged(
                response: Response<zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse>
            ) {
                if (response.data == null && !response.isSuccessful) return
                configViewModel.getCountries.removeObserver(this)
                if (!response.isSuccessful || response.data == null) {
                    Toast.makeText(
                        this@MovieDetailActivity,
                        NETWORK_ERROR_MSG,
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                WatchRegionPicker.show(this@MovieDetailActivity, response.body) {
                    moviesViewModel.getWatchProviders.value?.let { bindWatchProviders(it) }
                }
            }
        }
        configViewModel.getCountries.observe(this, observer)
        configViewModel.getCountries()
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
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)

        authViewModel.watchlistMutation.observe(this) { mutation ->
            if (mutation.mediaType != Constants.MEDIA_TYPE_MOVIE) return@observe
            if (mutation.success) {
                val message = if (mutation.added) {
                    R.string.added_to_watchlist
                } else {
                    R.string.removed_from_watchlist
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                updateBookmarkUi(mutation.added)
            } else {
                Toast.makeText(this, R.string.watchlist_error, Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.favoriteMutation.observe(this) { mutation ->
            if (mutation.mediaType != Constants.MEDIA_TYPE_MOVIE) return@observe
            if (mutation.success) {
                val message = if (mutation.added) {
                    R.string.added_to_favorites
                } else {
                    R.string.removed_from_favorites
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                updateFavoriteUi(mutation.added)
            } else {
                Toast.makeText(this, R.string.favorites_error, Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.ratingMutation.observe(this) { mutation ->
            if (mutation.mediaType != Constants.MEDIA_TYPE_MOVIE) return@observe
            if (mutation.success) {
                val message = if (mutation.deleted) {
                    R.string.rating_removed
                } else {
                    R.string.rating_saved
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                updateRateUi(mutation.rating)
            } else {
                Toast.makeText(this, R.string.rating_error, Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.isOnWatchlist.observe(this) { onWatchlist ->
            updateBookmarkUi(onWatchlist)
        }
        authViewModel.isFavorite.observe(this) { favorite ->
            updateFavoriteUi(favorite)
        }
        authViewModel.userRating.observe(this) { rating ->
            updateRateUi(rating)
        }

        if (ConfigStore.isLoggedIn(this) && movieId != null && movieId != 0) {
            authViewModel.checkAccountStates(movieId!!, Constants.MEDIA_TYPE_MOVIE)
        }

        binding.bookmarkBtn.setOnClickListener {
            if (ConfigStore.isLoggedIn(this)) {
                toggleWatchlist()
            } else {
                pendingAuthAction = PendingAuthAction.WATCHLIST
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_SHORT).show()
                loginLauncher.launch(Intent(this, LoginActivity::class.java))
            }
        }
        binding.favoriteBtn.setOnClickListener {
            if (ConfigStore.isLoggedIn(this)) {
                toggleFavorite()
            } else {
                pendingAuthAction = PendingAuthAction.FAVORITE
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_SHORT).show()
                loginLauncher.launch(Intent(this, LoginActivity::class.java))
            }
        }
        binding.rateBtn.setOnClickListener {
            if (ConfigStore.isLoggedIn(this)) {
                showRateDialog()
            } else {
                pendingAuthAction = PendingAuthAction.RATE
                Toast.makeText(this, R.string.login_required_rate, Toast.LENGTH_SHORT).show()
                loginLauncher.launch(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun toggleWatchlist() {
        val id = movieId ?: return
        if (id == 0) return
        val currentlyOnWatchlist = authViewModel.isOnWatchlist.value == true
        authViewModel.setWatchlist(id, !currentlyOnWatchlist, Constants.MEDIA_TYPE_MOVIE)
    }

    private fun toggleFavorite() {
        val id = movieId ?: return
        if (id == 0) return
        val currentlyFavorite = authViewModel.isFavorite.value == true
        authViewModel.setFavorite(id, !currentlyFavorite, Constants.MEDIA_TYPE_MOVIE)
    }

    private fun showRateDialog() {
        val id = movieId ?: return
        if (id == 0) return
        RateMediaDialog.show(
            context = this,
            currentRating = authViewModel.userRating.value,
            onRate = { value ->
                authViewModel.rateMedia(id, value, Constants.MEDIA_TYPE_MOVIE)
            },
            onClear = {
                authViewModel.deleteRating(id, Constants.MEDIA_TYPE_MOVIE)
            }
        )
    }

    private fun updateBookmarkUi(onWatchlist: Boolean) {
        binding.bookmarkBtn.alpha = if (onWatchlist) 1f else 0.55f
        binding.bookmarkBtn.contentDescription = getString(
            if (onWatchlist) R.string.remove_from_watchlist_title else R.string.bookmark
        )
    }

    private fun updateFavoriteUi(isFavorite: Boolean) {
        binding.favoriteBtn.alpha = if (isFavorite) 1f else 0.55f
        binding.favoriteBtn.contentDescription = getString(
            if (isFavorite) R.string.remove_from_favorites_title else R.string.favorite
        )
    }

    private fun updateRateUi(rating: Double?) {
        binding.rateBtn.alpha = if (rating != null) 1f else 0.55f
        binding.rateBtn.contentDescription = if (rating != null) {
            getString(R.string.your_rating_value, rating)
        } else {
            getString(R.string.rate)
        }
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

            R.id.action_add_to_list -> {
                val id = movieId
                if (id == null || id == 0) return true
                if (!ConfigStore.isLoggedIn(this)) {
                    Toast.makeText(this, R.string.login_required_lists, Toast.LENGTH_SHORT).show()
                    loginLauncher.launch(Intent(this, LoginActivity::class.java))
                } else {
                    AddToListPicker.show(this, id)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_movie_detail, menu)
        return true
    }
}