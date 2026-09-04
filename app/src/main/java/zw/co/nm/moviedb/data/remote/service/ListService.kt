package zw.co.nm.moviedb.data.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import zw.co.nm.moviedb.data.remote.model.request.CreateListRequest
import zw.co.nm.moviedb.data.remote.model.request.ListItemRequest
import zw.co.nm.moviedb.data.remote.model.response.CreateListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetListDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse

interface ListService {

    @POST("list")
    suspend fun createList(
        @Query("session_id") sessionId: String,
        @Body body: CreateListRequest
    ): Response<CreateListResponse>

    @GET("list/{list_id}")
    suspend fun getListDetail(
        @Path("list_id") listId: Int,
        @Query("language") language: String
    ): Response<GetListDetailResponse>

    @POST("list/{list_id}/add_item")
    suspend fun addItem(
        @Path("list_id") listId: Int,
        @Query("session_id") sessionId: String,
        @Body body: ListItemRequest
    ): Response<StatusResponse>

    @POST("list/{list_id}/remove_item")
    suspend fun removeItem(
        @Path("list_id") listId: Int,
        @Query("session_id") sessionId: String,
        @Body body: ListItemRequest
    ): Response<StatusResponse>

    @DELETE("list/{list_id}")
    suspend fun deleteList(
        @Path("list_id") listId: Int,
        @Query("session_id") sessionId: String
    ): Response<StatusResponse>
}
