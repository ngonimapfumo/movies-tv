package zw.co.nm.moviedb.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.nm.moviedb.R

class PostersImagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posters_images)
    }

    companion object {
        const val MOVIE_ID: String = "movieId"
    }
}