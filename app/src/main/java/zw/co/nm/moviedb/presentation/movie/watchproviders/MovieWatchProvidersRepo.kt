package zw.co.nm.moviedb.presentation.movie.watchproviders

import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class MovieWatchProvidersRepo {
    suspend fun getWatchProviders(movieId:Int):Response<GetWatchProvidersResponse> =
        apiCall{
            NetworkManager.movieService.getWatchProviders(movieId)
        }
    }
