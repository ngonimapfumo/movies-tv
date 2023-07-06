package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.domain.models.TV
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.repo.TvShowsRepo

class TvShowsViewModel(application: Application) : AndroidViewModel(application) {

    var page: Int = 1
    private val tvShowsRepo = TvShowsRepo()

    private val _getPopularShows =
        MutableLiveData<Response<GetPopularTVSeriesListResponse>>()
    val getPopularShows: LiveData<Response<GetPopularTVSeriesListResponse>> =
        _getPopularShows

    private val _getShowDetails =
        MutableLiveData<TV?>()
    val getShowDetails: LiveData<TV?> =
        _getShowDetails

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

}