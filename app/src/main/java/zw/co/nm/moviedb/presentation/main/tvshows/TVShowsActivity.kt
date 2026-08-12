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
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.databinding.ActivityTvshowsBinding
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.TvShowsViewModel
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.BACKDROP_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvshowsBinding
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    private var isLoadingMore = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvshowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.title = getString(R.string.tv_shows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        setUpScroll()
        observeShows()
        loadInitial()
    }

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

    private fun observeShows() {
        tvShowsViewModel.getPopularShows.observe(this) { response ->
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

        val imageUrl = when {
            !featured.backdropPath.isNullOrBlank() ->
                BACKDROP_IMAGE_BASE_URL + featured.backdropPath
            !featured.posterPath.isNullOrBlank() ->
                Constants.IMAGE_BASE_URL + featured.posterPath
            else -> null
        }
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.sample_cover_large_exp)
                .into(binding.heroImage)
        } else {
            binding.heroImage.setImageResource(R.drawable.sample_cover_large_exp)
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
        tvShowsViewModel.getPopularTvShows()
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage) return
        isLoadingMore = true
        setLoadMoreVisible(true)
        tvShowsViewModel.page++
        tvShowsViewModel.getPopularTvShows()
    }

    private fun setLoadMoreVisible(visible: Boolean) {
        binding.loadMoreCard.visibility = if (visible) VISIBLE else GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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
}
