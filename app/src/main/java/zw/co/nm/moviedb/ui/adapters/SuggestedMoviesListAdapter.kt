package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.data.remote.networkmodel.GetSimilarMoviesResponse
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.Constants.LOW_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.Constants.MED_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.utils.PageNavUtils

class SuggestedMoviesListAdapter(private var data: List<GetSimilarMoviesResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        //  binding!!.text.text = data[position].title
        Picasso.get().load(LOW_RES_IMAGE_BASE_URL + imgPath)
           // .resize(250,400)
            .into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.toMovieDetailsPage(
                holder.itemView.context,
                data[position].id
            )
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}