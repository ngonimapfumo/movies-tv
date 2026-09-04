package zw.co.nm.moviedb.presentation.lists

import zw.co.nm.moviedb.data.remote.model.request.CreateListRequest
import zw.co.nm.moviedb.data.remote.model.request.ListItemRequest
import zw.co.nm.moviedb.data.remote.model.response.CreateListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetAccountListsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetListDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ListsRepo {

    suspend fun getAccountLists(
        accountId: Int,
        sessionId: String,
        language: String,
        page: Int
    ): Response<GetAccountListsResponse> =
        apiCall {
            NetworkManager.accountService.getAccountLists(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }

    suspend fun createList(
        sessionId: String,
        name: String,
        description: String,
        language: String
    ): Response<CreateListResponse> =
        apiCall {
            NetworkManager.listService.createList(
                sessionId = sessionId,
                body = CreateListRequest(
                    name = name,
                    description = description,
                    language = language
                )
            )
        }

    suspend fun getListDetail(listId: Int, language: String): Response<GetListDetailResponse> =
        apiCall {
            NetworkManager.listService.getListDetail(listId, language)
        }

    suspend fun addItem(
        listId: Int,
        sessionId: String,
        mediaId: Int
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.listService.addItem(
                listId = listId,
                sessionId = sessionId,
                body = ListItemRequest(mediaId)
            )
        }

    suspend fun removeItem(
        listId: Int,
        sessionId: String,
        mediaId: Int
    ): Response<StatusResponse> =
        apiCall {
            NetworkManager.listService.removeItem(
                listId = listId,
                sessionId = sessionId,
                body = ListItemRequest(mediaId)
            )
        }

    suspend fun deleteList(listId: Int, sessionId: String): Response<StatusResponse> =
        apiCall {
            NetworkManager.listService.deleteList(listId, sessionId)
        }
}
