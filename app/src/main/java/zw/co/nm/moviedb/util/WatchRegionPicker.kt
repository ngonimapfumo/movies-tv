package zw.co.nm.moviedb.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetCountriesResponse

object WatchRegionPicker {

    data class CountryOption(
        val iso: String,
        val displayName: String
    ) {
        override fun toString(): String = displayName
    }

    fun toOptions(
        countries: List<GetCountriesResponse.GetCountriesResponseItem>
    ): List<CountryOption> =
        countries
            .map {
                CountryOption(
                    iso = it.iso31661.uppercase(),
                    displayName = "${it.englishName} (${it.iso31661.uppercase()})"
                )
            }
            .sortedBy { it.displayName.lowercase() }

    fun show(
        context: Context,
        countries: List<GetCountriesResponse.GetCountriesResponseItem>,
        onSelected: (isoCode: String?) -> Unit
    ): AlertDialog {
        val options = toOptions(countries)
        val labels = buildList {
            add(context.getString(R.string.watch_region_device_default))
            addAll(options.map { it.displayName })
        }.toTypedArray()

        val preferred = ConfigStore.getPreferredWatchRegion(context)
        val checked = when {
            !ConfigStore.hasWatchRegionOverride(context) -> 0
            else -> {
                val index = options.indexOfFirst { it.iso == preferred }
                if (index >= 0) index + 1 else 0
            }
        }

        return MaterialAlertDialogBuilder(context)
            .setTitle(R.string.watch_region)
            .setSingleChoiceItems(labels, checked) { dialog, which ->
                if (which == 0) {
                    ConfigStore.clearConfig(context, Constants.WATCH_REGION)
                    onSelected(null)
                } else {
                    val option = options[which - 1]
                    ConfigStore.saveStringConfig(context, Constants.WATCH_REGION, option.iso)
                    onSelected(option.iso)
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}
