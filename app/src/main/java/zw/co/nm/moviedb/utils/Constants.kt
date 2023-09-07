package zw.co.nm.moviedb.utils

object Constants {

    val BASE_URL
        get() = "https://api.themoviedb.org/3/"

    val IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w500"
    val LOW_RES_IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w200"
    val MED_RES_IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w300"

    val SAVED_SHOW_ID
        get() = "tvShowId"
    val REVIEW_TYPE
        get() = "review_type"
    val REVIEW_TV
        get() = "tv_show"
    val REVIEW_MOVIE
        get() = "movie"
    val TRAILER_TYPE
        get() = "trailer_type"


}
