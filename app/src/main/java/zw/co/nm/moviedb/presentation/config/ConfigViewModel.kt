package zw.co.nm.moviedb.presentation.config

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTranslations
import zw.co.nm.moviedb.data.remote.util.Response

class ConfigViewModel(application: Application) : AndroidViewModel(application) {

    private val configRepo = ConfigRepo()

    private val _getTranslations =
        MutableLiveData<Response<GetTranslations>>()
    val getTranslations: LiveData<Response<GetTranslations>> =
        _getTranslations

    private val _getCountries =
        MutableLiveData<Response<GetCountriesResponse>>()
    val getCountries: LiveData<Response<GetCountriesResponse>> =
        _getCountries

    fun getTranslations() {
        viewModelScope.launch {
            _getTranslations.value = configRepo.getConfigTranslations()
        }
    }

    fun getCountries() {
        viewModelScope.launch {
            _getCountries.value = configRepo.getCountries()
        }
    }
}