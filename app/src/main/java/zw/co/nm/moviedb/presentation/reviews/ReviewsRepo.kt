package zw.co.nm.moviedb.presentation.reviews

import zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse
import zw.co.nm.moviedb.data.remote.util.NetworkManager
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.GeneralUtil.apiCall

class ReviewsRepo {

    suspend fun getTvShowReviews(tvShowId: Int, language: String): Response<GetReviewsResponse> =
        apiCall { NetworkManager.tvShowService.getTVReviews(tvShowId, language) }

    suspend fun getMovieReviews(id: Int, language: String): Response<GetReviewsResponse> =
        apiCall { NetworkManager.movieService.getMovieReviews(id, language) }
}