package zw.co.nm.moviedb.data.remote.service

import retrofit2.http.GET

interface CollectionService {

    @GET("collection/{id}")
    suspend fun getCollectionDetail()
}