package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import zw.co.nm.moviedb.data.remote.util.RatedValueDeserializer

data class AccountStatesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("favorite")
    val favorite: Boolean,
    @JsonAdapter(RatedValueDeserializer::class)
    @SerializedName("rated")
    val rated: Double? = null,
    @SerializedName("watchlist")
    val watchlist: Boolean
)
