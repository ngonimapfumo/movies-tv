package zw.co.nm.moviedb.presentation.reviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore

class ReviewsViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewsRepo = ReviewsRepo()
    private var language = ConfigStore.getStringLang(application, "LANGUAGE_KEY")
    private val _getTvReviews =
        MutableLiveData<Response<GetReviewsResponse>>()
    val getTvReviews: LiveData<Response<GetReviewsResponse>> =
        _getTvReviews

    private val _getMovieReviews =
        MutableLiveData<Response<GetReviewsResponse>>()
    val getMovieReviews: LiveData<Response<GetReviewsResponse>> =
        _getMovieReviews

    fun getTvReviews(id: Int) {
        viewModelScope.launch {
            _getTvReviews.value = reviewsRepo.getTvShowReviews(id, language!!)
        }
    }

    fun getMovieReviews(id: Int) {
        viewModelScope.launch {
            _getMovieReviews.value = reviewsRepo.getMovieReviews(id, language!!)
        }
    }


}