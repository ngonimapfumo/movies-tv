package zw.co.nm.moviedb.ui.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding
import zw.co.nm.moviedb.ui.search.SearchActivity
import zw.co.nm.moviedb.ui.tv.TvShowsViewModel

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        setUpView()
        tvShowsViewModel.getPopularTvShows()
        tvShowsViewModel.getPopularShows.observe(this) { response ->
            binding.progressBar.visibility = View.GONE
            when (response.data) {
                null -> {
                    Snackbar.make(binding.root, "Error getting data", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") {
                            binding.progressBar.visibility = View.VISIBLE
                            tvShowsViewModel.getPopularTvShows()

                        }.show()
                }

                else -> {
                    val data = response.body.results
                    adapter = TvShowsAdapter(data)
                    binding.recyclerView.adapter = adapter
                }
            }


        }
    }

    private fun setUpView() {
        supportActionBar?.title = getString(R.string.tv_shows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }




}