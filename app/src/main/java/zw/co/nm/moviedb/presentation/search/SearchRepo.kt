package zw.co.nm.moviedb.presentation.search

import android.content.Context
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.GeneralUtil

class SearchRepo(private val context: Context) {

    suspend fun searchMulti(
        query: String,
        page: Int
    ): Response<zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse> =
        GeneralUtil.apiCall {
            NetworkManager.movieService.searchMulti(
                query,
                page,
                ConfigStore.getBool(context, ConfigStore.SEARCH_CONFIG_KEY)
            )
        }
}