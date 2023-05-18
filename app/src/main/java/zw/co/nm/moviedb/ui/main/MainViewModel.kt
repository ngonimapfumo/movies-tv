package zw.co.nm.moviedb.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.repositories.GetPopularMoviesRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val getPopularMoviesRepo = GetPopularMoviesRepo()

    fun getPopularMovies(page: Int): Flow<GetPopularMoviesListResponse?> {
        return getPopularMoviesRepo.getPopularMovies(page)
    }
}