package zw.co.nm.moviedb.repo

import retrofit2.Response
import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.networkmodel.GetCombinedCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetPersonResponse

class PersonRepo {
    suspend fun getPerson(query: Int): Response<GetPersonResponse> =
        NetworkManager.movieService.getPerson(query)

    suspend fun getCombinedCredits(query: Int): Response<GetCombinedCreditsResponse> =
        NetworkManager.movieService.getCombinedCredits(query)
}