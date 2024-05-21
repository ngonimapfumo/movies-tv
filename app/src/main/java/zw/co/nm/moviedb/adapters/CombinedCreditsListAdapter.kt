package zw.co.nm.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.LOW_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.PageNavUtils

class CombinedCreditsListAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse.Cast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        val mediaType = data[position].mediaType
        val id = data[position].id
        val displayMetricsWidth = ConfigStore.getInt(
            holder.itemView.context,
            Constants.DISPLAY_METRICS_WIDTH
        )

        val picasso = Picasso.get().load(LOW_RES_IMAGE_BASE_URL + imgPath)
        if (displayMetricsWidth >= 1080) {
            picasso.resize(270, 400)
        }
        picasso.into(binding!!.imageView)

        holder.itemView.setOnClickListener {
            when (mediaType) {
                "movie" -> {
                    proceedToMovie(holder.itemView.context, position)
                }

                "tv" -> {
                    PageNavUtils.navTvDetailsPage(
                        holder.itemView.context, id
                    )

                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private fun proceedToMovie(context: Context, position: Int) {
        PageNavUtils.navMovieDetailsPage(
            context,
            data[position].id
        )
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}