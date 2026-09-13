package zw.co.nm.moviedb.data.domain.mappers

import zw.co.nm.moviedb.data.domain.models.Movie

object MovieMapper {

    private var productionCompany: ArrayList<String>? = arrayListOf()
    private var genre: ArrayList<String>? = arrayListOf()
    fun buildFrom(response: zw.co.nm.moviedb.data.remote.model.response.GetMovieDetailResponse): Movie {
        if (genre!!.isNotEmpty()) {
            genre!!.clear()
        }
        if (productionCompany!!.isNotEmpty()) {
            productionCompany!!.clear()
        }
        response.productionCompanies.indices.forEach { i ->
            productionCompany!!.add(response.productionCompanies[i].name)
        }
        response.genres.indices.forEach { i ->
            genre!!.add(response.genres[i].name)

        }

        return Movie(
            adult = response.adult,
            genres =
            Movie.Genre(
                name = genre.toString().replace("[", "")
                    .replace("]", "")
            ),
            homepage = response.homepage.orEmpty(),
            id = response.id,
            originalTitle = response.originalTitle.orEmpty(),
            overview = response.overview.orEmpty(),
            popularity = response.popularity,
            posterPath = response.posterPath.orEmpty(),
            productionCompanies = Movie.ProductionCompany(
                name = productionCompany.toString().replace("[", "")
                    .replace("]", "")
            ),
            releaseDate = response.releaseDate.orEmpty(),
            runtime = response.runtime,
            status = response.status.orEmpty(),
            tagline = response.tagline.orEmpty(),
            title = response.title.orEmpty(),
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }

}