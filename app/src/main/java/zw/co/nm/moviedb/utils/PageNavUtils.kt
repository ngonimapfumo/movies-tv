package zw.co.nm.moviedb.utils

import android.content.Context
import android.content.Intent
import zw.co.nm.moviedb.ui.movie.MovieActivity

object PageNavUtils {

    fun toMovieDetailsPage(context: Context, movieId: Int) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra(MovieActivity.MOVIE_ID_EXTRA, movieId)
        context.startActivity(intent)
    }
}