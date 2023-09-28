package zw.co.nm.moviedb.presentation.person

import retrofit2.Response
import zw.co.nm.moviedb.data.remote.NetworkManager

class PersonRepo {
    suspend fun getPerson(query: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse> =
        NetworkManager.movieService.getPerson(query)

    suspend fun getCombinedCredits(query: Int): Response<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse> =
        NetworkManager.movieService.getCombinedCredits(query)
}