package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.api.Response
import zw.co.nm.moviedb.model.GetCreditsResponse
import zw.co.nm.moviedb.model.GetMovieDetailResponse
import zw.co.nm.moviedb.model.GetPopularMoviesListResponse
import zw.co.nm.moviedb.model.GetSimilarMoviesResponse
import zw.co.nm.moviedb.model.SearchMovieResponse
import zw.co.nm.moviedb.model.SearchMultiResponse
import zw.co.nm.moviedb.repo.MoviesRepository


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MoviesRepository()
    var page: Int = 1

    private val _searchMulti: MutableLiveData<retrofit2.Response<SearchMultiResponse>> =
        MutableLiveData<retrofit2.Response<SearchMultiResponse>>()
    val searchMulti: LiveData<retrofit2.Response<SearchMultiResponse>> =
        _searchMulti

    private val _getPopularMovies: MutableLiveData<retrofit2.Response<GetPopularMoviesListResponse>> =
        MutableLiveData<retrofit2.Response<GetPopularMoviesListResponse>>()
    val getPopularMovies: LiveData<retrofit2.Response<GetPopularMoviesListResponse>> =
        _getPopularMovies


    fun getMovieDetail(movieId: Int): Flow<Response<GetMovieDetailResponse>> {
        return moviesRepository.getMovieDetails(movieId)
    }

    fun getSimilarMoviesList(movieId: Int): Flow<Response<GetSimilarMoviesResponse>> {
        return moviesRepository.getSimilarMoviesList(movieId)
    }

    fun getCredits(movieId: Int): Flow<Response<GetCreditsResponse>> {
        return moviesRepository.getCredits(movieId)
    }

    fun searchMovie(query: String): Flow<Response<SearchMovieResponse>> {
        return moviesRepository.searchMovie(query)
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
            _getPopularMovies.postValue(response)
        }
    }


}