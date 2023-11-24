package zw.co.nm.moviedb.presentation.search

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
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
        /*binding.progressBar2.visibility = VISIBLE
        if (searchViewModel.page > 1) {
            searchViewModel.resetPages()
        }*/

        //   searchViewModel.searchMulti(query!!)

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        binding.progressBar2.visibility = VISIBLE
        if (searchViewModel.page > 1) {
            searchViewModel.resetPages()
        }
        searchViewModel.searchMulti(newText!!)
        searchViewModel.searchMulti.observe(this) { response ->

            binding.progressBar2.visibility = GONE
            queryStr = newText
            val data = response.body.results
            totalPages = response.body.totalPages
            if (totalPages!! > 1) {
                binding.constraintLayoutPages.visibility = VISIBLE
                binding.nextB.isEnabled = searchViewModel.page != response.body.totalPages
            } else {
                binding.constraintLayoutPages.visibility = GONE
            }

            if (response.body.results.isEmpty() && queryStr!!.isNotEmpty()) {
                /*binding.textView10.animate().apply {
                    duration = 1000
                    rotationYBy(360f)
                }.withEndAction {
                    binding.textView10.animate().apply {
                        duration = 1000
                        rotationYBy(3600f)
                    }.start()
                }*/
                binding.textView14.text = buildString {
                    append("No results found")
                }
                binding.searchRecycler.visibility = GONE
                binding.noResultLay.visibility = VISIBLE
            } else {
                binding.searchRecycler.visibility = VISIBLE
                binding.noResultLay.visibility = GONE
            }
            binding.prevB.isEnabled = response.body.page != 1
            adapter = SearchAdapter(data)
            binding.searchRecycler.adapter = adapter
        }
        return true
    }

    private fun setUpView() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.nextB.setOnClickListener {
            searchViewModel.page++
            searchViewModel.searchMulti(queryStr!!)
        }
        binding.prevB.setOnClickListener {
            if (searchViewModel.page != 1) {
                searchViewModel.page--
                searchViewModel.searchMulti(queryStr!!)
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