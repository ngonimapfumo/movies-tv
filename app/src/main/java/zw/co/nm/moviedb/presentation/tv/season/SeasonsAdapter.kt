package zw.co.nm.moviedb.presentation.tv.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse
import zw.co.nm.moviedb.databinding.ItemSeasonDetailBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class SeasonsAdapter(
    private var data: List<GetTVShowDetailResponse.Season>
) : RecyclerView.Adapter<SeasonsAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding =
            ItemSeasonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val season = data[position]
        holder.binding.seasonTxt.text = season.name.replace(" ", "\n")
        ImageLoader.loadPoster(
            holder.binding.imageView,
            season.posterPath,
            placeholder = R.drawable.sample_recycler_small_exp
        )
        holder.binding.imageView.setOnClickListener {
            PageNavUtils.navSeasonPage(holder.itemView.context, season.seasonNumber)
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemSeasonDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
