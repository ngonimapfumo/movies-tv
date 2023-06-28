package zw.co.nm.moviedb.data.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
) {

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
}
