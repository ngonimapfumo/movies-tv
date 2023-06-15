package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import zw.co.nm.moviedb.model.GetPersonResponse
import zw.co.nm.moviedb.repo.PersonRepo

class PersonViewModel(application: Application) :
    AndroidViewModel(application) {
    private val personRepo = PersonRepo()

    private val _getPerson =
        MutableLiveData<Response<GetPersonResponse>>()
    val getPerson: LiveData<Response<GetPersonResponse>> =
        _getPerson

    fun getPerson(personId: Int) {
        viewModelScope.launch {
            val response = personRepo.getPerson(personId)
            _getPerson.postValue(response)
        }
    }

}