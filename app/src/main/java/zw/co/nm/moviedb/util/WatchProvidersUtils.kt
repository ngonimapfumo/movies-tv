package zw.co.nm.moviedb.util

import zw.co.nm.moviedb.data.remote.model.response.CountryWatchProviders
import zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse
import zw.co.nm.moviedb.data.remote.model.response.WatchProvider

data class WatchProviderItem(
    val providerId: Int,
    val name: String,
    val logoPath: String?,
    val offerType: String,
    val justWatchLink: String?
)

object WatchProvidersUtils {

    /**
     * When [preferredCountry] is set (user pick or device), use that region only.
     * Otherwise fall back through language / common regions.
     */
    fun resolveForCountry(
        response: GetWatchProvidersResponse,
        preferredCountry: String?,
        language: String?
    ): Pair<String, CountryWatchProviders>? {
        val results = response.results
        if (results.isEmpty()) return null

        val preferred = preferredCountry?.trim()?.uppercase()?.takeIf { it.length == 2 }
        if (preferred != null) {
            return preferred to (results[preferred] ?: CountryWatchProviders())
        }

        val candidates = buildList {
            language?.substringAfter('-', "")?.uppercase()?.takeIf { it.length == 2 }?.let { add(it) }
            add("US")
            add("ZA")
            add("GB")
        }.distinct()

        for (code in candidates) {
            val country = results[code]
            if (country != null && country.hasAnyProvider()) {
                return code to country
            }
        }

        return results.entries
            .firstOrNull { it.value.hasAnyProvider() }
            ?.let { it.key to it.value }
    }

    fun toItems(country: CountryWatchProviders): List<WatchProviderItem> {
        val items = mutableListOf<WatchProviderItem>()
        val seen = mutableSetOf<Int>()

        fun addAll(providers: List<WatchProvider>, type: String) {
            providers
                .sortedBy { it.displayPriority }
                .forEach { provider ->
                    if (seen.add(provider.providerId)) {
                        items.add(
                            WatchProviderItem(
                                providerId = provider.providerId,
                                name = provider.providerName,
                                logoPath = provider.logoPath,
                                offerType = type,
                                justWatchLink = country.link
                            )
                        )
                    }
                }
        }

        addAll(country.flatrate, "Stream")
        addAll(country.free, "Free")
        addAll(country.ads, "Ads")
        addAll(country.rent, "Rent")
        addAll(country.buy, "Buy")
        return items
    }
}
