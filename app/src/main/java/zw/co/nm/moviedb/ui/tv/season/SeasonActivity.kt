package zw.co.nm.moviedb.ui.tv.season

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivitySeasonBinding
import zw.co.nm.moviedb.ui.tv.episode.EpisodeAdapter
import zw.co.nm.moviedb.utils.ConfigStore
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.Constants.SAVED_SHOW_ID
import java.time.LocalDate

class SeasonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeasonBinding
    private lateinit var seasonViewModel: SeasonViewModel
    private lateinit var adapter: EpisodeAdapter
    private var seasonNumber: Int? = null
    private var tvShowId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        seasonViewModel = ViewModelProvider(this)[SeasonViewModel::class.java]
        seasonNumber = intent.getIntExtra(SEASON_ID, 0)
        tvShowId = ConfigStore.getInt(this, SAVED_SHOW_ID)
        seasonViewModel.getSeasonDetail(tvShowId!!, seasonNumber!!)
        seasonViewModel.getSeasonDetail.observe(this) {
            Picasso.get().load(Constants.IMAGE_BASE_URL + it!!.body.posterPath)
                .placeholder(R.drawable.sample_cover_small)
                .into(binding.seasonPoster)

            when {
                it.body.overview.isEmpty() -> {
                    binding.seasonOverView.text = it.body.name
                }

                else -> {
                    binding.seasonOverView.text = it.body.overview
                }
            }
            binding.seasonName.text = it.body.name
            binding.episodeCount.text = buildString {
                append(it.body.episodes.size)
                append(" Episodes")
            }
            when {
                it.body.airDate.isNullOrBlank() -> {
                    binding.seasonAirDate.text = ""
                }
                else -> {
                    val localDate = LocalDate.parse(it.body.airDate)
                    binding.seasonAirDate.text = buildString {
                        append(localDate.year)
                    }
                }
            }

            val data = it.body.episodes
            adapter = EpisodeAdapter(data)
            binding.episodeRecycler.adapter = adapter
        }


    }

    companion object {
        const val TV_NAME: String = "show_name"
        const val SEASON_ID: String = "season_id"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}