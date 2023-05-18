package zw.co.nm.moviedb.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.repositories.MoviesRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository = MoviesRepository()

    fun getPopularMovies(page: Int): Flow<Response<GetPopularMoviesListResponse>> {
        return moviesRepository.getPopularMovies(page)
    }
}