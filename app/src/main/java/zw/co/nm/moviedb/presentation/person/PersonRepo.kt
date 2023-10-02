package zw.co.nm.moviedb.presentation.person

import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class PersonRepo {
    suspend fun getPerson(query: Int): Response<GetPersonResponse> =
        apiCall { NetworkManager.movieService.getPerson(query) }

    suspend fun getCombinedCredits(query: Int): Response<GetCombinedCreditsResponse> =
        apiCall { NetworkManager.movieService.getCombinedCredits(query) }
}