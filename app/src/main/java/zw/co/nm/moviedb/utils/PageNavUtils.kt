package zw.co.nm.moviedb.utils

import android.content.Context
import android.content.Intent
import zw.co.nm.moviedb.ui.movie.MovieDetailActivity

object PageNavUtils {

    fun toMovieDetailsPage(context: Context, movieId: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movieId)
        context.startActivity(intent)
    }
}