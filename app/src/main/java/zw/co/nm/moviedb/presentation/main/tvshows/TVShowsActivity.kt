package zw.co.nm.moviedb.presentation.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.databinding.ActivityTvshowsBinding
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.TvShowsViewModel
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils
import zw.co.nm.moviedb.util.PillBottomNav
import java.time.LocalDate

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvshowsBinding
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    private var identifier: String = Constants.IDENTIFIER_POPULAR
    private var isLoadingMore = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvshowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        identifier = intent.getStringExtra(Constants.LIST_IDENTIFIER)
            ?: Constants.IDENTIFIER_POPULAR
        supportActionBar?.title = intent.getStringExtra(Constants.LIST_TITLE)
            ?: if (isHiddenGems()) getString(R.string.hidden_gems) else getString(R.string.tv_shows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.popularTitle.text = if (isHiddenGems()) {
            getString(R.string.hidden_gems)
        } else {
            getString(R.string.popular_tv_shows)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }

        tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        adapter = TvShowsAdapter()
        binding.recyclerView.adapter = adapter
        PillBottomNav.setup(this, binding.pillNav)
        setUpScroll()
        observeShows()
        loadInitial()
    }

    private fun isHiddenGems(): Boolean =
        identifier.equals(Constants.IDENTIFIER_HIDDEN_GEMS, true)

    private fun setUpScroll() {
        binding.scrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { scrollView, _, scrollY, _, oldScrollY ->
                binding.scrollTopFab.visibility = if (scrollY > 800) VISIBLE else GONE

                val scrollingDown = scrollY > oldScrollY
                if (!scrollingDown || isLoadingMore || isLastPage) return@OnScrollChangeListener

                val child = scrollView.getChildAt(0) ?: return@OnScrollChangeListener
                val distanceFromBottom = child.measuredHeight - (scrollView.height + scrollY)
                if (distanceFromBottom < 900) {
                    loadNextPage()
                }
            }
        )
        binding.scrollTopFab.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, 0)
        }
    }

    private fun showsLiveData(): LiveData<Response<GetPopularTVSeriesListResponse>> =
        if (isHiddenGems()) tvShowsViewModel.getHiddenGems else tvShowsViewModel.getPopularShows

    private fun observeShows() {
        showsLiveData().observe(this) { response ->
            binding.progressBar.visibility = GONE
            setLoadMoreVisible(false)
            val loadingMore = isLoadingMore
            isLoadingMore = false

            when (response.data) {
                null -> {
                    if (loadingMore && tvShowsViewModel.page > 1) {
                        tvShowsViewModel.page--
                    }
                    if (!loadingMore) {
                        binding.heroLayout.visibility = GONE
                    }
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        if (tvShowsViewModel.page == 1) {
                            loadInitial()
                        } else {
                            isLoadingMore = false
                            loadNextPage()
                        }
                    }
                }

                else -> {
                    isLastPage = response.body.page >= response.body.totalPages
                    if (response.body.page == 1) {
                        bindFeaturedHero(response.body.results)
                        adapter.submitList(response.body.results)
                    } else {
                        adapter.appendList(response.body.results)
                    }
                }
            }
        }
    }

    private fun bindFeaturedHero(shows: List<GetPopularTVSeriesListResponse.Result>) {
        val featured = shows
            .filter { !it.backdropPath.isNullOrBlank() }
            .maxByOrNull { it.voteAverage }
            ?: shows.firstOrNull()

        if (featured == null) {
            binding.heroLayout.visibility = GONE
            return
        }

        binding.heroLayout.visibility = VISIBLE
        binding.heroTitle.text = featured.name.orEmpty()
        binding.heroOverview.text = featured.overview.orEmpty()
        binding.heroOverview.visibility =
            if (featured.overview.isNullOrBlank()) GONE else VISIBLE

        val year = try {
            val airDate = featured.firstAirDate
            if (!airDate.isNullOrBlank()) {
                LocalDate.parse(airDate).year.toString()
            } else {
                ""
            }
        } catch (_: Exception) {
            ""
        }
        val rating = "${(featured.voteAverage * 10).toInt()}%"
        binding.heroMeta.text = listOf(year, rating)
            .filter { it.isNotBlank() }
            .joinToString(" · ")

        when {
            !featured.backdropPath.isNullOrBlank() -> {
                val density = resources.displayMetrics.density
                ImageLoader.loadBackdrop(
                    binding.heroImage,
                    featured.backdropPath,
                    resources.displayMetrics.widthPixels,
                    (480 * density).toInt()
                )
            }
            !featured.posterPath.isNullOrBlank() -> {
                val density = resources.displayMetrics.density
                ImageLoader.loadPoster(
                    binding.heroImage,
                    featured.posterPath,
                    resources.displayMetrics.widthPixels,
                    (480 * density).toInt(),
                    R.drawable.sample_cover_large_exp
                )
            }
            else -> binding.heroImage.setImageResource(R.drawable.sample_cover_large_exp)
        }

        val openFeatured = {
            PageNavUtils.navTvDetailsPage(this@TVShowsActivity, featured.id)
        }
        binding.heroCta.setOnClickListener { openFeatured() }
        binding.heroLayout.setOnClickListener { openFeatured() }
    }

    private fun loadInitial() {
        tvShowsViewModel.page = 1
        isLastPage = false
        isLoadingMore = false
        binding.progressBar.visibility = VISIBLE
        setLoadMoreVisible(false)
        fetchPage()
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage) return
        isLoadingMore = true
        setLoadMoreVisible(true)
        tvShowsViewModel.page++
        fetchPage()
    }

    private fun fetchPage() {
        if (isHiddenGems()) {
            tvShowsViewModel.getHiddenGems()
        } else {
            tvShowsViewModel.getPopularTvShows()
        }
    }

    private fun setLoadMoreVisible(visible: Boolean) {
        binding.loadMoreCard.visibility = if (visible) VISIBLE else GONE
    }

    private fun openListMode(newIdentifier: String, titleRes: Int) {
        if (identifier.equals(newIdentifier, true)) return
        startActivity(
            Intent(this, TVShowsActivity::class.java).apply {
                putExtra(Constants.LIST_IDENTIFIER, newIdentifier)
                putExtra(Constants.LIST_TITLE, getString(titleRes))
            }
        )
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_list_browse, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_hidden_gems)?.isVisible = !isHiddenGems()
        menu?.findItem(R.id.action_popular)?.isVisible = isHiddenGems()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }

            R.id.action_hidden_gems -> {
                openListMode(Constants.IDENTIFIER_HIDDEN_GEMS, R.string.hidden_gems)
                true
            }

            R.id.action_popular -> {
                openListMode(Constants.IDENTIFIER_POPULAR, R.string.tv_shows)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
