package zw.co.nm.moviedb.ui.search

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivitySearchBinding
import zw.co.nm.moviedb.ui.adapters.SearchAdapter
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        moviesViewModel.searchMulti(query!!)
        moviesViewModel.searchMulti.observe(this) { response ->

            val data = response.body.results
            if (data.isEmpty()) {
                /* GeneralUtil.generalAlertDialog(this,
                 "Alert",
                 "No results for this search, please try another search",
                 "OKAY","",
                 null,null)*/

                Toast.makeText(this, "no dataa", Toast.LENGTH_SHORT).show()
            }

            if (response.body.totalPages > 1){
                binding.constraintLayoutPages.visibility = VISIBLE
            }else{binding.constraintLayoutPages.visibility = GONE}
                adapter = SearchAdapter(data)
            binding.searchRecycler.adapter = adapter

        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // TODO("Not yet implemented")
        return false
    }

    private fun setUpView() {
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.onActionViewExpanded()
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }


}