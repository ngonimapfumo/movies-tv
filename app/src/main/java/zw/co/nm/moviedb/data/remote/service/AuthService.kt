package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import zw.co.nm.moviedb.data.remote.model.request.SessionLoginRequest
import zw.co.nm.moviedb.data.remote.model.response.CreateRequestTokenResponse
import zw.co.nm.moviedb.data.remote.model.response.CreateSessionResponse

interface AuthService {

    @GET("authentication/token/new")
    suspend fun getAuthToken(): Response<CreateRequestTokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun createSession(@Body body: SessionLoginRequest): Response<CreateSessionResponse>
}