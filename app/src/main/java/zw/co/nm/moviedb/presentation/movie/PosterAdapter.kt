package zw.co.nm.moviedb.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemPosterDetailBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL

class PosterAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse.Poster>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemPosterDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemPosterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imagePath = data[position].filePath
        Picasso.get().load(IMAGE_BASE_URL + imagePath).into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "" + position, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemPosterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}