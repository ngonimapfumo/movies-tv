package zw.co.nm.moviedb.presentation.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetMovieKeywordsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetReleaseDatesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTrendingResponse
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepo = MoviesRepo()
    private var language = ConfigStore.getStringLang(application, LANGUAGE_KEY)

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

    private val _getMovieImages = MutableLiveData<Response<GetMovieImagesResponse>>()
    val getMovieImages: LiveData<Response<GetMovieImagesResponse>> = _getMovieImages

    private val _getMovieGenres = MutableLiveData<Response<GetMovieGenres>>()
    val getMovieGenres: LiveData<Response<GetMovieGenres>> = _getMovieGenres

    private val _getMovieByGenreId =
        MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val getMovieByGenreId: LiveData<Response<GetPopularMoviesListResponse>> =
        _getMovieByGenreId

    private val _getMoviesByKeywordId =
        MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val getMoviesByKeywordId: LiveData<Response<GetPopularMoviesListResponse>> =
        _getMoviesByKeywordId

    private val _getTrending =
        MutableLiveData<Response<GetTrendingResponse>>()
    val getTrending: LiveData<Response<GetTrendingResponse>> = _getTrending

    private val _getNowPlaying =
        MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val getNowPlaying: LiveData<Response<GetPopularMoviesListResponse>> = _getNowPlaying

    private val _getUpcoming =
        MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val getUpcoming: LiveData<Response<GetPopularMoviesListResponse>> = _getUpcoming

    private val _getMovieKeywords =
        MutableLiveData<Response<GetMovieKeywordsResponse>>()
    val getMovieKeywords: LiveData<Response<GetMovieKeywordsResponse>> = _getMovieKeywords

    private val _getWatchProviders =
        MutableLiveData<Response<GetWatchProvidersResponse>>()
    val getWatchProviders: LiveData<Response<GetWatchProvidersResponse>> =
        _getWatchProviders

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

    fun getMovieImages(movieId: Int) {
        viewModelScope.launch {
            _getMovieImages.value = moviesRepo.getMovieImages(movieId)
        }
    }

    fun getMovieGenres() {
        viewModelScope.launch {
            _getMovieGenres.value = moviesRepo.getMovieGenres(language!!)
        }
    }

    fun getMoviesByGenreId(genreId: Int) {
        viewModelScope.launch {
            _getMovieByGenreId.value = moviesRepo.getMovieByGenreId(page, language!!, genreId)
        }
    }

    fun getMoviesByKeywordId(keywordId: Int) {
        viewModelScope.launch {
            _getMoviesByKeywordId.value =
                moviesRepo.getMoviesByKeywordId(page, language!!, keywordId)
        }
    }

    fun getTrending() {
        viewModelScope.launch {
            _getTrending.value = moviesRepo.getTrending(page, language!!)
        }
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            _getNowPlaying.value = moviesRepo.getNowPlaying(page, language!!)
        }
    }

    fun getUpcoming() {
        viewModelScope.launch {
            _getUpcoming.value = moviesRepo.getUpcoming(page, language!!)
        }
    }

    fun getMovieKeywords(movieId: Int) {
        viewModelScope.launch {
            _getMovieKeywords.value = moviesRepo.getMovieKeywords(movieId)
        }
    }

    fun getWatchProviders(movieId: Int) {
        viewModelScope.launch {
            _getWatchProviders.value = moviesRepo.getWatchProviders(movieId)
        }
    }

}
