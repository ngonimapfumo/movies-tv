package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class SuggestedMoviesListAdapter(
    private var data: List<GetSimilarMoviesResponse.Result>
) : RecyclerView.Adapter<SuggestedMoviesListAdapter.ItemMovieViewHolder>() {

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
        val movie = data[position]
        ImageLoader.loadLowResPoster(
            holder.binding.imageView,
            movie.posterPath,
            placeholder = R.drawable.sample_suggested
        )
        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context, movie.id)
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
