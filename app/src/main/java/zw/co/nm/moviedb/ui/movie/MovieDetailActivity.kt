package zw.co.nm.moviedb.ui.movie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, intent.getIntExtra(MOVIE_ID_EXTRA,0).toString(), Toast.LENGTH_SHORT).show()

    }

    companion object {
        const val MOVIE_ID_EXTRA: String = "movieId"
    }
}