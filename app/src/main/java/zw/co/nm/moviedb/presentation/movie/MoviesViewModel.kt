package zw.co.nm.moviedb.presentation.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepo = MoviesRepo(application)
    var page: Int = 1

    private val _getPopularMovies =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse>> =
        _getPopularMovies

    private val _getMovieDetail =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse>?>()
    val getMovieDetail: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse>?> =
        _getMovieDetail

    private val _getMovieCredits =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse>>()
    val getMovieCredits: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse>> =
        _getMovieCredits

    private val _getSimilarMovies =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse>>()
    val getSimilarMovies: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse>> =
        _getSimilarMovies

    private val _getMovieReleaseDates =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse>>()
    val getMovieReleaseDates: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse>> =
        _getMovieReleaseDates

    fun getSimilarMoviesList(movieId: Int) {
        viewModelScope.launch {
            _getSimilarMovies.value = moviesRepo.getSimilarMoviesList(movieId)
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            _getMovieCredits.value = moviesRepo.getCredits(movieId)
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            _getPopularMovies.value = moviesRepo.getPopularMovies(page)
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _getMovieDetail.value = moviesRepo.getMovieDetails(movieId)
        }
    }

    fun getMovieReleaseDates(movieId: Int) {
        viewModelScope.launch {
            _getMovieReleaseDates.value = moviesRepo.getMovieReleaseDates(movieId)
        }
    }


}