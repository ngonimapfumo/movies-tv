package zw.co.nm.moviedb.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetPopularMoviesListResponse
import zw.co.nm.moviedb.repositories.GetPopularMoviesListRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val getPopularMoviesListRepo = GetPopularMoviesListRepo()

    fun getPopularMovies(page: Int): Flow<GetPopularMoviesListResponse?> {
        return getPopularMoviesListRepo.getPopularMovies(page)
    }

}