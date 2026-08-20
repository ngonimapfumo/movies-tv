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
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.util.EndlessScrollListener

class SearchActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private var queryStr: String = ""
    private var isLoadingMore = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.title = "Search"
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

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        queryStr = newText.orEmpty()
        searchViewModel.resetPages()
        isLastPage = false
        isLoadingMore = false

        if (queryStr.isBlank()) {
            adapter.submitList(emptyList())
            binding.progressBar2.visibility = GONE
            binding.loadMoreCard.visibility = GONE
            binding.noResultLay.visibility = GONE
            binding.searchRecycler.visibility = VISIBLE
            return true
        }

        binding.progressBar2.visibility = VISIBLE
        binding.loadMoreCard.visibility = GONE
        searchViewModel.searchMulti(queryStr)
        return true
    }

    private fun setUpView() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        adapter = SearchAdapter()
        binding.searchRecycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                if (results.isEmpty() && queryStr.isNotEmpty()) {
                    binding.textView14.text = "No results found"
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
