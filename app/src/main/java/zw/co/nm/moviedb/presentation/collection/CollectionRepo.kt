package zw.co.nm.moviedb.presentation.collection

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class CollectionRepo {
    suspend fun getCollectionDetail(id: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCollectionDetailResponse> =
        apiCall { NetworkManager.collectionService.getCollectionDetail(id) }

}