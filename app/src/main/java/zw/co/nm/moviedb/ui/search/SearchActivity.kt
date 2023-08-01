package zw.co.nm.moviedb.ui.search

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.ui.adapters.SearchAdapter
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: SearchAdapter
    private var queryStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (moviesViewModel.page > 1) {
            moviesViewModel.resetPage()
        }

        moviesViewModel.searchMulti(query!!)
        moviesViewModel.searchMulti.observe(this) { response ->
            queryStr = query
            val data = response.body.results
            /*if (data.isEmpty()) {
                Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show()

            }*/
            if (response.body.totalPages > 1) {
                binding.constraintLayoutPages.visibility = VISIBLE
                binding.nextB.isEnabled = moviesViewModel.page != response.body.totalPages
            }
            adapter = SearchAdapter(data)
            binding.searchRecycler.adapter = adapter


        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //sa
        //  moviesViewModel.page = 1

        return false
    }


    private fun setUpView() {
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        binding.searchView.setOnCloseListener(this)
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

    override fun onClose(): Boolean {
        Toast.makeText(this@SearchActivity, "close", Toast.LENGTH_SHORT).show()
        return true
    }


}