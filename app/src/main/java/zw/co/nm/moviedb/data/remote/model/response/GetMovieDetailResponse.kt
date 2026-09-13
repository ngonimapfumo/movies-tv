package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetMovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse.BelongsToCollection? = null,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse.Genre> = emptyList(),
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse.ProductionCompany> = emptyList(),
    @SerializedName("production_countries")
    val productionCountries: List<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse.ProductionCountry> = emptyList(),
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Long = 0,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse.SpokenLanguage> = emptyList(),
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    data class BelongsToCollection(
        @SerializedName("backdrop_path")
        val backdropPath: String? = null,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("poster_path")
        val posterPath: String? = null
    )

    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String,
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

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    )
}