package zw.co.nm.moviedb.presentation.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainListBinding
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.TvShowsViewModel
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        setUpView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) {
                view, insets, ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            binding.main.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }
        tvShowsViewModel.getPopularTvShows()
        tvShowsViewModel.getPopularShows.observe(this) { response ->

            when (response.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        binding.progressBar.visibility = View.VISIBLE
                        tvShowsViewModel.getPopularTvShows()
                    }
                }

                else -> {
                    binding.prevB.isEnabled = response.body.page != 1
                    binding.progressBar.visibility = View.GONE
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