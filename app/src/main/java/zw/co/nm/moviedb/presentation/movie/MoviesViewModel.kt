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
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetPopularMoviesListResponse>> =
        _getPopularMovies

    private val _getMovieDetail =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetMovieDetailResponse>?>()
    val getMovieDetail: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetMovieDetailResponse>?> =
        _getMovieDetail

    private val _getMovieCredits =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCreditsResponse>>()
    val getMovieCredits: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCreditsResponse>> =
        _getMovieCredits

    private val _getSimilarMovies =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetSimilarMoviesResponse>>()
    val getSimilarMovies: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetSimilarMoviesResponse>> =
        _getSimilarMovies

    private val _getMovieReleaseDates =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReleaseDatesResponse>>()
    val getMovieReleaseDates: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReleaseDatesResponse>> =
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