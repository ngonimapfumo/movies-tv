package zw.co.nm.moviedb.presentation.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetTrendingResponse
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class TrendingAdapter(
    items: List<GetTrendingResponse.Result> = emptyList()
) : RecyclerView.Adapter<TrendingAdapter.ItemViewHolder>() {

    private val data: MutableList<GetTrendingResponse.Result> = items
        .filter { it.mediaType == "movie" || it.mediaType == "tv" }
        .toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMovieMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        ImageLoader.loadPoster(
            holder.binding.imageView,
            item.posterPath,
            placeholder = R.drawable.sample_cover_small
        )
        holder.itemView.setOnClickListener {
            when (item.mediaType) {
                "tv" -> PageNavUtils.navTvDetailsPage(holder.itemView.context, item.id)
                else -> PageNavUtils.navMovieDetailsPage(holder.itemView.context, item.id)
            }
        }
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    fun submitList(items: List<GetTrendingResponse.Result>) {
        data.clear()
        data.addAll(items.filter { it.mediaType == "movie" || it.mediaType == "tv" })
        notifyDataSetChanged()
    }

    fun appendList(items: List<GetTrendingResponse.Result>) {
        val filtered = items.filter { it.mediaType == "movie" || it.mediaType == "tv" }
        if (filtered.isEmpty()) return
        val start = data.size
        data.addAll(filtered)
        notifyItemRangeInserted(start, filtered.size)
    }

    class ItemViewHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}
