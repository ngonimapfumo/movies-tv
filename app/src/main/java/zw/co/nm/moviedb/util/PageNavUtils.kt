package zw.co.nm.moviedb.util

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import zw.co.nm.moviedb.presentation.collection.CollectionActivity
import zw.co.nm.moviedb.presentation.movie.MovieDetailActivity
import zw.co.nm.moviedb.presentation.movie.PostersImagesActivity
import zw.co.nm.moviedb.presentation.person.PersonActivity
import zw.co.nm.moviedb.presentation.reviews.ReviewsActivity
import zw.co.nm.moviedb.presentation.trailers.TrailerActivity
import zw.co.nm.moviedb.presentation.tv.TvShowDetailActivity
import zw.co.nm.moviedb.presentation.tv.episode.EpisodeActivity
import zw.co.nm.moviedb.presentation.tv.season.SeasonActivity
import zw.co.nm.moviedb.util.Constants.TRAILER_TYPE

object PageNavUtils {

    fun navMovieDetailsPage(context: Context, movieId: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movieId)
        context.startActivity(intent)
    }

    fun navTvDetailsPage(context: Context, tvShowId: Int) {
        val intent = Intent(context, TvShowDetailActivity::class.java)
        intent.putExtra(TvShowDetailActivity.TV_SHOW_ID_EXTRA, tvShowId)
        context.startActivity(intent)
    }

    fun navPersonDetailsPage(context: Context, personId: Int) {
        val intent = Intent(context, PersonActivity::class.java)
        intent.putExtra(PersonActivity.PERSON_ID_EXTRA, personId)
        context.startActivity(
            intent,
            ActivityOptions.makeSceneTransitionAnimation(context as Activity?).toBundle()
        )
    }

    fun navTrailersPage(context: Context, trailerType: String, id: Int) {
        val intent = Intent(context, TrailerActivity::class.java)
        intent.putExtra(TrailerActivity.MOVIE_ID_EXTRA, id)
        intent.putExtra(TRAILER_TYPE, trailerType)
        context.startActivity(intent)
    }

    fun navCollectionPage(context: Context, id: Int) {
        val intent = Intent(context, CollectionActivity::class.java)
        intent.putExtra(CollectionActivity.COLLECTION_ID, id)
        context.startActivity(
            intent,
            ActivityOptions.makeSceneTransitionAnimation(context as Activity?).toBundle()
        )
    }

    fun navSeasonPage(context: Context?, id: Int) {
        val intent = Intent(context, SeasonActivity::class.java)
        intent.putExtra(SeasonActivity.SEASON_ID, id)
        context!!.startActivity(
            intent,
            ActivityOptions.makeSceneTransitionAnimation(context as Activity?).toBundle()
        )
    }

    fun navReviewsPage(context: Context?, reviewType: String, id: Int) {
        val intent = Intent(context, ReviewsActivity::class.java)
        intent.putExtra(ReviewsActivity.ID, id)
        intent.putExtra(Constants.REVIEW_TYPE, reviewType)
        context!!.startActivity(intent)
    }

    fun navEpisodePage(context: Context?, seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        val extrasBundle = Bundle()
        extrasBundle.putSerializable("seriesId", seriesId)
        extrasBundle.putSerializable("seasonNumber", seasonNumber)
        extrasBundle.putSerializable("episodeNumber", episodeNumber)
        val intent = Intent(context, EpisodeActivity::class.java)
        intent.putExtras(extrasBundle)
        context!!.startActivity(intent)
    }

    fun navMoviePostersPage(context: Context?, movieId: Int) {
        val intent = Intent(context, PostersImagesActivity::class.java)
        intent.putExtra(PostersImagesActivity.MOVIE_ID, movieId)
        context!!.startActivity(
            intent,
            ActivityOptions.makeSceneTransitionAnimation(context as Activity?).toBundle()
        )
    }

}