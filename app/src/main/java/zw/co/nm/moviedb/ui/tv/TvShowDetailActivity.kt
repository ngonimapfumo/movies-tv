package zw.co.nm.moviedb.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.ui.adapters.SeasonsAdapter
import zw.co.nm.moviedb.ui.adapters.TVCastAdapter
import zw.co.nm.moviedb.ui.viewmodel.TvShowsViewModel
import zw.co.nm.moviedb.utils.ConfigStore
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.PageNavUtils
import java.time.LocalDate

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private var showId: Int? = null
    private var productionCompanies: ArrayList<String>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showId = intent.getIntExtra(TV_SHOW_ID_EXTRA, 0)
        binding.reviewsBtn.setOnClickListener {
            PageNavUtils.toReviewsPage(this, showId!!)
        }
        ConfigStore.saveIntConfig(
            this, Constants.SAVED_SHOW_ID,
            showId!!
        )
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) {
            val tv = it!!.body
            Picasso.get().load(IMAGE_BASE_URL + tv.posterPath)
                .placeholder(R.drawable.sample_cover_large)
                .into(binding.tvBackgroundImm)
            binding.tvSummaryTxt.text = tv.tagline
            when {
                tv.overview.isEmpty() -> {
                    binding.detailedSummaryTxt.text = getString(R.string.no_info)
                }

                else -> {
                    binding.detailedSummaryTxt.text = tv.overview
                }
            }

            binding.tvTitleTxt.text = tv.name

            val localDate = LocalDate.parse(tv.firstAirDate)
            binding.yearTxt.text = buildString {
                append("First air ")
                append(localDate.year)
            }
            binding.statusTxt.text = tv.status
            tv.productionCompanies.forEach { productionCompany ->
                productionCompanies!!.add(productionCompany.name)
                binding.prodCompany.text = productionCompanies.toString().replace("[", "")
                    .replace("]", "")
            }
            binding.tvRatingTxt.text = buildString {
                append((tv.voteAverage!! * 10).toInt().toString())
                append("%")
                append(" (${tv.voteCount} votes)")
            }

            val adapter: SeasonsAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = tv.seasons
            adapter = SeasonsAdapter(data)
            binding.recyclerView.adapter = adapter

        }

        tvShowsViewModel.getTvCredits(showId!!)
        tvShowsViewModel.getTvCredits.observe(this) { response ->
            val adapter: TVCastAdapter
            binding.castRecyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false
            )
            val data = response!!.body.cast
            adapter = TVCastAdapter(data)
            binding.castRecyclerView.adapter = adapter

        }

    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}