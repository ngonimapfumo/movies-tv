package zw.co.nm.moviedb.presentation.auth

import zw.co.nm.moviedb.data.remote.model.request.AddWatchlistRequest
import zw.co.nm.moviedb.data.remote.model.request.CreateSessionRequest
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
}
