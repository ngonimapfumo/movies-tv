package zw.co.nm.moviedb.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class AddWatchlistRequest(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("watchlist")
    val watchlist: Boolean
)
