package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetMovieGenres(
    @SerializedName("genres")
    val genres: List<Genre?>?
) {
    data class Genre(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )
}