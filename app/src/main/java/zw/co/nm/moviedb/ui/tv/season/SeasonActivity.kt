package zw.co.nm.moviedb.ui.tv.season

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivitySeasonBinding
import zw.co.nm.moviedb.ui.viewmodel.SeasonViewModel
import zw.co.nm.moviedb.utils.ConfigStore
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.Constants.SAVED_SHOW_ID

class SeasonActivity : AppCompatActivity() {
    lateinit var binding: ActivitySeasonBinding
    private lateinit var seasonViewModel: SeasonViewModel

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
            Picasso.get().load(Constants.IMAGE_BASE_URL + it.body.posterPath)
                .placeholder(R.drawable.sample_cover_small)
                .into(binding.seasonPoster)

            when {
                it.body.overview.isEmpty() -> {
                    binding.seasonOverView.text = getString(R.string.no_info)
                }
                else -> {
                    binding.seasonOverView.text = it.body.overview
                }
            }
            binding.seasonName.text = it.body.name
        }

    }

    companion object {
        const val SEASON_ID: String = "season_id"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}