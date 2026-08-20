package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetSimilarMoviesResponse
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants.DISPLAY_METRICS_WIDTH
import zw.co.nm.moviedb.util.Constants.LOW_RES_IMAGE_BASE_URL
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
        val displayMetrics = ConfigStore.getInt(holder.itemView.context, DISPLAY_METRICS_WIDTH)
        val picasso = Picasso.get()
            .load(LOW_RES_IMAGE_BASE_URL + movie.posterPath)
            .placeholder(R.drawable.sample_suggested)
        if (displayMetrics >= 1080) {
            picasso.resize(270, 400)
        }
        picasso.into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context, movie.id)
        }
    }

    class ItemMovieViewHolder(val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
