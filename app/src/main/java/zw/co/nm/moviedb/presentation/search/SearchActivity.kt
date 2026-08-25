package zw.co.nm.moviedb.presentation.search

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.EndlessScrollListener

class SearchActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private var queryStr: String = ""
    private var isLoadingMore = false
    private var isLastPage = false
    private var lastSavedQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.title = getString(R.string.search)
        setUpView()

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
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val text = query.orEmpty().trim()
        if (text.isBlank()) return true
        binding.searchView.clearFocus()
        runSearch(text, saveRecent = true)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val text = newText.orEmpty()
        if (text.isBlank()) {
            queryStr = ""
            searchViewModel.resetPages()
            isLastPage = false
            isLoadingMore = false
            adapter.submitList(emptyList())
            binding.progressBar2.visibility = GONE
            binding.loadMoreCard.visibility = GONE
            binding.noResultLay.visibility = GONE
            binding.searchRecycler.visibility = VISIBLE
            refreshRecentSearches()
        } else {
            binding.recentSearchesSection.visibility = GONE
        }
        return true
    }

    private fun setUpView() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        adapter = SearchAdapter()
        binding.searchRecycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        binding.searchView.imeOptions = android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recentSearchesRecycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.clearRecentSearchesBtn.setOnClickListener {
            ConfigStore.clearRecentSearches(this)
            refreshRecentSearches()
        }
        refreshRecentSearches()

        val layoutManager = binding.searchRecycler.layoutManager as LinearLayoutManager
        binding.searchRecycler.addOnScrollListener(
            EndlessScrollListener(layoutManager) { loadNextPage() }
        )
        binding.searchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val shouldShow = recyclerView.computeVerticalScrollOffset() > 800
                binding.scrollTopFab.visibility = if (shouldShow) VISIBLE else GONE
            }
        })
        binding.scrollTopFab.setOnClickListener {
            binding.searchRecycler.smoothScrollToPosition(0)
        }

        searchViewModel.searchMulti.observe(this) { response ->
            binding.progressBar2.visibility = GONE
            binding.loadMoreCard.visibility = GONE
            val loadingMore = isLoadingMore
            isLoadingMore = false

            if (!response.isSuccessful) {
                if (loadingMore && searchViewModel.page > 1) {
                    searchViewModel.page--
                }
                return@observe
            }

            isLastPage = response.body.page >= response.body.totalPages
            val results = response.body.results

            if (response.body.page == 1) {
                adapter.submitList(results)
                if (results.isNotEmpty() && queryStr.isNotBlank() && queryStr != lastSavedQuery) {
                    ConfigStore.addRecentSearch(this, queryStr)
                    lastSavedQuery = queryStr
                }
                if (results.isEmpty() && queryStr.isNotEmpty()) {
                    binding.textView14.text = getString(R.string.no_results_found)
                    binding.searchRecycler.visibility = GONE
                    binding.noResultLay.visibility = VISIBLE
                } else {
                    binding.searchRecycler.visibility = VISIBLE
                    binding.noResultLay.visibility = GONE
                }
            } else {
                adapter.appendList(results)
            }
        }
    }

    private fun runSearch(query: String, saveRecent: Boolean) {
        queryStr = query
        searchViewModel.resetPages()
        isLastPage = false
        isLoadingMore = false
        binding.recentSearchesSection.visibility = GONE
        binding.progressBar2.visibility = VISIBLE
        binding.loadMoreCard.visibility = GONE
        binding.noResultLay.visibility = GONE
        binding.searchRecycler.visibility = VISIBLE
        if (saveRecent) {
            ConfigStore.addRecentSearch(this, query)
            lastSavedQuery = query
        }
        searchViewModel.searchMulti(queryStr)
    }

    private fun refreshRecentSearches() {
        val recent = ConfigStore.getRecentSearches(this)
        if (recent.isEmpty() || queryStr.isNotBlank()) {
            binding.recentSearchesSection.visibility = GONE
            return
        }
        binding.recentSearchesSection.visibility = VISIBLE
        binding.recentSearchesRecycler.adapter = RecentSearchChipsAdapter(recent) { query ->
            binding.searchView.setQuery(query, false)
            runSearch(query, saveRecent = true)
        }
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage || queryStr.isBlank()) return
        isLoadingMore = true
        binding.loadMoreCard.visibility = VISIBLE
        searchViewModel.page++
        searchViewModel.searchMulti(queryStr)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
