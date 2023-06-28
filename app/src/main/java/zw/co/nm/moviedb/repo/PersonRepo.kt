package zw.co.nm.moviedb.repo

import retrofit2.Response
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.networkmodel.GetCombinedCredits
import zw.co.nm.moviedb.data.remote.networkmodel.GetPersonResponse

class PersonRepo {
    suspend fun getPerson(query: Int): Response<GetPersonResponse> =
        NetworkManager.movieService.getPerson(query)

    suspend fun getCombinedCredits(query: Int): Response<GetCombinedCredits> =
        NetworkManager.movieService.getCombinedCredits(query)
}