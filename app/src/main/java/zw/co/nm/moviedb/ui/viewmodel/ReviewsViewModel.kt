package zw.co.nm.moviedb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvShowReviews
import zw.co.nm.moviedb.repo.ReviewsRepo

class ReviewsViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewsRepo = ReviewsRepo()

    private val _getTvReviews = MutableLiveData<Response<GetTvShowReviews>>()
    val getTvReviews: LiveData<Response<GetTvShowReviews>> = _getTvReviews

    fun getTvReviews(id: Int) {
        viewModelScope.launch {
            val response = reviewsRepo.getTvShowReviews(id)
            if (response.isSuccessful) {
                _getTvReviews.postValue(response)
            }
        }
    }


}