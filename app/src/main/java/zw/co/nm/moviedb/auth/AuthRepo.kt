package zw.co.nm.moviedb.auth

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class AuthRepo {

    suspend fun getAuthToken(): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetRequestTokenResponse> =
        apiCall { NetworkManager.authService.getAuthToken() }
}