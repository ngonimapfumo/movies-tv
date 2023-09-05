package zw.co.nm.moviedb.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityReviewsBinding
import zw.co.nm.moviedb.ui.adapters.ReviewsAdapter
import zw.co.nm.moviedb.ui.viewmodel.ReviewsViewModel

class ReviewsActivity : AppCompatActivity() {
    private lateinit var adapter: ReviewsAdapter
    lateinit var binding: ActivityReviewsBinding
    private lateinit var reviewsViewModel: ReviewsViewModel
    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        reviewsViewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]
        tvShowId = intent.getIntExtra(ID, 0)
        reviewsViewModel.getTvReviews(tvShowId!!)
        reviewsViewModel.getTvReviews.observe(this) {

            if (it.body.results.isEmpty()) {
                binding.noResultLay.visibility = View.VISIBLE
            } else  {
                binding.noResultLay.visibility = View.GONE
            }
            val data = it.body.results
            adapter = ReviewsAdapter(data)
            binding.reviewsRecycler.adapter = adapter

        }
    }

    companion object {
        const val ID: String = "tv_id"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}