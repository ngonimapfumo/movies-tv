package zw.co.nm.moviedb.presentation.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.PageNavUtils

class WatchlistAdapter(
    movies: List<GetPopularMoviesListResponse.Result> = emptyList(),
    private val onRemove: (GetPopularMoviesListResponse.Result) -> Unit
) : RecyclerView.Adapter<WatchlistAdapter.ItemMovieViewHolder>() {

    private val data: MutableList<GetPopularMoviesListResponse.Result> = movies.toMutableList()

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
        val movie = data[position]
        Picasso.get()
            .load(IMAGE_BASE_URL + movie.posterPath)
            .resize(336, 504)
            .centerCrop()
            .placeholder(R.drawable.sample_cover_small)
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context, movie.id)
        }
        holder.itemView.setOnLongClickListener {
            onRemove(movie)
            true
        }
    }

    fun submitList(movies: List<GetPopularMoviesListResponse.Result>) {
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

    fun appendList(movies: List<GetPopularMoviesListResponse.Result>) {
        if (movies.isEmpty()) return
        val start = data.size
        data.addAll(movies)
        notifyItemRangeInserted(start, movies.size)
    }

    class ItemMovieViewHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}
