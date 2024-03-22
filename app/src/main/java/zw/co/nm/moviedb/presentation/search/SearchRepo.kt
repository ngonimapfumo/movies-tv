package zw.co.nm.moviedb.presentation.search

import android.content.Context
import zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.GeneralUtil

class SearchRepo(private val context: Context) {

    suspend fun searchMulti(
        query: String,
        page: Int,
        language: String
    ): Response<SearchMultiResponse> =
        GeneralUtil.apiCall {
            NetworkManager.movieService.searchMulti(
                query,
                page,
                ConfigStore.getBool(context, ConfigStore.SEARCH_CONFIG_KEY),
                language
            )
        }
}

