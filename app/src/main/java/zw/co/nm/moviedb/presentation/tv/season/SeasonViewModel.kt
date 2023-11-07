package zw.co.nm.moviedb.presentation.tv.season

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore

class SeasonViewModel(application: Application) : AndroidViewModel(application) {
    private val seasonRepo = SeasonRepo()
    private var language = ConfigStore.getString(application, "LANGUAGE_KEY")
    private val _getSeasonDetail =
        MutableLiveData<Response<GetTvSeasonDetail>>()
    val getSeasonDetail: LiveData<Response<GetTvSeasonDetail>> =
        _getSeasonDetail

    fun getSeasonDetail(tvShowId: Int, season: Int) {
        viewModelScope.launch {
            _getSeasonDetail.value = seasonRepo.getSeasonDetail(tvShowId, season, language!!)
        }
    }

}