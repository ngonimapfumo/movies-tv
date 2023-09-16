package zw.co.nm.moviedb.ui.tv.episode

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityEpisodeBinding
import zw.co.nm.moviedb.ui.adapters.GuestCastAdapter
import zw.co.nm.moviedb.ui.tv.TvShowsViewModel
import zw.co.nm.moviedb.util.Constants
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class EpisodeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEpisodeBinding
    private var seriesId: Int? = null
    private var seasonNumber: Int? = null
    private var episodeNumber: Int? = null
    private lateinit var tvViewModel: TvShowsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle = intent.extras
        bundle!!.getBundle(BUNDLE_EXTRAS)
        seriesId = bundle.getInt("seriesId")
        seasonNumber = bundle.getInt("seasonNumber")
        episodeNumber = bundle.getInt("episodeNumber")
        tvViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvViewModel.getEpisodeDetail(seriesId!!, seasonNumber!!, episodeNumber!!)
        tvViewModel.getEpisodeDetail.observe(this) {
          //  binding.progressBar4.visibility = GONE
            when (it!!.data) {
                null -> {
                    Snackbar.make(binding.root, "Error getting data", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") {
                        //    binding.progressBar4.visibility = VISIBLE
                            tvViewModel.getEpisodeDetail(seriesId!!, seasonNumber!!, episodeNumber!!)

                        }.show()

                }

                else -> {
                    binding.mainLayout.visibility = VISIBLE
                    Picasso.get()
                        .load(Constants.IMAGE_BASE_URL + it.body.stillPath)
                        .placeholder(R.drawable.sample_episode)
                        .into(binding.episodePoster)
                    binding.episodeName.text = it.body.name
                    binding.episodeOverView.text = it.body.overview

                    binding.guestsRecycler.layoutManager = LinearLayoutManager(
                        this@EpisodeActivity,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    var adapter = GuestCastAdapter(it.body.guestStars)
                    binding.guestsRecycler.adapter = adapter

                    binding.ratingTxt.text = buildString {
                        append((it.body.voteAverage * 10).toInt().toString())
                        append("%")
                        append(" (${it.body.voteCount} votes)")
                    }
                    var simpleDate = LocalDate.parse(it.body.airDate)
                    binding.EpisodeAirDate.text = buildString {
                        append(simpleDate.dayOfMonth)
                        append(" ")
                        append(
                            simpleDate.month.getDisplayName(
                                TextStyle.SHORT,
                                Locale.ENGLISH
                            )
                        )
                        append(" ")
                        append(simpleDate.year)
                    }

                    if (it.body.guestStars.isEmpty()) {
                        binding.textView19.visibility = GONE
                    }


                    binding.textViewEpisodeNumber.text = buildString {
                        append("Episode ")
                        append(it.body.episodeNumber)
                    }
                }

            }

        }
    }

    companion object {
        const val BUNDLE_EXTRAS: String = "extras"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}