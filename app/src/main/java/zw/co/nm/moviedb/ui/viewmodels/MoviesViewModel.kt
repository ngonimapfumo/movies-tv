package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import zw.co.nm.moviedb.model.GetCreditsResponse
import zw.co.nm.moviedb.model.GetMovieDetailResponse
import zw.co.nm.moviedb.model.GetPopularMoviesListResponse
import zw.co.nm.moviedb.model.GetSimilarMoviesResponse
import zw.co.nm.moviedb.model.SearchMultiResponse
import zw.co.nm.moviedb.repo.MoviesRepository


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MoviesRepository()
    var page: Int = 1

    private val _searchMulti =
        MutableLiveData<Response<SearchMultiResponse>>()
    val searchMulti: LiveData<Response<SearchMultiResponse>> =
        _searchMulti

    private val _getPopularMovies =
        MutableLiveData<zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<zw.co.nm.moviedb.data.remote.Response<GetPopularMoviesListResponse>> =
        _getPopularMovies

    private val _getMovieDetail =
        MutableLiveData<Response<GetMovieDetailResponse>>()
    val getMovieDetail: LiveData<Response<GetMovieDetailResponse>> =
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
            val response = moviesRepository.getSimilarMoviesList(movieId)
            _getSimilarMovies.postValue(response)
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepository.getCredits(movieId)
            _getMovieCredits.postValue(response)
        }
    }

    fun searchMulti(query: String) {
        viewModelScope.launch {
            val response = moviesRepository.searchMulti(query)
            _searchMulti.postValue(response)
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val response = moviesRepository.getPopularMovies(page)
            if (response.isSuccessful){
                _getPopularMovies.postValue(response)
            }
            else{
//todo: handle this better
                Toast.makeText(getApplication(), "network", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepository.getMovieDetails(movieId)
            _getMovieDetail.postValue(response)
        }
    }


}