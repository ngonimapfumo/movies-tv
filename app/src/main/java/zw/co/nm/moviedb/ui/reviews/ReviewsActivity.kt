package zw.co.nm.moviedb.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetReviewsResponse
import zw.co.nm.moviedb.databinding.ActivityReviewsBinding
import zw.co.nm.moviedb.ui.adapters.ReviewsAdapter
import zw.co.nm.moviedb.ui.viewmodel.ReviewsViewModel
import zw.co.nm.moviedb.utils.Constants.REVIEW_MOVIE
import zw.co.nm.moviedb.utils.Constants.REVIEW_TV
import zw.co.nm.moviedb.utils.Constants.REVIEW_TYPE

class ReviewsActivity : AppCompatActivity() {
    private lateinit var adapter: ReviewsAdapter
    lateinit var binding: ActivityReviewsBinding
    private lateinit var reviewsViewModel: ReviewsViewModel
    private var tvShowId: Int? = null
    private var reviewType: String? = null
    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        reviewsViewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]
        tvShowId = intent.getIntExtra(ID, 0)
        movieId = intent.getIntExtra(ID, 0)
        reviewType = intent.getStringExtra(REVIEW_TYPE)
        if (reviewType.equals(REVIEW_TV)) {
            reviewsViewModel.getTvReviews(tvShowId!!)
            reviewsViewModel.getTvReviews.observe(this) {
                adapter(it)

            }
        } else if (reviewType.equals(REVIEW_MOVIE)) {
            reviewsViewModel.getMovieReviews(movieId!!)
            reviewsViewModel.getMovieReviews.observe(this) {
                adapter(it)

            }
        }

    }

    private fun adapter(response: Response<GetReviewsResponse>) {
        if (response.body.results.isEmpty()) {
            binding.noResultLay.visibility = View.VISIBLE
        } else {
            binding.noResultLay.visibility = View.GONE
        }
        val data = response.body.results
        adapter = ReviewsAdapter(data)
        binding.reviewsRecycler.adapter = adapter
    }

    companion object {
        const val ID: String = "tv_id"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}