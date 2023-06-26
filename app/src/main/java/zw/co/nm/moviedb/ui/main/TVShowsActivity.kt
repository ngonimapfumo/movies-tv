package zw.co.nm.moviedb.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.ui.adapters.MoviesAdapter
import zw.co.nm.moviedb.ui.adapters.TvShowsAdapter
import zw.co.nm.moviedb.ui.viewmodels.MoviesViewModel
import zw.co.nm.moviedb.ui.viewmodels.TvShowsViewModel

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.tv_shows)
        tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        setUpView()
        tvShowsViewModel.getPopularTvShows()
        tvShowsViewModel.getPopularShows.observe(this) { response ->
            val data = response.body.results
            adapter = TvShowsAdapter(data)
            binding.recyclerView.adapter = adapter

        }
    }

    private fun setUpView() {
        binding.nextB.setOnClickListener {
            tvShowsViewModel.page++
            tvShowsViewModel.getPopularTvShows()
        }

        binding.prevB.setOnClickListener {
            if (tvShowsViewModel.page != 1) {
                tvShowsViewModel.page--
                tvShowsViewModel.getPopularTvShows()
            } else {
                return@setOnClickListener
            }
        }
    }
}