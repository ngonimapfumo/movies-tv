package zw.co.nm.moviedb.util

import android.content.Context
import android.view.LayoutInflater
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import zw.co.nm.moviedb.R
import kotlin.math.roundToInt

object RateMediaDialog {

    fun show(
        context: Context,
        currentRating: Double?,
        onRate: (Double) -> Unit,
        onClear: (() -> Unit)? = null
    ) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_rate_media, null)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val valueText = view.findViewById<TextView>(R.id.ratingValueText)

        fun updateLabel(rating: Float) {
            val value = ((rating * 2).roundToInt() / 2.0)
            valueText.text = context.getString(R.string.your_rating_value, value)
        }

        val initial = (currentRating ?: 0.0).toFloat().coerceIn(0f, 10f)
        ratingBar.rating = if (initial > 0f) initial else 5f
        updateLabel(ratingBar.rating)
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateLabel(rating)
        }

        val builder = MaterialAlertDialogBuilder(context)
            .setTitle(R.string.rate_title)
            .setView(view)
            .setPositiveButton(R.string.rate) { _, _ ->
                val value = ((ratingBar.rating * 2).roundToInt() / 2.0).coerceIn(0.5, 10.0)
                onRate(value)
            }
            .setNegativeButton(R.string.cancel, null)

        if (currentRating != null && onClear != null) {
            builder.setNeutralButton(R.string.clear_rating) { _, _ -> onClear() }
        }

        builder.show()
    }
}
