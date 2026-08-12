package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class AccountStatesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("watchlist")
    val watchlist: Boolean
)
