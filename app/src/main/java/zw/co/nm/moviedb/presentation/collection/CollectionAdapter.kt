package zw.co.nm.moviedb.presentation.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetCollectionDetailResponse
import zw.co.nm.moviedb.databinding.ItemCollectionDetailBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class CollectionAdapter(
    private var data: List<GetCollectionDetailResponse.Part>
) : RecyclerView.Adapter<CollectionAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemCollectionDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val part = data[position]
        holder.binding.textViewMovieName.text = part.title
        holder.binding.textViewShortSummary.text = part.overview
        ImageLoader.loadLowResPoster(holder.binding.imageView, part.posterPath)
        holder.itemView.setOnClickListener {
            PageNavUtils.navMovieDetailsPage(holder.itemView.context, part.id)
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemCollectionDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
