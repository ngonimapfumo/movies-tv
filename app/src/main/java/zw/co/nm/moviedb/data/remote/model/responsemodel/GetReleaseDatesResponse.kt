package zw.co.nm.moviedb.data.remote.model.responsemodel


import com.google.gson.annotations.SerializedName

data class GetReleaseDatesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReleaseDatesResponse.Result>
) {
    data class Result(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("release_dates")
        val releaseDates: List<zw.co.nm.moviedb.data.remote.model.responsemodel.GetReleaseDatesResponse.Result.ReleaseDate>
    ) {
        data class ReleaseDate(
            @SerializedName("certification")
            val certification: String,
            @SerializedName("descriptors")
            val descriptors: List<Any>,
            @SerializedName("iso_639_1")
            val iso6391: String,
            @SerializedName("note")
            val note: String,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("type")
            val type: Int
        )
    }
}