package zw.co.nm.moviedb.ui.reviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetReviewsResponse

class ReviewsViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewsRepo = ReviewsRepo()

    private val _getTvReviews = MutableLiveData<Response<GetReviewsResponse>>()
    val getTvReviews: LiveData<Response<GetReviewsResponse>> = _getTvReviews

    private val _getMovieReviews = MutableLiveData<Response<GetReviewsResponse>>()
    val getMovieReviews: LiveData<Response<GetReviewsResponse>> = _getMovieReviews

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