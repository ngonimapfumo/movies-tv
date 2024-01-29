package zw.co.nm.moviedb.presentation.movie.posters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
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
        Picasso.get().load(IMAGE_BASE_URL + imagePath)
            .placeholder(R.drawable.sample_recycler_small_exp).into(binding!!.imageView)
        binding!!.iso6391Txt.text = data[position].iso6391
        holder.itemView.setOnClickListener {
            val alertDialog = AlertDialog.Builder(holder.itemView.context)
            val customLayout: View =
                View.inflate(holder.itemView.context, R.layout.dialog_view_img, null)
            val img = customLayout.findViewById<ImageView>(R.id.posterImageView)
            Picasso.get().load(IMAGE_BASE_URL + imagePath)
                .placeholder(R.drawable.sample_cover_large_exp).into(img)
            alertDialog.setView(customLayout)
            alertDialog.show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemPosterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}