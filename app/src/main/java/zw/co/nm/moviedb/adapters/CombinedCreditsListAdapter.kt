package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.ImageLoader
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
        ImageLoader.loadLowResPoster(holder.binding.imageView, credit.posterPath)
        holder.itemView.setOnClickListener {
            when (credit.mediaType) {
                "movie" -> PageNavUtils.navMovieDetailsPage(holder.itemView.context, credit.id)
                "tv" -> PageNavUtils.navTvDetailsPage(holder.itemView.context, credit.id)
            }
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
