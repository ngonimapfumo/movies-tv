package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class GetMovieKeywordsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("keywords")
    val keywords: List<Keyword> = emptyList()
) {
    data class Keyword(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}
