package zw.co.nm.moviedb.ui.tv.episode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityEpisodeBinding
import zw.co.nm.moviedb.ui.tv.TvShowsViewModel

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
        tvViewModel.getEpisodeDetail(seriesId!!,seasonNumber!!,episodeNumber!!)
        tvViewModel.getEpisodeDetail.observe(this){


            //todo: continue here

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