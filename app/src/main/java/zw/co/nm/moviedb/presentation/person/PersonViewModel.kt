package zw.co.nm.moviedb.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPersonResponse
import zw.co.nm.moviedb.data.remote.util.Response

class PersonViewModel(application: Application) :
    AndroidViewModel(application) {
    private val personRepo = PersonRepo()

    private val _getPerson =
        MutableLiveData<Response<GetPersonResponse>>()
    val getPerson: LiveData<Response<GetPersonResponse>> =
        _getPerson

    private val _getCombinedCreditsResponse =
        MutableLiveData<Response<GetCombinedCreditsResponse>>()
    val getCombinedCreditsResponse: LiveData<Response<GetCombinedCreditsResponse>> =
        _getCombinedCreditsResponse


    fun getPerson(personId: Int) {
        viewModelScope.launch {
            _getPerson.value = personRepo.getPerson(personId)
        }
    }

    fun getCombinedCredits(personId: Int) {
        viewModelScope.launch {
            _getCombinedCreditsResponse.value = personRepo.getCombinedCredits(personId)
        }

    }

}