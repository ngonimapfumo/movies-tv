package zw.co.nm.moviedb.presentation.lists

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.databinding.ActivityListDetailBinding
import zw.co.nm.moviedb.presentation.watchlist.WatchlistAdapter
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class ListDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListDetailBinding
    private lateinit var viewModel: ListsViewModel
    private lateinit var adapter: WatchlistAdapter
    private var listId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listId = intent.getIntExtra(Constants.EXTRA_LIST_ID, 0)
        val listName = intent.getStringExtra(Constants.EXTRA_LIST_NAME).orEmpty()
        supportActionBar?.title = listName.ifBlank { getString(R.string.custom_lists) }

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

        viewModel = ViewModelProvider(this)[ListsViewModel::class.java]
        adapter = WatchlistAdapter(onRemove = { movie -> confirmRemove(movie) })
        binding.recyclerView.adapter = adapter

        viewModel.listDetail.observe(this) { response ->
            binding.progressBar.visibility = GONE
            when (response.data) {
                null -> actionSnack(binding.root, getString(R.string.lists_load_error), "Retry") {
                    loadList()
                }

                else -> {
                    if (response.body.name.isNotBlank()) {
                        supportActionBar?.title = response.body.name
                    }
                    adapter.submitList(response.body.items)
                    if (response.body.items.isEmpty()) {
                        Toast.makeText(this, R.string.list_items_empty, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.mutation.observe(this) { mutation ->
            if (mutation.success && mutation.type == ListMutation.Type.REMOVE) {
                Toast.makeText(this, R.string.removed_from_list, Toast.LENGTH_SHORT).show()
                loadList()
            }
        }

        loadList()
    }

    private fun loadList() {
        if (listId == 0) return
        binding.progressBar.visibility = VISIBLE
        viewModel.getListDetail(listId)
    }

    private fun confirmRemove(movie: GetPopularMoviesListResponse.Result) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.remove_from_list)
            .setMessage(getString(R.string.remove_from_list_confirm, movie.title))
            .setPositiveButton(R.string.remove) { _, _ ->
                viewModel.removeMovieFromList(listId, movie.id)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
