package zw.co.nm.moviedb.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateListRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("language")
    val language: String = "en"
)

data class ListItemRequest(
    @SerializedName("media_id")
    val mediaId: Int
)
