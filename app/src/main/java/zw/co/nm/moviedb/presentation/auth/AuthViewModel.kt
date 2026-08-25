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

data class FavoriteMutation(
    val success: Boolean,
    val added: Boolean,
    val mediaType: String
)

data class RatingMutation(
    val success: Boolean,
    val rating: Double?,
    val mediaType: String,
    val deleted: Boolean = false
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepo()
    private var language = ConfigStore.getStringLang(application, LANGUAGE_KEY)

    var moviesPage: Int = 1
    var tvPage: Int = 1
    var favoriteMoviesPage: Int = 1
    var favoriteTvPage: Int = 1
    var ratedMoviesPage: Int = 1
    var ratedTvPage: Int = 1

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

    private val _favoriteMutation = MutableLiveData<FavoriteMutation>()
    val favoriteMutation: LiveData<FavoriteMutation> = _favoriteMutation

    private val _ratingMutation = MutableLiveData<RatingMutation>()
    val ratingMutation: LiveData<RatingMutation> = _ratingMutation

    private val _isOnWatchlist = MutableLiveData(false)
    val isOnWatchlist: LiveData<Boolean> = _isOnWatchlist

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _userRating = MutableLiveData<Double?>()
    val userRating: LiveData<Double?> = _userRating

    private val _watchlistMovies = MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val watchlistMovies: LiveData<Response<GetPopularMoviesListResponse>> = _watchlistMovies

    private val _watchlistTv = MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val watchlistTv: LiveData<Response<GetPopularTVSeriesListResponse>> = _watchlistTv

    private val _favoriteMovies = MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val favoriteMovies: LiveData<Response<GetPopularMoviesListResponse>> = _favoriteMovies

    private val _favoriteTv = MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val favoriteTv: LiveData<Response<GetPopularTVSeriesListResponse>> = _favoriteTv

    private val _ratedMovies = MutableLiveData<Response<GetPopularMoviesListResponse>>()
    val ratedMovies: LiveData<Response<GetPopularMoviesListResponse>> = _ratedMovies

    private val _ratedTv = MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val ratedTv: LiveData<Response<GetPopularTVSeriesListResponse>> = _ratedTv

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
            val displayName = accountResponse.body.username.takeIf { it.isNotBlank() }
                ?: accountResponse.body.name
            if (!displayName.isNullOrBlank()) {
                ConfigStore.saveStringConfig(
                    getApplication(),
                    Constants.ACCOUNT_USERNAME,
                    displayName
                )
            }
            ConfigStore.clearConfig(getApplication(), REQ_TOKEN)
            _loading.value = false
            _loginResult.value = true
        }
    }

    fun checkAccountStates(mediaId: Int, mediaType: String = Constants.MEDIA_TYPE_MOVIE) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = when (mediaType) {
                Constants.MEDIA_TYPE_TV -> authRepo.getTvAccountStates(mediaId, sessionId)
                else -> authRepo.getMovieAccountStates(mediaId, sessionId)
            }
            if (response.isSuccessful) {
                _isOnWatchlist.value = response.body.watchlist
                _isFavorite.value = response.body.favorite
                _userRating.value = response.body.rated
            }
        }
    }

    /** @deprecated Prefer [checkAccountStates]. Kept for existing call sites. */
    fun checkWatchlistState(mediaId: Int, mediaType: String = Constants.MEDIA_TYPE_MOVIE) {
        checkAccountStates(mediaId, mediaType)
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

    fun setFavorite(
        mediaId: Int,
        addToFavorites: Boolean,
        mediaType: String = Constants.MEDIA_TYPE_MOVIE
    ) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            val response = authRepo.setFavorite(
                accountId = accountId,
                sessionId = sessionId,
                mediaId = mediaId,
                mediaType = mediaType,
                favorite = addToFavorites
            )
            val success = response.isSuccessful && response.body.success
            if (success) {
                _isFavorite.value = addToFavorites
            }
            _favoriteMutation.value = FavoriteMutation(
                success = success,
                added = addToFavorites,
                mediaType = mediaType
            )
        }
    }

    fun rateMedia(
        mediaId: Int,
        value: Double,
        mediaType: String = Constants.MEDIA_TYPE_MOVIE
    ) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = when (mediaType) {
                Constants.MEDIA_TYPE_TV -> authRepo.rateTvShow(mediaId, sessionId, value)
                else -> authRepo.rateMovie(mediaId, sessionId, value)
            }
            val success = response.isSuccessful && response.body.success
            if (success) {
                _userRating.value = value
            }
            _ratingMutation.value = RatingMutation(
                success = success,
                rating = value,
                mediaType = mediaType,
                deleted = false
            )
        }
    }

    fun deleteRating(
        mediaId: Int,
        mediaType: String = Constants.MEDIA_TYPE_MOVIE
    ) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = when (mediaType) {
                Constants.MEDIA_TYPE_TV -> authRepo.deleteTvRating(mediaId, sessionId)
                else -> authRepo.deleteMovieRating(mediaId, sessionId)
            }
            val success = response.isSuccessful && response.body.success
            if (success) {
                _userRating.value = null
            }
            _ratingMutation.value = RatingMutation(
                success = success,
                rating = null,
                mediaType = mediaType,
                deleted = true
            )
        }
    }

    fun addMovieToWatchlist(movieId: Int) =
        setWatchlist(movieId, true, Constants.MEDIA_TYPE_MOVIE)

    fun removeMovieFromWatchlist(movieId: Int) =
        setWatchlist(movieId, false, Constants.MEDIA_TYPE_MOVIE)

    fun removeTvFromWatchlist(tvId: Int) =
        setWatchlist(tvId, false, Constants.MEDIA_TYPE_TV)

    fun removeMovieFromFavorites(movieId: Int) =
        setFavorite(movieId, false, Constants.MEDIA_TYPE_MOVIE)

    fun removeTvFromFavorites(tvId: Int) =
        setFavorite(tvId, false, Constants.MEDIA_TYPE_TV)

    fun removeMovieRating(movieId: Int) =
        deleteRating(movieId, Constants.MEDIA_TYPE_MOVIE)

    fun removeTvRating(tvId: Int) =
        deleteRating(tvId, Constants.MEDIA_TYPE_TV)

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

    fun getFavoriteMovies() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _favoriteMovies.value = authRepo.getFavoriteMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = favoriteMoviesPage
            )
        }
    }

    fun getFavoriteTv() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _favoriteTv.value = authRepo.getFavoriteTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = favoriteTvPage
            )
        }
    }

    fun getRatedMovies() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _ratedMovies.value = authRepo.getRatedMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = ratedMoviesPage
            )
        }
    }

    fun getRatedTv() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                return@launch
            }
            _ratedTv.value = authRepo.getRatedTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language ?: "en-US",
                page = ratedTvPage
            )
        }
    }
}
