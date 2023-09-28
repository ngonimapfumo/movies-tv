package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetTvSeasonDetail(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episodes")
    val episodes: List<zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail.Episode>,
    val _id: String,
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("vote_average")
    val voteAverage: Double
) {
    data class Episode(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("crew")
        val crew: List<zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail.Episode.Crew>,
        @SerializedName("episode_number")
        val episodeNumber: Int,
        @SerializedName("episode_type")
        val episodeType: String,
        @SerializedName("guest_stars")
        val guestStars: List<zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail.Episode.GuestStar>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("production_code")
        val productionCode: String,
        @SerializedName("runtime")
        val runtime: Int,
        @SerializedName("season_number")
        val seasonNumber: Int,
        @SerializedName("show_id")
        val showId: Int,
        @SerializedName("still_path")
        val stillPath: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    ) {
        data class Crew(
            @SerializedName("adult")
            val adult: Boolean,
            @SerializedName("credit_id")
            val creditId: String,
            @SerializedName("department")
            val department: String,
            @SerializedName("gender")
            val gender: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("job")
            val job: String,
            @SerializedName("known_for_department")
            val knownForDepartment: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("original_name")
            val originalName: String,
            @SerializedName("popularity")
            val popularity: Double,
            @SerializedName("profile_path")
            val profilePath: String
        )

        data class GuestStar(
            @SerializedName("adult")
            val adult: Boolean,
            @SerializedName("character")
            val character: String,
            @SerializedName("credit_id")
            val creditId: String,
            @SerializedName("gender")
            val gender: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("known_for_department")
            val knownForDepartment: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("order")
            val order: Int,
            @SerializedName("original_name")
            val originalName: String,
            @SerializedName("popularity")
            val popularity: Double,
            @SerializedName("profile_path")
            val profilePath: String
        )
    }
}