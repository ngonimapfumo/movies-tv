package zw.co.nm.moviedb.data.domain.mappers

import zw.co.nm.moviedb.data.domain.models.TV
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse

object TVMapper {

    fun buildFrom(response: GetTVShowDetailResponse): TV {
        return TV(
            name = response.name,
            id = response.id,
            voteAverage = response.voteAverage,
            tagline = response.tagline,
            status = response.status,
            popularity = response.popularity,
            overview = response.overview,
            homepage = response.homepage,
            genres = emptyList(),
            posterPath = response.posterPath,
            adult = response.adult,
            backdropPath = response.backdropPath,
            productionCompanies = response.productionCompanies,
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
            seasons = emptyList(),
            type = response.type

        )
    }


}