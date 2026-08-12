package zw.co.nm.moviedb.presentation.main.movies

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainListBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.util.EndlessScrollListener
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var genreId: Int = 0
    private var identifier: String = ""
    private var isLoadingMore = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.movies)

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
        identifier = intent.getStringExtra("identifier").orEmpty()

        adapter = MoviesAdapter()
        binding.recyclerView.adapter = adapter
        setUpScroll()
        observeMovies()
        loadInitial()
    }

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

    private fun observeMovies() {
        val liveData = if (identifier.equals("from_genre", true)) {
            moviesViewModel.getMovieByGenreId
        } else {
            moviesViewModel.getPopularMovies
        }

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
                        adapter.submitList(results)
                    } else {
                        adapter.appendList(results)
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
        if (identifier.equals("from_genre", true)) {
            moviesViewModel.getMoviesByGenreId(genreId)
        } else {
            moviesViewModel.getPopularMovies()
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
