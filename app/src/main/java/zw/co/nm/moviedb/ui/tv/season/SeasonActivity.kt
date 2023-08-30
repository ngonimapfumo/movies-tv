package zw.co.nm.moviedb.ui.tv.season

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ActivitySeasonBinding
import zw.co.nm.moviedb.ui.tv.TvShowDetailActivity.Companion.TV_SHOW_ID_EXTRA
import zw.co.nm.moviedb.ui.viewmodel.SeasonViewModel
import zw.co.nm.moviedb.utils.Constants

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
        //tvshowid is missing here possible crash// continue from here
        seasonViewModel.getSeasonDetail(tvShowId!!, seasonNumber!!)
        seasonViewModel.getSeasonDetail.observe(this) {

            Picasso.get().load(Constants.IMAGE_BASE_URL+it.body.posterPath)
                .into(binding.seasonPoster)
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