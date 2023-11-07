package zw.co.nm.moviedb.presentation.config

import zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTranslations
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ConfigRepo {
    suspend fun getConfigTranslations(): Response<GetTranslations> =
        apiCall { NetworkManager.configService.getTranslations() }

    suspend fun getCountries(): Response<GetCountriesResponse> =
        apiCall { NetworkManager.configService.getCountries() }
}