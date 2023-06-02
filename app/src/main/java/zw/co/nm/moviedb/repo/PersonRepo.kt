package zw.co.nm.moviedb.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import zw.co.nm.moviedb.model.GetPersonResponse
import zw.co.nm.moviedb.network.NetworkManager
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.utils.GeneralUtil.apiCall

class PersonRepo {
    fun getPerson(query: Int): Flow<Response<GetPersonResponse>> =
        flow {
            emit(apiCall { NetworkManager.apiService.getPerson(query) })
        }
}