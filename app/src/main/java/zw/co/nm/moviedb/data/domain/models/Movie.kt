package zw.co.nm.moviedb.data.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,
    val genres: Genre,
    val homepage: String,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: ProductionCompany,
    @SerializedName("release_date")
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {

    data class Genre(
        val name: String
    )

    data class ProductionCompany(
        val name: String
    )
}
