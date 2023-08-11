package zw.co.nm.moviedb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse
import zw.co.nm.moviedb.repo.TvShowsRepo

class TvShowsViewModel(application: Application) : AndroidViewModel(application) {

    var page: Int = 1
    private val tvShowsRepo = TvShowsRepo()

    private val _getPopularShows =
        MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val getPopularShows: LiveData<Response<GetPopularTVSeriesListResponse>> =
        _getPopularShows

    private val _getShowDetails =
        MutableLiveData<Response<GetTVShowDetailResponse>?>()
    val getShowDetails: LiveData<Response<GetTVShowDetailResponse>?> =
        _getShowDetails

    private val _getTvCredits =
        MutableLiveData<Response<GetTVCreditsResponse>?>()
    val getTvCredits: LiveData<Response<GetTVCreditsResponse>?> =
        _getTvCredits

    fun getPopularTvShows() {
        viewModelScope.launch {
            val response = tvShowsRepo.getPopularTvShows(page)
            _getPopularShows.postValue(response)
        }
    }

    fun getShowDetails(showId: Int) {
        viewModelScope.launch {
            val response = tvShowsRepo.getTvShowDetails(showId)
            _getShowDetails.postValue(response)
        }
    }

    fun getTvCredits(showId: Int) {
        viewModelScope.launch {
            val response = tvShowsRepo.getTvCredits(showId)
            _getTvCredits.postValue(response)
        }
    }

}