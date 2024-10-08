package zw.co.nm.moviedb.presentation.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.databinding.ItemReviewDetailBinding
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import java.time.OffsetDateTime
import java.time.format.TextStyle
import java.util.Locale

class ReviewsAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetReviewsResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemReviewDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemReviewDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val localDate = OffsetDateTime.parse(data[position].createdAt)
        binding!!.textViewAuthorName.text = data[position].author
        binding!!.textViewContent.text = data[position].content
        binding!!.reviewedDate.text = buildString {
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
                data[position].content,
                "OKAY"
            )
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemReviewDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}