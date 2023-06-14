package zw.co.nm.moviedb.ui.search

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.setOnCloseListener(this)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        moviesViewModel.searchMulti(query!!)
        moviesViewModel.searchMulti.observe(this) { response ->
            val data = response.body()!!.results
            adapter = SearchAdapter(data)
            binding.searchRecycler.adapter = adapter

        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // TODO("Not yet implemented")
        return false
    }

    override fun onClose(): Boolean {
        Toast.makeText(this@SearchActivity, "hello", Toast.LENGTH_SHORT).show()
        return true
    }


}