package zw.co.nm.moviedb.presentation.auth

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.ACCOUNT_ID
import zw.co.nm.moviedb.util.Constants.AUTH_REDIRECT_URI
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY
import zw.co.nm.moviedb.util.Constants.REQ_TOKEN
import zw.co.nm.moviedb.util.Constants.SESSION_ID
import zw.co.nm.moviedb.util.Constants.TMDB_AUTH_BASE_URL

data class WatchlistMutation(
    val success: Boolean,
    val added: Boolean,
    val mediaType: String
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepo()
    private var language = ConfigStore.getStringLang(application, LANGUAGE_KEY)

    var moviesPage: Int = 1
    var tvPage: Int = 1

    private val _authUrl = MutableLiveData<String?>()
    val authUrl: LiveData<String?> = _authUrl

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> = _loginError

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _watchlistMutation = MutableLiveData<WatchlistMutation>()
    val watchlistMutation: LiveData<WatchlistMutation> = _watchlistMutation

    private val _isOnWatchlist = MutableLiveData(false)
    val isOnWatchlist: LiveData<Boolean> = _isOnWatchlist

    private val _watchlistMovies = MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val watchlistMovies: LiveData<Response<GetPopularMoviesListResponse>> = _watchlistMovies

    private val _watchlistTv = MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val watchlistTv: LiveData<Response<GetPopularTVSeriesListResponse>> = _watchlistTv

    fun startTmdbLogin() {
        viewModelScope.launch {
            _loading.value = true
            _loginError.value = null
            _authUrl.value = null

            val tokenResponse = authRepo.createAuthToken()
            if (!tokenResponse.isSuccessful) {
                _loading.value = false
                _loginError.value = "Could not start TMDB login. Please try again."
                return@launch
            }

            val requestToken = tokenResponse.body.requestToken
            ConfigStore.saveStringConfig(getApplication(), REQ_TOKEN, requestToken)

            val redirect = Uri.encode(AUTH_REDIRECT_URI)
            _authUrl.value = "$TMDB_AUTH_BASE_URL$requestToken?redirect_to=$redirect"
            _loading.value = false
        }
    }

    fun clearAuthUrl() {
        _authUrl.value = null
    }

    fun completeLogin(requestToken: String) {
        viewModelScope.launch {
            _loading.value = true
            _loginError.value = null

            val sessionResponse = authRepo.createSession(requestToken)
            if (!sessionResponse.isSuccessful || !sessionResponse.body.success) {
                _loading.value = false
                _loginError.value = "Could not create session. Please approve access and try again."
                return@launch
            }

            val sessionId = sessionResponse.body.sessionId
            val accountResponse = authRepo.getAccount(sessionId)
            if (!accountResponse.isSuccessful) {
                _loading.value = false
                _loginError.value = "Could not load account. Please try again."
                return@launch
            }

            ConfigStore.saveStringConfig(getApplication(), SESSION_ID, sessionId)
            ConfigStore.saveIntConfig(getApplication(), ACCOUNT_ID, accountResponse.body.id)
            ConfigStore.clearConfig(getApplication(), REQ_TOKEN)
            _loading.value = false
            _loginResult.value = true
        }
    }

    fun checkWatchlistState(mediaId: Int, mediaType: String = Constants.MEDIA_TYPE_MOVIE) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = when (mediaType) {
                Constants.MEDIA_TYPE_TV -> authRepo.getTvAccountStates(mediaId, sessionId)
                else -> authRepo.getMovieAccountStates(mediaId, sessionId)
            }
            if (response.isSuccessful) {
                _isOnWatchlist.value = response.body.watchlist
            }
        }
    }

    fun setWatchlist(
        mediaId: Int,
        addToWatchlist: Boolean,
        mediaType: String = Constants.MEDIA_TYPE_MOVIE
    ) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            val response = authRepo.setWatchlist(
                accountId = accountId,
                sessionId = sessionId,
                mediaId = mediaId,
                mediaType = mediaType,
                watchlist = addToWatchlist
            )
            val success = response.isSuccessful && response.body.success
            if (success) {
                _isOnWatchlist.value = addToWatchlist
            }
            _watchlistMutation.value = WatchlistMutation(
                success = success,
                added = addToWatchlist,
                mediaType = mediaType
            )
        }
    }

    fun addMovieToWatchlist(movieId: Int) =
        setWatchlist(movieId, true, Constants.MEDIA_TYPE_MOVIE)

    fun removeMovieFromWatchlist(movieId: Int) =
        setWatchlist(movieId, false, Constants.MEDIA_TYPE_MOVIE)

    fun removeTvFromWatchlist(tvId: Int) =
        setWatchlist(tvId, false, Constants.MEDIA_TYPE_TV)

    fun getWatchlistMovies() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _watchlistMovies.value = authRepo.getWatchlistMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = moviesPage
            )
        }
    }

    fun getWatchlistTv() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _watchlistTv.value = authRepo.getWatchlistTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = tvPage
            )
        }
    }
}
