package zw.co.nm.moviedb.presentation.tv.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail
import zw.co.nm.moviedb.databinding.ItemEpisodeDetailBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class EpisodeAdapter(
    private var data: List<GetTvSeasonDetail.Episode>
) : RecyclerView.Adapter<EpisodeAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding =
            ItemEpisodeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val episode = data[position]
        holder.binding.textViewEpisodeName.text = episode.name
        holder.binding.textViewShortSummary.text = episode.overview
        holder.binding.textViewEpisodeNumber.text = buildString {
            append("Episode ")
            append(episode.episodeNumber)
        }
        ImageLoader.loadPoster(
            holder.binding.imageView,
            episode.stillPath,
            widthPx = 480,
            heightPx = 270,
            placeholder = R.drawable.sample_episode_exp
        )
        holder.itemView.setOnClickListener {
            PageNavUtils.navEpisodePage(
                holder.itemView.context,
                episode.showId,
                episode.seasonNumber,
                episode.episodeNumber,
            )
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemEpisodeDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
