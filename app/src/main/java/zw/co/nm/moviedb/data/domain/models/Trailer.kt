package zw.co.nm.moviedb.data.domain.models

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: Result
) {
    data class Result(
        @SerializedName("id")
        val id: String,
        @SerializedName("key")
        val key: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("official")
        val official: Boolean,
        @SerializedName("site")
        val site: String,
        @SerializedName("type")
        val type: String
    )
}