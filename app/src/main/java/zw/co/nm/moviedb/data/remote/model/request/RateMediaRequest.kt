package zw.co.nm.moviedb.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class RateMediaRequest(
    @SerializedName("value")
    val value: Double
)
