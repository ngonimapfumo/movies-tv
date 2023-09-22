package zw.co.nm.moviedb.presentation.movie

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse


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

    private val _getMovieReleaseDates =
        MutableLiveData<Response<GetReleaseDatesResponse>>()
    val getMovieReleaseDates: LiveData<Response<GetReleaseDatesResponse>> =
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

    fun searchMulti(query: String) {
        viewModelScope.launch {
            val data = moviesRepo.searchMulti(query, page)
            if (data.isSuccessful) {
                _searchMulti.value = moviesRepo.searchMulti(query, page)
            }else{
                Toast.makeText(getApplication(), "Something went wrong, please try again later", Toast.LENGTH_SHORT).show()
            }
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


    fun resetPages() {
        page = 1
    }

}