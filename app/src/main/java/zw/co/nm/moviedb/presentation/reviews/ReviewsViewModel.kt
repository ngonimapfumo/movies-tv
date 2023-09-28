package zw.co.nm.moviedb.presentation.reviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response

class ReviewsViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewsRepo = ReviewsRepo()

    private val _getTvReviews =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>>()
    val getTvReviews: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>> =
        _getTvReviews

    private val _getMovieReviews =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>>()
    val getMovieReviews: LiveData<Response<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse>> =
        _getMovieReviews

    fun getTvReviews(id: Int) {
        viewModelScope.launch {
            _getTvReviews.value = reviewsRepo.getTvShowReviews(id)
        }
    }

    fun getMovieReviews(id: Int) {
        viewModelScope.launch {
            _getMovieReviews.value = reviewsRepo.getMovieReviews(id)
        }
    }


}