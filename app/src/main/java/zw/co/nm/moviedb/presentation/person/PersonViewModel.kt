package zw.co.nm.moviedb.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonViewModel(application: Application) :
    AndroidViewModel(application) {
    private val personRepo = PersonRepo()

    private val _getPerson =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse>>()
    val getPerson: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse>> =
        _getPerson

    private val _getCombinedCreditsResponse =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse>>()
    val getCombinedCreditsResponse: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse>> =
        _getCombinedCreditsResponse


    fun getPerson(personId: Int) {
        viewModelScope.launch {
            val response = personRepo.getPerson(personId)
            _getPerson.postValue(response)
        }
    }

    fun getCombinedCredits(personId: Int) {
        viewModelScope.launch {
            val response = personRepo.getCombinedCredits(personId)
            _getCombinedCreditsResponse.postValue(response)
        }

    }

}