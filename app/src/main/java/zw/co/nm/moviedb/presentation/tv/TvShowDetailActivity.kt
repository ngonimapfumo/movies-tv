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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.TVCastAdapter
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.presentation.auth.AuthViewModel
import zw.co.nm.moviedb.presentation.auth.LoginActivity
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.season.SeasonsAdapter
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var authViewModel: AuthViewModel
    private var showId: Int? = null
    private var productionCompanies: ArrayList<String>? = arrayListOf()
    private var tvNetworks: ArrayList<String>? = arrayListOf()
    private var iso6391: String? = null
    private var iso31661: String? = null
    private var logos: ArrayList<String>? = arrayListOf()
    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val id = showId ?: return@registerForActivityResult
            if (id != 0) {
                authViewModel.checkWatchlistState(id, Constants.MEDIA_TYPE_TV)
                authViewModel.setWatchlist(id, true, Constants.MEDIA_TYPE_TV)
            }
        }
    }

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
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) {

            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }

                else -> {
                    val tv = it.body
                    supportActionBar?.title = tv.name
                    Picasso.get().load(IMAGE_BASE_URL + tv.posterPath)
                        .resize(500, 750)
                        .placeholder(R.drawable.sample_cover_large_exp)
                        .into(binding.tvBackgroundImm)
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
                                logos!!.add(IMAGE_BASE_URL + it.filePath)
                            }
                        }
                    }

                    when (logos!!.size) {
                        0 -> {
                            binding.tvTitleTxt.visibility = View.VISIBLE
                        }

                        else -> Picasso.get().load(logos!![0]).into(binding.tvLogo)
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

        authViewModel.isOnWatchlist.observe(this) { onWatchlist ->
            updateBookmarkUi(onWatchlist)
        }

        if (ConfigStore.isLoggedIn(this) && showId != null && showId != 0) {
            authViewModel.checkWatchlistState(showId!!, Constants.MEDIA_TYPE_TV)
        }

        binding.bookmarkBtn.setOnClickListener {
            if (ConfigStore.isLoggedIn(this)) {
                toggleWatchlist()
            } else {
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_SHORT).show()
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

    private fun updateBookmarkUi(onWatchlist: Boolean) {
        binding.bookmarkBtn.alpha = if (onWatchlist) 1f else 0.55f
        binding.bookmarkBtn.contentDescription = getString(
            if (onWatchlist) R.string.remove_from_watchlist_title else R.string.bookmark
        )
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