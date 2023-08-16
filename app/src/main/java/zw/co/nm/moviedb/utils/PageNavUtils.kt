package zw.co.nm.moviedb.utils

import android.content.Context
import android.content.Intent
import zw.co.nm.moviedb.ui.movie.MovieDetailActivity
import zw.co.nm.moviedb.ui.person.PersonActivity
import zw.co.nm.moviedb.ui.trailers.TrailerActivity
import zw.co.nm.moviedb.ui.tv.TvShowDetailActivity

object PageNavUtils {

    fun toMovieDetailsPage(context: Context, movieId: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movieId)
        context.startActivity(intent)
    }
    fun toTvDetailsPage(context: Context, tvShowId: Int) {
        val intent = Intent(context, TvShowDetailActivity::class.java)
        intent.putExtra(TvShowDetailActivity.TV_SHOW_ID_EXTRA, tvShowId)
        context.startActivity(intent)
    }

    fun toPersonDetailsPage(context: Context, personId: Int) {
        val intent = Intent(context, PersonActivity::class.java)
        intent.putExtra(PersonActivity.PERSON_ID_EXTRA, personId)
        context.startActivity(intent)
    }

    fun toTrailersPage(context: Context, movieId:Int) {
        val intent = Intent(context, TrailerActivity::class.java)
        intent.putExtra(TrailerActivity.MOVIE_ID_EXTRA, movieId)
        context.startActivity(intent)
    }

}