package zw.co.nm.moviedb.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import zw.co.nm.moviedb.data.domain.models.Movie
import zw.co.nm.moviedb.data.remote.networkmodel.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse
import zw.co.nm.moviedb.repo.MoviesRepo


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepo = MoviesRepo(application)
    var page: Int = 1

    private val _searchMulti =
        MutableLiveData<zw.co.nm.moviedb.data.remote.Response<SearchMultiResponse>>()
    val searchMulti: LiveData<zw.co.nm.moviedb.data.remote.Response<SearchMultiResponse>> =
        _searchMulti

    private val _getPopularMovies =
        MutableLiveData<zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse>> =
        _getPopularMovies

    private val _getMovieDetail =
        MutableLiveData<zw.co.nm.moviedb.data.remote.Response<GetMovieDetailResponse>?>()
    val getMovieDetail: LiveData<zw.co.nm.moviedb.data.remote.Response<GetMovieDetailResponse>?> =
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
            _getSimilarMovies.postValue(response)
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepo.getCredits(movieId)
            _getMovieCredits.postValue(response)
        }
    }

    fun searchMulti(query: String) {
        viewModelScope.launch {
            val response = moviesRepo.searchMulti(query, page)
            if (response.isSuccessful) {
                _searchMulti.postValue(response)
            } else {
                Toast.makeText(getApplication(), "Mmmmm..network", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val response = moviesRepo.getPopularMovies(page)
            if (response.isSuccessful) {
                _getPopularMovies.postValue(response)
            } else {
                Toast.makeText(getApplication(), "Mmmmm..network", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepo.getMovieDetails(movieId)
            _getMovieDetail.postValue(response)

        }
    }

    fun resetPages() {
        page = 1
    }

}