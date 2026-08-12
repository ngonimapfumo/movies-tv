package zw.co.nm.moviedb.presentation.main.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.PageNavUtils

class TvShowsAdapter(
    private val data: MutableList<GetPopularTVSeriesListResponse.Result> = mutableListOf()
) : RecyclerView.Adapter<TvShowsAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemMovieMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val show = data[position]
        Picasso.get()
            .load(IMAGE_BASE_URL + show.posterPath)
            .placeholder(R.drawable.sample_cover_small)
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.navTvDetailsPage(holder.itemView.context, show.id)
        }
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

    class ItemMovieViewHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}
