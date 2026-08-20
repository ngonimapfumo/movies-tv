package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class GetWatchProvidersResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("results")
    val results: Map<String, CountryWatchProviders> = emptyMap()
)

data class CountryWatchProviders(
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("flatrate")
    val flatrate: List<WatchProvider> = emptyList(),
    @SerializedName("rent")
    val rent: List<WatchProvider> = emptyList(),
    @SerializedName("buy")
    val buy: List<WatchProvider> = emptyList(),
    @SerializedName("ads")
    val ads: List<WatchProvider> = emptyList(),
    @SerializedName("free")
    val free: List<WatchProvider> = emptyList()
) {
    fun hasAnyProvider(): Boolean =
        flatrate.isNotEmpty() ||
            rent.isNotEmpty() ||
            buy.isNotEmpty() ||
            ads.isNotEmpty() ||
            free.isNotEmpty()
}

data class WatchProvider(
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("provider_id")
    val providerId: Int = 0,
    @SerializedName("provider_name")
    val providerName: String = "",
    @SerializedName("display_priority")
    val displayPriority: Int = Int.MAX_VALUE
)
