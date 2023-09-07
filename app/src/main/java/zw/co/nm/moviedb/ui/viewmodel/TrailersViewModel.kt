package zw.co.nm.moviedb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetTrailersResponse
import zw.co.nm.moviedb.repo.TrailersRepo

class TrailersViewModel(application: Application) :
    AndroidViewModel(application) {

    private val trailersRepo = TrailersRepo()

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
            val response = trailersRepo.getTrailers(movieId)
            _getTrailers.postValue(response)
        }
    }
    fun getTvTrailers(tvShowId: Int) {
        viewModelScope.launch {
            val response = trailersRepo.getTVTrailers(tvShowId)
            _getTrailers.postValue(response)
        }
    }


}