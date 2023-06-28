package zw.co.nm.moviedb.data.domain.mappers

import zw.co.nm.moviedb.data.domain.models.Movie
import zw.co.nm.moviedb.data.remote.networkmodel.GetMovieDetailResponse

object MovieMapper {

    private var productionCompany: String? = null
    private var genre: String? = null
    fun buildFrom(response: GetMovieDetailResponse): Movie {
        response.productionCompanies.indices.forEach { i ->
            productionCompany = response.productionCompanies[i].name
        }
        response.genres.indices.forEach { i ->
            genre = response.genres[i].name
        }

        return Movie(
            adult = response.adult,
            genres = Movie.Genre(
                name = genre!!
            ),
            homepage = response.homepage,
            id = response.id,
            originalTitle = response.originalTitle,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.posterPath,
            productionCompanies = Movie.ProductionCompany(
                name = productionCompany!!
            ),
            releaseDate = response.releaseDate,
            runtime = response.runtime,
            status = response.status,
            tagline = response.tagline,
            response.title,
            voteAverage = response.voteAverage
        )
    }

}