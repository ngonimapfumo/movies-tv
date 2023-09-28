package zw.co.nm.moviedb.presentation.trailers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response

class TrailersViewModel(application: Application) :
    AndroidViewModel(application) {

    private val trailersRepo = TrailersRepo()

    private val _getTrailers =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>>()
    val getTrailers: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>> =
        _getTrailers

    private val _getTVTrailers =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>>()
    val getTVTrailers: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse>> =
        _getTrailers

    fun getTrailers(movieId: Int) {
        viewModelScope.launch {
            _getTrailers.value = trailersRepo.getTrailers(movieId)
        }
    }

    fun getTvTrailers(tvShowId: Int) {
        viewModelScope.launch {
            _getTrailers.value = trailersRepo.getTVTrailers(tvShowId)
        }
    }


}