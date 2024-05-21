package zw.co.nm.moviedb.presentation.tv

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.TVCastAdapter
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.presentation.search.SearchActivity
import zw.co.nm.moviedb.presentation.tv.season.SeasonsAdapter
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private var showId: Int? = null
    private var productionCompanies: ArrayList<String>? = arrayListOf()
    private var iso6391: String? = null
    private var iso31661: String? = null
    private var logos: ArrayList<String>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        iso6391 = ConfigStore.getStringLang(this, Constants.LANGUAGE_KEY)!!.substring(0, 2)
        iso31661 = ConfigStore.getStringLang(this, Constants.LANGUAGE_KEY)!!.substring(3)
        showId = intent.getIntExtra(TV_SHOW_ID_EXTRA, 0)
        binding.reviewsBtn.setOnClickListener {
            PageNavUtils.navReviewsPage(this, "tv_show", showId!!)
        }
        ConfigStore.saveIntConfig(
            this, Constants.SAVED_SHOW_ID,
            showId!!
        )
        binding.trailerBtn.setOnClickListener {
            PageNavUtils.navTrailersPage(this, "tv", showId!!)
        }
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) {

            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }

                else -> {

                    val tv = it.body
                    Picasso.get().load(IMAGE_BASE_URL + tv.posterPath)
                        .resize(500, 750)
                        .placeholder(R.drawable.sample_cover_large_exp)
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

                    if (tv.firstAirDate.isEmpty()) {
                        binding.yearTxt.text = ""
                    } else {
                        val simpleDate = LocalDate.parse(tv.firstAirDate)
                        binding.yearTxt.text = buildString {
                            append("First air ")
                            append(simpleDate.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                            append(" ")
                            append(simpleDate.year)

                        }
                    }
                    binding.statusTxt.text = tv.status
                    tv.productionCompanies.forEach { productionCompany ->
                        productionCompanies!!.add(productionCompany.name)
                        binding.prodCompany.text = productionCompanies.toString().replace("[", "")
                            .replace("]", "")
                    }
                    binding.tvRatingTxt.text = buildString {
                        append((tv.voteAverage * 10).toInt().toString())
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


        }

        tvShowsViewModel.getTvImages(showId!!)
        tvShowsViewModel.getTVImages.observe(this) { images ->

            when (images.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }

                else -> {

                    images.body.logos.forEach {
                        when (it.iso6391) {
                            iso6391 -> {
                                binding.tvLogo.visibility = View.VISIBLE
                                binding.tvTitleTxt.visibility = GONE
                                logos!!.add(IMAGE_BASE_URL + it.filePath)
                            }
                        }
                    }

                    when (logos!!.size) {
                        0 -> {
                            binding.tvTitleTxt.visibility = View.VISIBLE
                        }

                        else -> Picasso.get().load(logos!![0]).into(binding.tvLogo)
                    }


                }
            }
        }

        tvShowsViewModel.getTvCredits(showId!!)
        tvShowsViewModel.getTvCredits.observe(this) { response ->

            when (response!!.data) {
                null -> {
                    actionSnack(binding.root, Constants.NETWORK_ERROR_MSG, "Retry") {
                        tvShowsViewModel.getShowDetails(showId!!)
                        tvShowsViewModel.getTvImages(showId!!)
                        tvShowsViewModel.getTvCredits(showId!!)
                    }
                }
                else -> {

                    val adapter: TVCastAdapter
                    binding.castRecyclerView.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    val data = response.body.cast
                    adapter = TVCastAdapter(data)
                    binding.castRecyclerView.adapter = adapter
                    if (response.body.cast.isEmpty()) {
                        binding.textView8.visibility = GONE
                    }

                }
            }


        }

    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}