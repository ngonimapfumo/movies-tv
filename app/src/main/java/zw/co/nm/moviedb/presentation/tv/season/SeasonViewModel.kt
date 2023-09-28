package zw.co.nm.moviedb.presentation.tv.season

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response

class SeasonViewModel(application: Application) : AndroidViewModel(application) {
    private val seasonRepo = SeasonRepo()

    private val _getSeasonDetail =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTvSeasonDetail>>()
    val getSeasonDetail: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetTvSeasonDetail>> =
        _getSeasonDetail

    fun getSeasonDetail(tvShowId: Int, season: Int) {
        viewModelScope.launch {
            _getSeasonDetail.value = seasonRepo.getSeasonDetail(tvShowId, season)
        }
    }

}