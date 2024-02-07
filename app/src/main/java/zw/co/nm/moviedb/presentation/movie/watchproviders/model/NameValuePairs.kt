package zw.co.nm.moviedb.presentation.movie.watchproviders.model

data class NameValuePairs(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)


data class Buy(
    val values: List<NameValuePairs>
)

data class Root(
    val buy: Buy,
)
