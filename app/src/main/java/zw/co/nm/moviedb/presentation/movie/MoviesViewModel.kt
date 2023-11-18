package zw.co.nm.moviedb.presentation.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.util.Response


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepo = MoviesRepo()
    private var language = "en-US"

    var page: Int = 1

    private val _getPopularMovies =
        MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<Response<GetPopularMoviesListResponse>> =
        _getPopularMovies

    private val _getMovieDetail =
        MutableLiveData<Response<GetMovieDetailResponse>?>()
    val getMovieDetail: LiveData<Response<GetMovieDetailResponse>?> =
        _getMovieDetail

    private val _getMovieCredits =
        MutableLiveData<Response<GetCreditsResponse>>()
    val getMovieCredits: LiveData<Response<GetCreditsResponse>> =
        _getMovieCredits

    private val _getSimilarMovies =
        MutableLiveData<Response<GetSimilarMoviesResponse>>()
    val getSimilarMovies: LiveData<Response<GetSimilarMoviesResponse>> =
        _getSimilarMovies

    private val _getMovieReleaseDates =
        MutableLiveData<Response<GetReleaseDatesResponse>>()
    val getMovieReleaseDates: LiveData<Response<GetReleaseDatesResponse>> =
        _getMovieReleaseDates

    fun getSimilarMoviesList(movieId: Int) {
        viewModelScope.launch {
            _getSimilarMovies.value = moviesRepo.getSimilarMoviesList(movieId, language!!)
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            _getMovieCredits.value = moviesRepo.getCredits(movieId, language!!)
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            _getPopularMovies.value = moviesRepo.getPopularMovies(page, language!!)
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _getMovieDetail.value = moviesRepo.getMovieDetails(movieId, language!!)
        }
    }

    fun getMovieReleaseDates(movieId: Int) {
        viewModelScope.launch {
            _getMovieReleaseDates.value = moviesRepo.getMovieReleaseDates(movieId, language!!)
        }
    }


}