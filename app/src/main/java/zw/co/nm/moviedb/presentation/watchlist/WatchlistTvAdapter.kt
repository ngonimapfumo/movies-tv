package zw.co.nm.moviedb.presentation.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class WatchlistTvAdapter(
    shows: List<GetPopularTVSeriesListResponse.Result> = emptyList(),
    private val onRemove: (GetPopularTVSeriesListResponse.Result) -> Unit
) : RecyclerView.Adapter<WatchlistTvAdapter.ItemViewHolder>() {

    private val data: MutableList<GetPopularTVSeriesListResponse.Result> = shows.toMutableList()

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
        val show = data[position]
        ImageLoader.loadPoster(
            holder.binding.imageView,
            show.posterPath,
            placeholder = R.drawable.sample_cover_small
        )

        holder.itemView.setOnClickListener {
            PageNavUtils.navTvDetailsPage(holder.itemView.context, show.id)
        }
        holder.itemView.setOnLongClickListener {
            onRemove(show)
            true
        }
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    fun submitList(shows: List<GetPopularTVSeriesListResponse.Result>) {
        data.clear()
        data.addAll(shows)
        notifyDataSetChanged()
    }

    fun appendList(shows: List<GetPopularTVSeriesListResponse.Result>) {
        if (shows.isEmpty()) return
        val start = data.size
        data.addAll(shows)
        notifyItemRangeInserted(start, shows.size)
    }

    class ItemViewHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}
