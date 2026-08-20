package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.LOW_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.PageNavUtils

class CombinedCreditsListAdapter(
    private var data: List<GetCombinedCreditsResponse.Cast>
) : RecyclerView.Adapter<CombinedCreditsListAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemMovieDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val credit = data[position]
        val displayMetricsWidth = ConfigStore.getInt(
            holder.itemView.context,
            Constants.DISPLAY_METRICS_WIDTH
        )

        val picasso = Picasso.get().load(LOW_RES_IMAGE_BASE_URL + credit.posterPath)
        if (displayMetricsWidth >= 1080) {
            picasso.resize(270, 400)
        }
        picasso.into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            when (credit.mediaType) {
                "movie" -> {
                    PageNavUtils.navMovieDetailsPage(holder.itemView.context, credit.id)
                }

                "tv" -> {
                    PageNavUtils.navTvDetailsPage(holder.itemView.context, credit.id)
                }
            }
        }
    }

    class ItemMovieViewHolder(val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
