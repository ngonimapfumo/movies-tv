package zw.co.nm.moviedb.presentation.reviews

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ReviewsRepo {

    suspend fun getTvShowReviews(tvShowId: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReviewsResponse> =
        apiCall { NetworkManager.tvShowService.getTVReviews(tvShowId) }

    suspend fun getMovieReviews(id: Int): Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReviewsResponse> =
        apiCall { NetworkManager.movieService.getMovieReviews(id) }
}