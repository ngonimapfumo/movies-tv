package zw.co.nm.moviedb.presentation.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetEpisodeDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY

class TvShowsViewModel(application: Application) : AndroidViewModel(application) {

    var page: Int = 1
    private val tvShowsRepo = TvShowsRepo()
    private var language = ConfigStore.getStringLang(application, LANGUAGE_KEY)
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

    private val _getEpisodeDetail =
        MutableLiveData<Response<GetEpisodeDetailResponse>?>()
    val getEpisodeDetail: LiveData<Response<GetEpisodeDetailResponse>?> =
        _getEpisodeDetail

    fun getPopularTvShows() {
        viewModelScope.launch {
            _getPopularShows.value = tvShowsRepo.getPopularTvShows(page, language!!)
        }
    }

    fun getShowDetails(showId: Int) {
        viewModelScope.launch {
            _getShowDetails.value = tvShowsRepo.getTvShowDetails(showId, language!!)
        }
    }

    fun getTvCredits(showId: Int) {
        viewModelScope.launch {
            _getTvCredits.value = tvShowsRepo.getTvCredits(showId, language!!)
        }
    }

    fun getEpisodeDetail(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        viewModelScope.launch {
            _getEpisodeDetail.value =
                tvShowsRepo.getEpisodeDetail(seriesId, seasonNumber, episodeNumber, language!!)
        }
    }

}