package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {
    @GET("collection/{id}")
    suspend fun getCollectionDetail(
        @Path("id") collectionId: Int,
        @Query("language") language: String
    ): Response<zw.co.nm.moviedb.data.remote.model.response.GetCollectionDetailResponse>
}