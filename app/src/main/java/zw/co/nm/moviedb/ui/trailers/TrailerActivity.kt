package zw.co.nm.moviedb.ui.trailers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.data.remote.networkmodel.GetTrailersResponse
import zw.co.nm.moviedb.databinding.ActivityTrailerBinding
import zw.co.nm.moviedb.ui.adapters.TrailersAdapter
import zw.co.nm.moviedb.ui.viewmodel.TrailersViewModel
import zw.co.nm.moviedb.utils.Constants.TRAILER_TYPE

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
                adapter(response)
            }

        } else if (trailerType.equals("tv")) {
            trailersViewModel.getTvTrailers(mediaId!!)
            trailersViewModel.getTVTrailers.observe(this) { response ->
                adapter(response)
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

    private fun adapter(response: retrofit2.Response<GetTrailersResponse>) {
        if (response.body()!!.results.isEmpty()) {
            binding.noResultLay.visibility = View.VISIBLE
        } else {
            binding.noResultLay.visibility = View.GONE
        }
        val data = response.body()!!.results
        adapter = TrailersAdapter(data)
        binding.trailerRecycler.adapter = adapter
    }
}