package zw.co.nm.moviedb.ui.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityTvShowsBinding

class TvShowsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTvShowsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, intent.getIntExtra(TV_SHOW_ID_EXTRA,0).toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }
}