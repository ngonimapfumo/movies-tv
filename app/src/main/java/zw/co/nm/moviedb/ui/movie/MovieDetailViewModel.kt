package zw.co.nm.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.repositories.GetMovieDetailRepo

class MovieDetailViewModel(application: Application): AndroidViewModel(application) {

    private val getMovieDetailRepo = GetMovieDetailRepo()

    fun getMovieDetail(movieId: Int): Flow<GetMovieDetailResponse?> {
        return getMovieDetailRepo.getMovieDetails(movieId)
    }
}