package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import zw.co.nm.moviedb.data.remote.networkmodel.GetCollectionDetailResponse

interface CollectionService {
    @GET("collection/{id}")
    suspend fun getCollectionDetail(
        @Path("id") collectionId: Int
    ): Response<GetCollectionDetailResponse>
}