package zw.co.nm.moviedb.util

object Constants {

    val BASE_URL
        get() = "https://api.themoviedb.org/3/"

    val IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w500"
    val LOW_RES_IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w200"
    val MED_RES_IMAGE_BASE_URL
        get() = "https://image.tmdb.org/t/p/w300"

    val REQ_TOKEN
        get() = "request_token"

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

    val PREMIERE
        get() = 1
    val THEATRICAL_LIMITED
        get() = 2
    val THEATRICAL
        get() = 3
    val DIGITAL
        get() = 4
    val PHYSICAL
        get() = 5
    val TV
        get() = 6
    val COUNTRY_ISO
        get() = "countryIsoVal"
    val NETWORK_ERROR_MSG
        get() = "Error getting data"

    val LANGUAGE_KEY
        get() = "lang"


}
