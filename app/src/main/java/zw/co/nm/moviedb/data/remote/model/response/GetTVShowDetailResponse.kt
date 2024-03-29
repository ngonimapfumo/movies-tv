package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName


data class GetTVShowDetailResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("created_by")
    val createdBy: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.CreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Any>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.LastEpisodeToAir,
    @SerializedName("name")
    val name: String,
    @SerializedName("networks")
    val networks: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.Network>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.NextEpisodeToAir,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.ProductionCountry>,
    @SerializedName("seasons")
    val seasons: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.Season>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    data class CreatedBy(
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_path")
        val profilePath: String
    )

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

    data class NextEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_number")
        val episodeNumber: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("production_code")
        val productionCode: String,
        @SerializedName("runtime")
        val runtime: Any,
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
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: Any,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("name")
        val name: String
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
        val seasonNumber: Int,
        @SerializedName("vote_average")
        val voteAverage: Double
    )

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    )
}