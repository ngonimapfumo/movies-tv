package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectionService {
    @GET("collection/{id}")
    suspend fun getCollectionDetail(
        @Path("id") collectionId: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCollectionDetailResponse>
}