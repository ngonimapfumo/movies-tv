package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET

interface AuthService {

    @GET("authentication/token/new")
    suspend fun getAuthToken(): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetRequestTokenResponse>
}