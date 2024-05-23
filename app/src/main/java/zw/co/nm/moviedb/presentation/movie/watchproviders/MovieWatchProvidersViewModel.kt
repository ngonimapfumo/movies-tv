package zw.co.nm.moviedb.presentation.movie.watchproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.util.Response

class MovieWatchProvidersViewModel():ViewModel() {

    private val watchProvidersRepo = MovieWatchProvidersRepo()
    private val _getMovieWatchProviders =
        MutableLiveData<Response<GetWatchProvidersResponse>>()
    val getMovieWatchProviders: LiveData<Response<GetWatchProvidersResponse>> =
        _getMovieWatchProviders


    fun getMovieWatchProviders(movieId:Int){
        viewModelScope.launch {
            _getMovieWatchProviders.value = watchProvidersRepo.getWatchProviders(movieId)
        }
    }
}