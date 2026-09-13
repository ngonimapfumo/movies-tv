package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetCombinedCreditsResponse
import zw.co.nm.moviedb.databinding.ItemCreditPosterBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class CombinedCreditsListAdapter(
    private var data: List<GetCombinedCreditsResponse.Cast>
) : RecyclerView.Adapter<CombinedCreditsListAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemCreditPosterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val credit = data[position]
        val imagePath = credit.backdropPath?.takeIf { it.isNotBlank() }
            ?: credit.posterPath?.takeIf { it.isNotBlank() }
        ImageLoader.loadLandscapeThumb(
            holder.binding.imageView,
            imagePath,
            placeholder = R.drawable.sample_episode_exp
        )
        holder.binding.titleTxt.text = creditDisplayName(credit)
        holder.itemView.setOnClickListener {
            when (credit.mediaType) {
                "movie" -> PageNavUtils.navMovieDetailsPage(holder.itemView.context, credit.id)
                "tv" -> PageNavUtils.navTvDetailsPage(holder.itemView.context, credit.id)
            }
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    private fun creditDisplayName(credit: GetCombinedCreditsResponse.Cast): String {
        return when (credit.mediaType) {
            "tv" -> listOfNotNull(
                credit.name?.takeIf { it.isNotBlank() },
                credit.originalName?.takeIf { it.isNotBlank() }
            ).firstOrNull().orEmpty()

            else -> listOfNotNull(
                credit.title?.takeIf { it.isNotBlank() },
                credit.originalTitle?.takeIf { it.isNotBlank() },
                credit.name?.takeIf { it.isNotBlank() }
            ).firstOrNull().orEmpty()
        }
    }

    class ItemMovieViewHolder(val binding: ItemCreditPosterBinding) :
        RecyclerView.ViewHolder(binding.root)
}
