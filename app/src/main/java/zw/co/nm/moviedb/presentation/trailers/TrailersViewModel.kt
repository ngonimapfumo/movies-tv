package zw.co.nm.moviedb.presentation.trailers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore

class TrailersViewModel(application: Application) :
    AndroidViewModel(application) {

    private val trailersRepo = TrailersRepo()
    private var language = ConfigStore.getString(application, "LANGUAGE_KEY")
    private val _getTrailers =
        MutableLiveData<Response<GetTrailersResponse>>()
    val getTrailers: LiveData<Response<GetTrailersResponse>> =
        _getTrailers

    private val _getTVTrailers =
        MutableLiveData<Response<GetTrailersResponse>>()
    val getTVTrailers: LiveData<Response<GetTrailersResponse>> =
        _getTrailers

    fun getTrailers(movieId: Int) {
        viewModelScope.launch {
            _getTrailers.value = trailersRepo.getTrailers(movieId, language!!)
        }
    }

    fun getTvTrailers(tvShowId: Int) {
        viewModelScope.launch {
            _getTrailers.value = trailersRepo.getTVTrailers(tvShowId, language!!)
        }
    }


}