package zw.co.nm.moviedb.presentation.auth

import zw.co.nm.moviedb.data.remote.model.request.AddFavoriteRequest
import zw.co.nm.moviedb.data.remote.model.request.AddWatchlistRequest
import zw.co.nm.moviedb.data.remote.model.request.CreateSessionRequest
import zw.co.nm.moviedb.data.remote.model.request.RateMediaRequest
import zw.co.nm.moviedb.data.remote.model.response.AccountStatesResponse
import zw.co.nm.moviedb.data.remote.model.response.CreateRequestTokenResponse
import zw.co.nm.moviedb.data.remote.model.response.CreateSessionIdResponse
import zw.co.nm.moviedb.data.remote.model.response.GetAccountResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class AuthRepo {

    suspend fun createAuthToken(): Response<CreateRequestTokenResponse> =
        apiCall { NetworkManager.authService.getAuthToken() }

    suspend fun createSession(requestToken: String): Response<CreateSessionIdResponse> =
        apiCall {
            NetworkManager.authService.createSession(
                CreateSessionRequest(requestToken = requestToken)
            )
        }

    suspend fun getAccount(sessionId: String): Response<GetAccountResponse> =
        apiCall { NetworkManager.accountService.getAccount(sessionId) }

    suspend fun getMovieAccountStates(
        movieId: Int,
        sessionId: String
    ): Response<AccountStatesResponse> =
        apiCall {
            NetworkManager.movieService.getMovieAccountStates(movieId, sessionId)
        }

    suspend fun getTvAccountStates(
        seriesId: Int,
        sessionId: String
    ): Response<AccountStatesResponse> =
        apiCall {
            NetworkManager.tvShowService.getTvAccountStates(seriesId, sessionId)
        }

    suspend fun getWatchlistMovies(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularMoviesListResponse> =
        apiCall {
            NetworkManager.accountService.getWatchlistMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun getWatchlistTv(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularTVSeriesListResponse> =
        apiCall {
            NetworkManager.accountService.getWatchlistTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun setWatchlist(
        accountId: Int,
        sessionId: String,
        mediaId: Int,
        mediaType: String,
        watchlist: Boolean
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.accountService.addToWatchlist(
                accountId = accountId,
                sessionId = sessionId,
                body = AddWatchlistRequest(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    watchlist = watchlist
                )
            )
        }

    suspend fun getFavoriteMovies(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularMoviesListResponse> =
        apiCall {
            NetworkManager.accountService.getFavoriteMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun getFavoriteTv(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularTVSeriesListResponse> =
        apiCall {
            NetworkManager.accountService.getFavoriteTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun setFavorite(
        accountId: Int,
        sessionId: String,
        mediaId: Int,
        mediaType: String,
        favorite: Boolean
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.accountService.addToFavorites(
                accountId = accountId,
                sessionId = sessionId,
                body = AddFavoriteRequest(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    favorite = favorite
                )
            )
        }

    suspend fun getRatedMovies(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularMoviesListResponse> =
        apiCall {
            NetworkManager.accountService.getRatedMovies(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun getRatedTv(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetPopularTVSeriesListResponse> =
        apiCall {
            NetworkManager.accountService.getRatedTv(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun rateMovie(
        movieId: Int,
        sessionId: String,
        value: Double
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.movieService.rateMovie(
                movieId = movieId,
                sessionId = sessionId,
                body = RateMediaRequest(value)
            )
        }

    suspend fun rateTvShow(
        seriesId: Int,
        sessionId: String,
        value: Double
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.tvShowService.rateTvShow(
                seriesId = seriesId,
                sessionId = sessionId,
                body = RateMediaRequest(value)
            )
        }

    suspend fun deleteMovieRating(
        movieId: Int,
        sessionId: String
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.movieService.deleteMovieRating(movieId, sessionId)
        }

    suspend fun deleteTvRating(
        seriesId: Int,
        sessionId: String
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.tvShowService.deleteTvRating(seriesId, sessionId)
        }
}
