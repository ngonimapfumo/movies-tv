package zw.co.nm.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.repositories.MoviesRepository


class MovieDetailViewModel(application: Application): AndroidViewModel(application) {

    private val getMovieDetailRepo = MoviesRepository()

    fun getMovieDetail(movieId: Int): Flow<Response<GetMovieDetailResponse>> {
        return getMovieDetailRepo.getMovieDetails(movieId)
    }
}