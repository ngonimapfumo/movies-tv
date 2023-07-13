package zw.co.nm.moviedb.ui.tv

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ActivityTvShowDetailBinding
import zw.co.nm.moviedb.ui.viewmodels.TvShowsViewModel
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private var showId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showId = intent.getIntExtra(TV_SHOW_ID_EXTRA, 0)
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) { tv->
            //we continue from here
         Picasso.get().load(IMAGE_BASE_URL + tv!!.posterPath)
             .into(binding.backgroundImm)
        }
    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }
}