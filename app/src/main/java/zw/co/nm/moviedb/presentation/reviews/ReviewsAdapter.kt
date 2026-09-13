package zw.co.nm.moviedb.presentation.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse
import zw.co.nm.moviedb.databinding.ItemReviewDetailBinding
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import java.time.OffsetDateTime
import java.time.format.TextStyle
import java.util.Locale

class ReviewsAdapter(
    private var data: List<GetReviewsResponse.Result>
) : RecyclerView.Adapter<ReviewsAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding =
            ItemReviewDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val review = data[position]
        val localDate = OffsetDateTime.parse(review.createdAt)
        holder.binding.textViewAuthorName.text = review.author
        holder.binding.textViewContent.text = review.content
        holder.binding.reviewedDate.text = buildString {
            append("-")
            append(localDate.dayOfMonth)
            append(" ")
            append(localDate.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
            append(" ")
            append(localDate.year)
        }
        holder.itemView.setOnClickListener {
            showGenericDialog(
                holder.itemView.context,
                review.content,
                "OKAY"
            )
        }
    }

    class ItemMovieViewHolder(val binding: ItemReviewDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
