package zw.co.nm.moviedb.ui.movie

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetCreditsResponse
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.models.network.GetSimilarMoviesResponse
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.repositories.MoviesRepository


class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val getMovieDetailRepo = MoviesRepository()

    fun getMovieDetail(movieId: Int): Flow<Response<GetMovieDetailResponse>> {
        return getMovieDetailRepo.getMovieDetails(movieId)
    }

    fun getSimilarMoviesList(movieId: Int): Flow<Response<GetSimilarMoviesResponse>> {
        return getMovieDetailRepo.getSimilarMoviesList(movieId)
    }
    fun getCredits(movieId: Int):Flow<Response<GetCreditsResponse>>{
        return getMovieDetailRepo.getCredits(movieId)
    }


}