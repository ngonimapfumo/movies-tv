package zw.co.nm.moviedb.presentation.auth

import zw.co.nm.moviedb.data.remote.model.request.SessionLoginRequest
import zw.co.nm.moviedb.data.remote.model.response.CreateSessionResponse
import zw.co.nm.moviedb.data.remote.model.response.GetRequestTokenResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class AuthRepo {

    suspend fun getAuthToken(): Response<GetRequestTokenResponse> =
        apiCall { NetworkManager.authService.getAuthToken() }

    suspend fun createSession(
        password: String,
        requestToken: String,
        username: String
    ): Response<CreateSessionResponse> =
        apiCall {
            NetworkManager.authService.createSession(
                SessionLoginRequest(
                    password = password, requestToken = requestToken, username = username
                )
            )
        }
}