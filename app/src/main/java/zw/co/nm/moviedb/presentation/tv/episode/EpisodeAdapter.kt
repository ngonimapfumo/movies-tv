package zw.co.nm.moviedb.presentation.tv.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemEpisodeDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class EpisodeAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetTvSeasonDetail.Episode>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemEpisodeDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemEpisodeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].stillPath
        binding!!.textViewEpisodeName.text = data[position].name
        binding!!.textViewShortSummary.text = data[position].overview
        binding!!.textViewEpisodeNumber.text = buildString {
            append("Episode ")
            append(data[position].episodeNumber)
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + imgPath)
            .placeholder(R.drawable.sample_episode_exp)
            .into(binding!!.imageView)
        holder.itemView.setOnClickListener {
            PageNavUtils.navEpisodePage(
                holder.itemView.context,
                data[position].showId,
                data[position].seasonNumber,
                data[position].episodeNumber,
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemEpisodeDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}