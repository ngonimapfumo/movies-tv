package zw.co.nm.moviedb.ui.tv.episode

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityEpisodeBinding
import zw.co.nm.moviedb.ui.tv.TvShowsViewModel
import zw.co.nm.moviedb.utils.Constants

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
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL + it!!.body.stillPath)
                .placeholder(R.drawable.sample_episode)
                .into(binding.episodePoster)
            binding.episodeName.text = it.body.name
            binding.episodeOverView.text = it.body.overview
            binding.cardView.visibility = VISIBLE


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