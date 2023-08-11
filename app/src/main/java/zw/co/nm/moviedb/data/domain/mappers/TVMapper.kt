package zw.co.nm.moviedb.data.domain.mappers

import zw.co.nm.moviedb.data.domain.models.TV
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse

object TVMapper {

    private var genre: ArrayList<String>? = arrayListOf()
    private var productionCompany: ArrayList<String>? = arrayListOf()
    private var seasons: ArrayList<String>? = arrayListOf()
    fun buildFrom(
        response: GetTVShowDetailResponse
    ): TV {

        if (productionCompany!!.isNotEmpty()) {
            productionCompany!!.clear()
        }
        response.productionCompanies.indices.forEach { i ->
            productionCompany!!.add(response.productionCompanies[i].name)
        }

        if (genre!!.isNotEmpty()) {
            genre!!.clear()
        }

        response.genres.indices.forEach { i ->
            genre!!.add(response.genres[i].name)

        }
        if (seasons!!.isNotEmpty()) {
            seasons!!.clear()
        }

        response.seasons.indices.forEach { i ->
            seasons!!.add(response.seasons[i].posterPath)

        }






        return TV(
            name = response.name,
            id = response.id,
            voteAverage = response.voteAverage,
            tagline = response.tagline,
            status = response.status,
            popularity = response.popularity,
            overview = response.overview,
            homepage = response.homepage,
            genres = TV.Genre(
                name = genre.toString().replace("[", "")
                    .replace("]", "")
            ),
            posterPath = response.posterPath,
            adult = response.adult,
            backdropPath = response.backdropPath,
            productionCompanies = TV.ProductionCompany(
                name = productionCompany.toString().replace("[", "")
                    .replace("]", "")
            ),
            firstAirDate = response.firstAirDate,
            inProduction = response.inProduction,
            languages = response.languages!!,
            lastAirDate = response.lastAirDate,
            lastEpisodeToAir = TV.LastEpisodeToAir(
                airDate = response.lastEpisodeToAir!!.airDate,
                episodeNumber = response.lastEpisodeToAir.episodeNumber,
                voteAverage = response.lastEpisodeToAir.voteAverage,
                overview = response.lastEpisodeToAir.overview,
                name = response.lastEpisodeToAir.name,
                runtime = response.lastEpisodeToAir.runtime,
                id = response.lastEpisodeToAir.id,
                productionCode = response.lastEpisodeToAir.productionCode,
                seasonNumber = response.lastEpisodeToAir.seasonNumber,
                showId = response.lastEpisodeToAir.showId,
                voteCount = response.lastEpisodeToAir.voteCount
            ),
            voteCount = response.voteCount,
            networks = emptyList(),
            numberOfEpisodes = response.numberOfEpisodes,
            numberOfSeasons = response.numberOfSeasons,
            originalLanguage = response.originalLanguage,
            originalName = response.originalName,
            seasons = listOf(
                TV.Season(
                    posterPath = seasons.toString().replace("[", "")
                        .replace("]", "")
                )
            ),
            type = response.type

        )
    }


}