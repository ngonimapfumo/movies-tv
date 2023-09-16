package zw.co.nm.moviedb.ui.reviews

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetReviewsResponse
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ReviewsRepo {

    suspend fun getTvShowReviews(tvShowId: Int): Response<GetReviewsResponse> =
        apiCall { NetworkManager.tvShowService.getTVReviews(tvShowId) }

    suspend fun getMovieReviews(id: Int): Response<GetReviewsResponse> =
        apiCall { NetworkManager.movieService.getMovieReviews(id) }
}