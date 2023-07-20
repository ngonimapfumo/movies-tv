package zw.co.nm.moviedb.data.domain.models

import com.google.gson.annotations.SerializedName

data class TV(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("in_production")
    val inProduction: Boolean?,
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,
    val name: String?,
    val networks: List<Network>,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<Any>,
    val seasons: List<Season>,
    val status: String?,
    val tagline: String?,
    val type: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_number")
        val episodeNumber: Int,
        val id: Int,
        val name: String,
        val overview: String,
        @SerializedName("production_code")
        val productionCode: String,
        val runtime: Int,
        @SerializedName("season_number")
        val seasonNumber: Int,
        @SerializedName("show_id")
        val showId: Int,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )

    data class Network(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )

    data class Season(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_count")
        val episodeCount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("season_number")
        val seasonNumber: Int
    )
}