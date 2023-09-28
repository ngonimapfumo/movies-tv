package zw.co.nm.moviedb.data.remote.model.request


import com.google.gson.annotations.SerializedName

data class AddFavoriteRequest(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)