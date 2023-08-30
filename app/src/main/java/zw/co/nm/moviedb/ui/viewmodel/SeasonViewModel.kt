package zw.co.nm.moviedb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvSeasonDetail
import zw.co.nm.moviedb.repo.SeasonRepo

class SeasonViewModel(application: Application) : AndroidViewModel(application) {
    private val seasonRepo = SeasonRepo()

    private val _getSeasonDetail = MutableLiveData<Response<GetTvSeasonDetail>>()
    val getSeasonDetail: LiveData<Response<GetTvSeasonDetail>> = _getSeasonDetail

    fun getSeasonDetail(tvShowId: Int, season: Int) {
        viewModelScope.launch {
            val response = seasonRepo.getSeasonDetail(tvShowId, season)
            if (response.isSuccessful) {
                _getSeasonDetail.postValue(response)
            }
        }
    }

}