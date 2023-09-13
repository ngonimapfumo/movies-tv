package zw.co.nm.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse
import zw.co.nm.moviedb.utils.GeneralUtil.displayGenToast


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepo = MoviesRepo(application)
    var page: Int = 1

    private val _searchMulti =
        MutableLiveData<Response<SearchMultiResponse>>()
    val searchMulti: LiveData<Response<SearchMultiResponse>> =
        _searchMulti

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

    fun getSimilarMoviesList(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepo.getSimilarMoviesList(movieId)
            if (response.isSuccessful) {
                _getSimilarMovies.postValue(response)
            } else {
                displayGenToast(
                    getApplication(),
                    "An error occurred whilst getting data"
                )
            }
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepo.getCredits(movieId)
            if (response.isSuccessful) {
                _getMovieCredits.postValue(response)
            } else {
                displayGenToast(
                    getApplication(),
                    "An error occurred whilst getting data"
                )
            }
        }
    }

    fun searchMulti(query: String) {
        viewModelScope.launch {
            val response = moviesRepo.searchMulti(query, page)
            if (response.isSuccessful) {
                _searchMulti.postValue(response)
            } else {
                displayGenToast(
                    getApplication(),
                    "An error occurred whilst getting data"
                )
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val response = moviesRepo.getPopularMovies(page)
            _getPopularMovies.value = response

        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepo.getMovieDetails(movieId)
            if (response.isSuccessful) {
                _getMovieDetail.postValue(response)
            } else {
                displayGenToast(
                    getApplication(),
                    "An error occurred whilst getting data"
                )
            }
        }
    }

    fun resetPages() {
        page = 1
    }

}