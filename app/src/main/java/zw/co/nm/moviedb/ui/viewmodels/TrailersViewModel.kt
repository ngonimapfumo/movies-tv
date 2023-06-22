package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import zw.co.nm.moviedb.model.GetTrailersResponse
import zw.co.nm.moviedb.repo.TrailersRepo

class TrailersViewModel(application: Application) :
    AndroidViewModel(application) {

    private val trailersRepo = TrailersRepo()

    private val _getTrailers =
        MutableLiveData<Response<GetTrailersResponse>>()
    val getTrailers: LiveData<Response<GetTrailersResponse>> =
        _getTrailers

    fun getTrailers(movieId: Int) {
        viewModelScope.launch {
            val response = trailersRepo.getTrailers(movieId)
            _getTrailers.postValue(response)
        }
    }


}