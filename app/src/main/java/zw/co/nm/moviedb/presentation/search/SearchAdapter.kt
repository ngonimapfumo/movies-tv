package zw.co.nm.moviedb.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse
import zw.co.nm.moviedb.databinding.ItemSearchDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate

class SearchAdapter(
    results: List<SearchMultiResponse.Result> = emptyList()
) : RecyclerView.Adapter<SearchAdapter.ItemMovieViewHolder>() {

    private val data: MutableList<SearchMultiResponse.Result> = results.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemSearchDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding
        var imgPath: Any? = null

        binding.textMediaType.text = item.mediaType
        when (item.mediaType) {
            "person" -> {
                imgPath = item.profilePath
                binding.textViewName.text = item.originalName
                binding.textViewRelease.text = ""
            }

            "movie" -> {
                imgPath = item.posterPath
                binding.textViewName.text = item.originalTitle
                binding.textViewRelease.text = if (item.releaseDate.isEmpty()) {
                    ""
                } else {
                    LocalDate.parse(item.releaseDate).year.toString()
                }
            }

            "tv" -> {
                imgPath = item.posterPath
                binding.textViewName.text = item.originalName
                binding.textViewRelease.text = if (item.firstAirDate.isEmpty()) {
                    ""
                } else {
                    LocalDate.parse(item.firstAirDate).year.toString()
                }
            }
        }

        Picasso.get()
            .load(Constants.MED_RES_IMAGE_BASE_URL + imgPath)
            .resize(200, 300)
            .centerCrop()
            .placeholder(R.drawable.sample_cover_small)
            .into(binding.imageView)

        holder.itemView.setOnClickListener {
            when (item.mediaType) {
                "movie" -> {
                    if (item.adult) {
                        GeneralUtil.generalAlertDialog(
                            holder.itemView.context,
                            holder.itemView.context.getString(R.string.warning),
                            holder.itemView.context.getString(R.string.content_may_contain_explicit_images),
                            holder.itemView.context.getString(R.string.proceed),
                            holder.itemView.context.getString(R.string.cancel),
                            { _, _ -> proceedToMovie(holder.itemView.context, item.id) },
                            null
                        )
                    } else {
                        proceedToMovie(holder.itemView.context, item.id)
                    }
                }

                "person" -> {
                    PageNavUtils.navPersonDetailsPage(holder.itemView.context, item.id)
                }

                "tv" -> {
                    PageNavUtils.navTvDetailsPage(holder.itemView.context, item.id)
                }
            }
        }
    }

    fun submitList(results: List<SearchMultiResponse.Result>) {
        data.clear()
        data.addAll(results)
        notifyDataSetChanged()
    }

    fun appendList(results: List<SearchMultiResponse.Result>) {
        if (results.isEmpty()) return
        val start = data.size
        data.addAll(results)
        notifyItemRangeInserted(start, results.size)
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        Picasso.get().cancelRequest(holder.binding.imageView)
        super.onViewRecycled(holder)
    }

    private fun proceedToMovie(context: Context, movieId: Int) {
        PageNavUtils.navMovieDetailsPage(context, movieId)
    }

    class ItemMovieViewHolder(val binding: ItemSearchDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
