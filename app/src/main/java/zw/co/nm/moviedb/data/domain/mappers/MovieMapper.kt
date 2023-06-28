package zw.co.nm.moviedb.data.domain.mappers

import zw.co.nm.moviedb.data.domain.models.Movie
import zw.co.nm.moviedb.data.remote.model.GetMovieDetailResponse

object MovieMapper {

    fun buildFrom(response: GetMovieDetailResponse?): Movie {
        return Movie(
            adult = response!!.adult,
            genres = emptyList(),
            homepage = response.homepage,
            id = response.id,
            originalTitle = response.originalTitle,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.posterPath,
            productionCompanies = emptyList(),
            releaseDate = response.releaseDate,
            runtime = response.runtime,
            status = response.status,
            tagline = response.tagline,
            response.title,
            voteAverage = response.voteAverage
        )
    }

}