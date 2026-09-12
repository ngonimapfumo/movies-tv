package zw.co.nm.moviedb.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetMovieImagesResponse
import zw.co.nm.moviedb.databinding.ItemPosterDetailBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.ImageLoader

class PosterAdapter(
    private var data: List<GetMovieImagesResponse.Poster>
) : RecyclerView.Adapter<PosterAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding =
            ItemPosterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val poster = data[position]
        ImageLoader.loadPoster(
            holder.binding.imageView,
            poster.filePath,
            placeholder = R.drawable.sample_recycler_small_exp
        )
        holder.binding.iso6391Txt.text = poster.iso6391
        holder.itemView.setOnClickListener {
            val alertDialog = MaterialAlertDialogBuilder(holder.itemView.context)
            val customLayout: View =
                View.inflate(holder.itemView.context, R.layout.dialog_view_img, null)
            val img = customLayout.findViewById<ImageView>(R.id.posterImageView)
            Picasso.get()
                .load(IMAGE_BASE_URL + poster.filePath)
                .resize(900, 1350)
                .centerInside()
                .placeholder(R.drawable.sample_cover_large_exp)
                .into(img)
            alertDialog.setView(customLayout)
            alertDialog.show()
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemPosterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
