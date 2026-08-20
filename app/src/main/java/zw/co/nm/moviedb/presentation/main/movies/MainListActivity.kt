package zw.co.nm.moviedb.presentation.main.movies

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTrendingResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.databinding.ActivityMainListBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.util.EndlessScrollListener
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var genreId: Int = 0
    private var keywordId: Int = 0
    private var identifier: String = ""
    private var isLoadingMore = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
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

        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        genreId = intent.getIntExtra("genre_id", 0)
        keywordId = intent.getIntExtra("keyword_id", 0)
        identifier = intent.getStringExtra("identifier").orEmpty()
        supportActionBar?.title = intent.getStringExtra("title")
            ?: defaultTitle()

        moviesAdapter = MoviesAdapter()
        trendingAdapter = TrendingAdapter()
        binding.recyclerView.adapter =
            if (isTrending()) trendingAdapter else moviesAdapter

        setUpScroll()
        observeList()
        loadInitial()
    }

    private fun defaultTitle(): String = when {
        identifier.equals("trending", true) -> getString(R.string.trending_today)
        identifier.equals("now_playing", true) -> getString(R.string.now_playing)
        identifier.equals("upcoming", true) -> getString(R.string.upcoming)
        identifier.equals("from_keyword", true) -> getString(R.string.keywords)
        else -> getString(R.string.movies)
    }

    private fun isTrending(): Boolean = identifier.equals("trending", true)

    private fun setUpScroll() {
        val layoutManager = binding.recyclerView.layoutManager as GridLayoutManager
        binding.recyclerView.addOnScrollListener(
            EndlessScrollListener(layoutManager) { loadNextPage() }
        )
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val shouldShow = recyclerView.computeVerticalScrollOffset() > 800
                binding.scrollTopFab.visibility = if (shouldShow) VISIBLE else GONE
            }
        })
        binding.scrollTopFab.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun observeList() {
        if (isTrending()) {
            observeTrending(moviesViewModel.getTrending)
        } else {
            observeMovies(moviesLiveData())
        }
    }

    private fun moviesLiveData(): LiveData<Response<GetPopularMoviesListResponse>> = when {
        identifier.equals("from_genre", true) -> moviesViewModel.getMovieByGenreId
        identifier.equals("from_keyword", true) -> moviesViewModel.getMoviesByKeywordId
        identifier.equals("now_playing", true) -> moviesViewModel.getNowPlaying
        identifier.equals("upcoming", true) -> moviesViewModel.getUpcoming
        else -> moviesViewModel.getPopularMovies
    }

    private fun observeMovies(liveData: LiveData<Response<GetPopularMoviesListResponse>>) {
        liveData.observe(this) { response ->
            binding.progressBar.visibility = GONE
            setLoadMoreVisible(false)
            val loadingMore = isLoadingMore
            isLoadingMore = false

            when (response.data) {
                null -> {
                    if (loadingMore && moviesViewModel.page > 1) {
                        moviesViewModel.page--
                    }
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        if (moviesViewModel.page == 1) {
                            loadInitial()
                        } else {
                            isLoadingMore = false
                            loadNextPage()
                        }
                    }
                }

                else -> {
                    isLastPage = response.body.page >= response.body.totalPages
                    val results = response.body.results
                    if (response.body.page == 1) {
                        moviesAdapter.submitList(results)
                    } else {
                        moviesAdapter.appendList(results)
                    }
                }
            }
        }
    }

    private fun observeTrending(liveData: LiveData<Response<GetTrendingResponse>>) {
        liveData.observe(this) { response ->
            binding.progressBar.visibility = GONE
            setLoadMoreVisible(false)
            val loadingMore = isLoadingMore
            isLoadingMore = false

            when (response.data) {
                null -> {
                    if (loadingMore && moviesViewModel.page > 1) {
                        moviesViewModel.page--
                    }
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        if (moviesViewModel.page == 1) {
                            loadInitial()
                        } else {
                            isLoadingMore = false
                            loadNextPage()
                        }
                    }
                }

                else -> {
                    isLastPage = response.body.page >= response.body.totalPages
                    val results = response.body.results
                    if (response.body.page == 1) {
                        trendingAdapter.submitList(results)
                    } else {
                        trendingAdapter.appendList(results)
                    }
                }
            }
        }
    }

    private fun loadInitial() {
        moviesViewModel.page = 1
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
        moviesViewModel.page++
        fetchPage()
    }

    private fun fetchPage() {
        when {
            identifier.equals("from_genre", true) ->
                moviesViewModel.getMoviesByGenreId(genreId)

            identifier.equals("from_keyword", true) ->
                moviesViewModel.getMoviesByKeywordId(keywordId)

            identifier.equals("trending", true) ->
                moviesViewModel.getTrending()

            identifier.equals("now_playing", true) ->
                moviesViewModel.getNowPlaying()

            identifier.equals("upcoming", true) ->
                moviesViewModel.getUpcoming()

            else -> moviesViewModel.getPopularMovies()
        }
    }

    private fun setLoadMoreVisible(visible: Boolean) {
        binding.loadMoreCard.visibility = if (visible) VISIBLE else GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
