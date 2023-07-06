package zw.co.nm.moviedb.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityTvShowBinding
import zw.co.nm.moviedb.ui.viewmodels.TvShowsViewModel

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding
    private var showId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showId = intent.getIntExtra(TV_SHOW_ID_EXTRA, 0)
        val tvShowsViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowsViewModel.getShowDetails(showId!!)
        tvShowsViewModel.getShowDetails.observe(this) { tv ->
            //we continue from here

        }
    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }
}