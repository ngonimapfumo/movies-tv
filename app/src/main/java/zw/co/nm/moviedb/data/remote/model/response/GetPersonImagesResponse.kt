package zw.co.nm.moviedb.data.remote.model.response

data class GetPersonImagesResponse(
    val id: Int,
    val profiles: List<Profile>
) {
    data class Profile(
        val aspect_ratio: Double,
        val file_path: String,
        val height: Int,
        val iso_639_1: Any?,
        val vote_average: Double,
        val vote_count: Int,
        val width: Int
    )
}