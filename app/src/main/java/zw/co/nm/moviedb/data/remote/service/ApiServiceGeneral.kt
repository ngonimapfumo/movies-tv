package zw.co.nm.moviedb.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPersonImagesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse
import zw.co.nm.moviedb.data.remote.util.Response

interface ApiServiceGeneral {
    @GET("person/{id}/images")
    suspend fun getPersonImages(
        @Path("id") personId: Int)
    : Response<GetPersonImagesResponse>

    @GET("person/{id}/combined_credits")
    suspend fun getCombinedCredits(
        @Path("id") query: Int,
        @Query("language") language: String
    ): retrofit2.Response<GetCombinedCreditsResponse>

    @GET("person/{id}")
    suspend fun getPerson(
        @Path("id") personId: Int,
        @Query("language") language: String
    ): retrofit2.Response<GetPersonResponse>

}