package zw.co.nm.moviedb.presentation.reviews

import zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ReviewsRepo {

    suspend fun getTvShowReviews(tvShowId: Int): Response<GetReviewsResponse> =
        apiCall { NetworkManager.tvShowService.getTVReviews(tvShowId) }

    suspend fun getMovieReviews(id: Int): Response<GetReviewsResponse> =
        apiCall { NetworkManager.movieService.getMovieReviews(id) }
}