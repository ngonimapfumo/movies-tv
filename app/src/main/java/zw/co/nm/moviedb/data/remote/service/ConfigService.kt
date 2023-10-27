package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import zw.co.nm.moviedb.data.remote.model.response.GetTranslations

interface ConfigService {

    @GET("configuration/primary_translations")
    suspend fun getTranslations(): Response<GetTranslations>

}