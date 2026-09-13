package zw.co.nm.moviedb.presentation.tv

import android.app.Activity
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
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
import zw.co.nm.moviedb.adapters.TVCastAdapter
import zw.co.nm.moviedb.adapters.WatchProvidersAdapter
import zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.presentation.auth.AuthViewModel
import zw.co.nm.moviedb.presentation.auth.LoginActivity
import zw.co.nm.moviedb.presentation.config.ConfigViewModel
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.season.SeasonsAdapter
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.MED_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils
import zw.co.nm.moviedb.util.RateMediaDialog
import zw.co.nm.moviedb.util.WatchProvidersUtils
import zw.co.nm.moviedb.util.WatchRegionPicker
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import android.view.View.VISIBLE

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private var showId: Int? = null
    private var productionCompanies: ArrayList<String>? = arrayListOf()
    private var tvNetworks: ArrayList<String>? = arrayListOf()
    private var iso6391: String? = null
    private var iso31661: String? = null
    private var logos: ArrayList<String>? = arrayListOf()
    private var pendingAuthAction: PendingAuthAction = PendingAuthAction.WATCHLIST
    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val id = showId ?: return@registerForActivityResult
            if (id == 0) return@registerForActivityResult
            authViewModel.checkAccountStates(id, Constants.MEDIA_TYPE_TV)
            when (pendingAuthAction) {
                PendingAuthAction.WATCHLIST ->
                    authViewModel.setWatchlist(id, true, Constants.MEDIA_TYPE_TV)

                PendingAuthAction.FAVORITE ->
                    authViewModel.setFavorite(id, true, Constants.MEDIA_TYPE_TV)

                PendingAuthAction.RATE -> showRateDialog()
            }
        }
    }

    private enum class PendingAuthAction { WATCHLIST, FAVORITE, RATE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { view, insets ->
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

        iso6391 = ConfigStore.getStringLang(this, Constants.LANGUAGE_KEY)!!.substring(0, 2)
        iso31661 = ConfigStore.getStringLang(this, Constants.LANGUAGE_KEY)!!.substring(3)
        showId = intent.getIntExtra(TV_SHOW_ID_EXTRA, 0)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setupWatchlist()
        binding.reviewsBtn.setOnClickListener {
            PageNavUtils.navReviewsPage(this, "tv_show", showId!!)
        }
        ConfigStore.saveIntConfig(
            this, Constants.SAVED_SHOW_ID,
            showId!!
        )
        binding.trailerBtn.setOnClickListener {
            PageNavUtils.navTrailersPage(this, "tv", showId!!)
        }
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        this.tvShowsViewModel = tvShowsViewModel
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getWatchProviders(showId!!)
        observeWatchProviders()
        tvShowsViewModel.getShowDetails.observe(this) {

            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getWatchProviders(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }

                else -> {
                    val tv = it.body
                    supportActionBar?.title = tv.name
                    ImageLoader.loadDetailPoster(
                        binding.tvBackgroundImm,
                        tv.posterPath,
                        placeholder = R.drawable.sample_cover_large_exp
                    )
                    binding.tvSummaryTxt.text = tv.tagline

                    tv.networks.forEach { network ->
                        tvNetworks!!.add(network.name)
                        binding.networksText.text = tvNetworks.toString().replace("[", "")
                            .replace("]", "")
                    }

                    when {
                        tv.overview.isEmpty() -> {
                            binding.detailedSummaryTxt.text = getString(R.string.no_info)
                        }

                        else -> {
                            binding.detailedSummaryTxt.text = tv.overview
                        }
                    }

                    binding.tvTitleTxt.text = tv.name

                    if (tv.firstAirDate.isEmpty()) {
                        binding.yearTxt.text = ""
                    } else {
                        val simpleDate = LocalDate.parse(tv.firstAirDate)
                        binding.yearTxt.text = buildString {
                            append("First air ")
                            append(simpleDate.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                            append(" ")
                            append(simpleDate.year)

                        }
                    }
                    binding.statusTxt.text = tv.status
                    tv.productionCompanies.forEach { productionCompany ->
                        productionCompanies!!.add(productionCompany.name)
                        binding.prodCompany.text = productionCompanies.toString().replace("[", "")
                            .replace("]", "")
                    }
                    binding.tvRatingTxt.text = buildString {
                        append((tv.voteAverage * 10).toInt().toString())
                        append("%")
                        append(" (${tv.voteCount} votes)")
                    }

                    val adapter: SeasonsAdapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    val data = tv.seasons
                    adapter = SeasonsAdapter(data)
                    binding.recyclerView.adapter = adapter
                }
            }


        }

        tvShowsViewModel.getTvImages(showId!!)
        tvShowsViewModel.getTVImages.observe(this) { images ->

            when (images.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }

                else -> {

                    images.body.logos.forEach {
                        when (it.iso6391) {
                            iso6391 -> {
                                binding.tvLogo.visibility = View.VISIBLE
                                binding.tvTitleTxt.visibility = GONE
                                logos!!.add(MED_RES_IMAGE_BASE_URL + it.filePath)
                            }
                        }
                    }

                    when (logos!!.size) {
                        0 -> {
                            binding.tvTitleTxt.visibility = View.VISIBLE
                        }

                        else -> ImageLoader.loadLogo(binding.tvLogo, logos!![0])
                    }


                }
            }
        }

        tvShowsViewModel.getTvCredits(showId!!)
        tvShowsViewModel.getTvCredits.observe(this) { response ->

            when (response!!.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }
                else -> {

                    val adapter: TVCastAdapter
                    binding.castRecyclerView.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    val data = response.body.cast
                    adapter = TVCastAdapter(data)
                    binding.castRecyclerView.adapter = adapter
                    if (response.body.cast.isEmpty()) {
                        binding.textView8.visibility = GONE
                    }

                }
            }


        }

    }

    private fun observeWatchProviders() {
        tvShowsViewModel.getWatchProviders.observe(this) { response ->
            bindWatchProviders(response)
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
            language = ConfigStore.getStringLang(this, Constants.LANGUAGE_KEY)
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
                tvShowsViewModel.getWatchProviders.value?.let { bindWatchProviders(it) }
            }
            return
        }
        val observer = object : Observer<Response<GetCountriesResponse>> {
            override fun onChanged(response: Response<GetCountriesResponse>) {
                if (response.data == null && !response.isSuccessful) return
                configViewModel.getCountries.removeObserver(this)
                if (!response.isSuccessful || response.data == null) {
                    Toast.makeText(
                        this@TvShowDetailActivity,
                        Constants.NETWORK_ERROR_MSG,
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                WatchRegionPicker.show(this@TvShowDetailActivity, response.body) {
                    tvShowsViewModel.getWatchProviders.value?.let { bindWatchProviders(it) }
                }
            }
        }
        configViewModel.getCountries.observe(this, observer)
        configViewModel.getCountries()
    }

    private fun setupWatchlist() {
        authViewModel.watchlistMutation.observe(this) { mutation ->
            if (mutation.mediaType != Constants.MEDIA_TYPE_TV) return@observe
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
            if (mutation.mediaType != Constants.MEDIA_TYPE_TV) return@observe
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
            if (mutation.mediaType != Constants.MEDIA_TYPE_TV) return@observe
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

        if (ConfigStore.isLoggedIn(this) && showId != null && showId != 0) {
            authViewModel.checkAccountStates(showId!!, Constants.MEDIA_TYPE_TV)
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
        val id = showId ?: return
        if (id == 0) return
        val currentlyOnWatchlist = authViewModel.isOnWatchlist.value == true
        authViewModel.setWatchlist(id, !currentlyOnWatchlist, Constants.MEDIA_TYPE_TV)
    }

    private fun toggleFavorite() {
        val id = showId ?: return
        if (id == 0) return
        val currentlyFavorite = authViewModel.isFavorite.value == true
        authViewModel.setFavorite(id, !currentlyFavorite, Constants.MEDIA_TYPE_TV)
    }

    private fun showRateDialog() {
        val id = showId ?: return
        if (id == 0) return
        RateMediaDialog.show(
            context = this,
            currentRating = authViewModel.userRating.value,
            onRate = { value ->
                authViewModel.rateMedia(id, value, Constants.MEDIA_TYPE_TV)
            },
            onClear = {
                authViewModel.deleteRating(id, Constants.MEDIA_TYPE_TV)
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

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
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