package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.models.Movie
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.PageNavUtils

class MovieListAdapter(private var data: ArrayList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].poster_path
        //  binding!!.text.text = data[position].title
        Picasso.get().load(IMAGE_BASE_URL + imgPath).placeholder(R.drawable.sample_cover_large)
            .into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.toMovieDetailsPage(holder.itemView.context, data[position].id)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}