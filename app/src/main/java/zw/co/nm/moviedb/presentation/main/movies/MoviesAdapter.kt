package zw.co.nm.moviedb.presentation.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.PageNavUtils

class MoviesAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieMainBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        Picasso.get().load(IMAGE_BASE_URL + imgPath).placeholder(R.drawable.sample_cover_small).into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context,
                data[position].id)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root)
}