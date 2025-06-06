package zw.co.nm.moviedb.presentation.trailers

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.databinding.ActivityTrailerBinding
import zw.co.nm.moviedb.util.Constants.TRAILER_TYPE
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class TrailerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTrailerBinding
    private lateinit var trailersViewModel: TrailersViewModel
    private lateinit var adapter: TrailersAdapter
    private var mediaId: Int? = null
    private var trailerType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        trailerType = intent.getStringExtra(TRAILER_TYPE)
        mediaId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        trailersViewModel = ViewModelProvider(this)[TrailersViewModel::class.java]

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

        if (trailerType.equals("movie")) {
            trailersViewModel.getTrailers(mediaId!!)
            trailersViewModel.getTrailers.observe(this) { response ->

                when (response.data) {
                    null -> {
                        actionSnack(binding.root, "Error getting data", "Retry") {
                            trailersViewModel.getTrailers(mediaId!!)
                        }
                    }

                    else -> {
                        adapter(response)
                    }
                }

            }

        } else if (trailerType.equals("tv")) {
            trailersViewModel.getTvTrailers(mediaId!!)
            trailersViewModel.getTVTrailers.observe(this) { response ->
                when (response.data) {
                    null -> {
                        actionSnack(binding.root, "Error getting data", "Retry") {
                            trailersViewModel.getTvTrailers(mediaId!!)
                        }
                    }

                    else -> {
                        adapter(response)
                    }
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }

    private fun adapter(response: Response<GetTrailersResponse>) {
        if (response.body.results.isEmpty()) {
            binding.noResultLay.visibility = View.VISIBLE
        } else {
            binding.noResultLay.visibility = View.GONE
        }
        val data = response.body.results
        adapter = TrailersAdapter(data)
        binding.trailerRecycler.adapter = adapter
    }
}