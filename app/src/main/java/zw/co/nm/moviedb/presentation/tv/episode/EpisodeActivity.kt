package zw.co.nm.moviedb.presentation.tv.episode

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.GuestCastAdapter
import zw.co.nm.moviedb.databinding.ActivityEpisodeBinding
import zw.co.nm.moviedb.presentation.tv.TvShowsViewModel
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
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
            val episode = it!!.body
            //  binding.progressBar4.visibility = GONE
            when (it!!.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        tvViewModel.getEpisodeDetail(seriesId!!, seasonNumber!!, episodeNumber!!)
                    }
                }

                else -> {
                    binding.mainLayout.visibility = VISIBLE
                    Picasso.get()
                        .load(Constants.IMAGE_BASE_URL + episode.stillPath)
                        .placeholder(R.drawable.sample_episode)
                        .into(binding.episodePoster)
                    binding.episodeName.text = episode.name
                    if (episode.overview.isEmpty()) {
                        binding.episodeOverView.text = "No information available"
                    } else {
                        binding.episodeOverView.text = episode.overview
                    }


                    binding.guestsRecycler.layoutManager = LinearLayoutManager(
                        this@EpisodeActivity,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    var adapter = GuestCastAdapter(episode.guestStars)
                    binding.guestsRecycler.adapter = adapter

                    binding.ratingTxt.text = buildString {
                        append((episode.voteAverage * 10).toInt().toString())
                        append("%")
                        append(" (${episode.voteCount} votes)")
                    }
                    when (episode.airDate) {
                        null -> {
                            binding.EpisodeAirDate.text = "N/A"
                        }

                        else -> {
                            var simpleDate = LocalDate.parse(episode.airDate)
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
                        }
                    }


                    if (episode.guestStars.isEmpty()) {
                        binding.textView19.visibility = GONE
                    }


                    binding.textViewEpisodeNumber.text = buildString {
                        append("Episode ")
                        append(episode.episodeNumber)
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