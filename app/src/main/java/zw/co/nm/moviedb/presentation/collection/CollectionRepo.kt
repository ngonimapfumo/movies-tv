package zw.co.nm.moviedb.presentation.collection

import zw.co.nm.moviedb.data.remote.model.response.GetCollectionDetailResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class CollectionRepo {
    suspend fun getCollectionDetail(id: Int): Response<GetCollectionDetailResponse> =
        apiCall { NetworkManager.collectionService.getCollectionDetail(id) }

}