package zw.co.nm.moviedb.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.ui.adapters.SeasonsAdapter
import zw.co.nm.moviedb.ui.viewmodels.TvShowsViewModel
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL

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
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) { it ->
            val tv = it!!.body
            //we continue from here
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

    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}