package zw.co.nm.moviedb.presentation.config

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.TmdbCountry
import zw.co.nm.moviedb.data.remote.util.Response

class ConfigViewModel(application: Application) : AndroidViewModel(application) {

    private val configRepo = ConfigRepo()

    private val _getTranslations = MutableLiveData<Response<List<String>>>()
    val getTranslations: LiveData<Response<List<String>>> = _getTranslations

    private val _getCountries = MutableLiveData<Response<List<TmdbCountry>>>()
    val getCountries: LiveData<Response<List<TmdbCountry>>> = _getCountries

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
