package zw.co.nm.moviedb.ui.collection

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetCollectionDetailResponse
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class CollectionRepo {
    suspend fun getCollectionDetail(id: Int): Response<GetCollectionDetailResponse> =
        apiCall { NetworkManager.collectionService.getCollectionDetail(id) }

}