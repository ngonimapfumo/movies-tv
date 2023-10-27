package zw.co.nm.moviedb.presentation.config

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetTranslations
import zw.co.nm.moviedb.data.remote.util.Response

class ConfigViewModel(application: Application) : AndroidViewModel(application) {

    private val configRepo = ConfigRepo()

    private val _getTranslations =
        MutableLiveData<Response<GetTranslations>>()
    val getTranslations: LiveData<Response<GetTranslations>> =
        _getTranslations

    fun getTranslations() {
        viewModelScope.launch {
            _getTranslations.value = configRepo.getConfigTranslations()
        }
    }
}