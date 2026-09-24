package zw.co.nm.moviedb.util

/**
 * Picks posters suited for a separate title logo overlay.
 * TMDB marks textless / no-title art with a null (or blank) [iso_639_1].
 */
object PosterUtils {

    data class ImageCandidate(
        val filePath: String,
        val iso6391: String?,
        val voteAverage: Double,
        val voteCount: Int
    )

    fun bestTextlessPath(candidates: List<ImageCandidate>): String? =
        candidates
            .filter { it.filePath.isNotBlank() && it.iso6391.isNullOrBlank() }
            .maxWithOrNull(compareBy({ it.voteAverage }, { it.voteCount }))
            ?.filePath
}
