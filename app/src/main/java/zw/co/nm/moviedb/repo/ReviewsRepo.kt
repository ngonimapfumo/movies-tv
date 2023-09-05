package zw.co.nm.moviedb.repo

import zw.co.nm.moviedb.data.remote.NetworkManager
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvShowReviews
import zw.co.nm.moviedb.utils.GeneralUtil

class ReviewsRepo {

    suspend fun getTvShowReviews(tvShowId: Int): Response<GetTvShowReviews> =
        GeneralUtil.apiCall { NetworkManager.tvShowService.getTVReviews(tvShowId) }
}