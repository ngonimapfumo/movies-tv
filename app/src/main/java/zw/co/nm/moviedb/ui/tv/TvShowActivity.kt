package zw.co.nm.moviedb.ui.tv

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.databinding.ActivityTvShowBinding

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, intent.getIntExtra(TV_SHOW_ID_EXTRA, 0).toString(), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val TV_SHOW_ID_EXTRA: String = "tvShowId"
    }
}