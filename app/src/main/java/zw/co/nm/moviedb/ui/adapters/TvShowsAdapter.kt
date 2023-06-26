package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.data.remote.model.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.PageNavUtils

class TvShowsAdapter(private var data: List<GetPopularTVSeriesListResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        Picasso.get().load(IMAGE_BASE_URL + imgPath).placeholder(R.drawable.sample_cover_small).into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            /*PageNavUtils.toMovieDetailsPage(holder.itemView.context,
                data[position].id)*/
            Toast.makeText(holder.itemView.context,
                "under construction", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}