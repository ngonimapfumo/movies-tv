package zw.co.nm.moviedb.ui.search

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.ui.adapters.SearchAdapter
import zw.co.nm.moviedb.ui.movie.MovieViewModel

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.setOnCloseListener(this)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.searchMulti(query!!).collect {
                        response ->
                    val data = response.data!!.body()!!.results
                    adapter = SearchAdapter(data)
                    binding.searchRecycler.adapter = adapter

                }

            }
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