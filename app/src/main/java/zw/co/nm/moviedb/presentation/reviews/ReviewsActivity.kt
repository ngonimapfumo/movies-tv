package zw.co.nm.moviedb.presentation.reviews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetReviewsResponse
import zw.co.nm.moviedb.databinding.ActivityReviewsBinding
import zw.co.nm.moviedb.util.Constants.REVIEW_MOVIE
import zw.co.nm.moviedb.util.Constants.REVIEW_TV
import zw.co.nm.moviedb.util.Constants.REVIEW_TYPE
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class ReviewsActivity : AppCompatActivity() {
    private lateinit var adapter: ReviewsAdapter
    lateinit var binding: ActivityReviewsBinding
    private lateinit var reviewsViewModel: ReviewsViewModel
    private var reviewType: String? = null
    private var mediaId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        reviewsViewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]

        mediaId = intent.getIntExtra(ID, 0)
        reviewType = intent.getStringExtra(REVIEW_TYPE)
        if (reviewType.equals(REVIEW_TV)) {
            reviewsViewModel.getTvReviews(mediaId!!)
            reviewsViewModel.getTvReviews.observe(this) { response ->
                when (response.data) {
                    null -> {
                        actionSnack(binding.root, "Error getting data", "Retry") {
                            reviewsViewModel.getTvReviews(mediaId!!)
                        }
                    }

                    else -> {
                        adapter(response)
                    }
                }

            }
        } else if (reviewType.equals(REVIEW_MOVIE)) {
            reviewsViewModel.getMovieReviews(mediaId!!)
            reviewsViewModel.getMovieReviews.observe(this) { response ->
                when (response.data) {
                    null -> {
                        actionSnack(binding.root, "Error getting data", "Retry") {
                            reviewsViewModel.getMovieReviews(mediaId!!)
                        }
                    }

                    else -> {
                        adapter(response)
                    }
                }


            }
        }

    }

    private fun adapter(response: Response<GetReviewsResponse>) {
        when {
            response.body.results.isEmpty() -> {
                binding.noResultLay.visibility = View.VISIBLE
            }

            else -> {
                binding.noResultLay.visibility = View.GONE
            }
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