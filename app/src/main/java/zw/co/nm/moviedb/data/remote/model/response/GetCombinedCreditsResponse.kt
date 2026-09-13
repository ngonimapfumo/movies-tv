package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetCombinedCreditsResponse(
    @SerializedName("cast")
    val cast: List<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse.Cast>,
    @SerializedName("crew")
    val crew: List<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse.Crew>,
    @SerializedName("id")
    val id: Int
) {
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String? = null,
        @SerializedName("character")
        val character: String? = null,
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("episode_count")
        val episodeCount: Int = 0,
        @SerializedName("first_air_date")
        val firstAirDate: String? = null,
        @SerializedName("genre_ids")
        val genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        val id: Int,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("order")
        val order: Int = 0,
        @SerializedName("origin_country")
        val originCountry: List<String> = emptyList(),
        @SerializedName("original_language")
        val originalLanguage: String? = null,
        @SerializedName("original_name")
        val originalName: String? = null,
        @SerializedName("original_title")
        val originalTitle: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("popularity")
        val popularity: Double = 0.0,
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("release_date")
        val releaseDate: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )

    data class Crew(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("department")
        val department: String,
        @SerializedName("episode_count")
        val episodeCount: Int,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("job")
        val job: String,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}