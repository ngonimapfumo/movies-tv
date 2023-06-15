package zw.co.nm.moviedb.repo

import retrofit2.Response
import zw.co.nm.moviedb.data.api.NetworkManager
import zw.co.nm.moviedb.model.GetPersonResponse

class PersonRepo {
    suspend fun getPerson(query: Int): Response<GetPersonResponse> =
        NetworkManager.apiService.getPerson(query)
}