package zw.co.nm.moviedb.presentation.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class MoviesAdapter(
    movies: List<GetPopularMoviesListResponse.Result> = emptyList()
) : RecyclerView.Adapter<MoviesAdapter.ItemMovieViewHolder>() {

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
        ImageLoader.loadPoster(
            holder.binding.imageView,
            movie.posterPath,
            placeholder = R.drawable.sample_cover_small
        )
        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context, movie.id)
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
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
