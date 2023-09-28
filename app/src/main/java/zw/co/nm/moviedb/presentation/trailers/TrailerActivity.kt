package zw.co.nm.moviedb.presentation.trailers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.data.remote.Response
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        trailerType = intent.getStringExtra(TRAILER_TYPE)
        mediaId = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        trailersViewModel = ViewModelProvider(this)[TrailersViewModel::class.java]

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

    private fun adapter(response: Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>) {
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