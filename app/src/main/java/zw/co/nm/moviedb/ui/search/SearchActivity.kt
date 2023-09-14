package zw.co.nm.moviedb.ui.search

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.ui.adapters.SearchAdapter
import zw.co.nm.moviedb.ui.movie.MoviesViewModel

class SearchActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: SearchAdapter
    private var queryStr: String? = null
    private var totalPages: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.progressBar2.visibility = VISIBLE
        if (moviesViewModel.page > 1) {
            moviesViewModel.resetPages()
        }

        //todo// fix loading progressbar when no network
        moviesViewModel.searchMulti(query!!)
        moviesViewModel.searchMulti.observe(this) { response ->
            binding.progressBar2.visibility = GONE
            queryStr = query
            val data = response.body.results
            totalPages = response.body.totalPages
            if (totalPages!! > 1) {
                binding.constraintLayoutPages.visibility = VISIBLE
                binding.nextB.isEnabled = moviesViewModel.page != response.body.totalPages
            } else {
                binding.constraintLayoutPages.visibility = GONE
            }

            if (response.body.results.isEmpty()) {
                binding.searchRecycler.visibility = GONE
                binding.noResultLay.visibility = VISIBLE
            } else {
                binding.searchRecycler.visibility = VISIBLE
                binding.noResultLay.visibility = GONE
            }
            adapter = SearchAdapter(data)
            binding.searchRecycler.adapter = adapter
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun setUpView() {
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.nextB.setOnClickListener {
            moviesViewModel.page++
            moviesViewModel.searchMulti(queryStr!!)
        }
        binding.prevB.setOnClickListener {
            if (moviesViewModel.page != 1) {
                moviesViewModel.page--
                moviesViewModel.searchMulti(queryStr!!)
            } else {
                return@setOnClickListener
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}