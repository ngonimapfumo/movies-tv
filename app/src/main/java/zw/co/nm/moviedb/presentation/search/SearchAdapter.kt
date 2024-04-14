package zw.co.nm.moviedb.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemSearchDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil
import zw.co.nm.moviedb.util.PageNavUtils
import java.time.LocalDate

class SearchAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.SearchMultiResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemSearchDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemSearchDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var imgPath: Any? = null

        binding!!.textMediaType.text = data[position].mediaType
        when (data[position].mediaType) {
            "person" -> {
                imgPath = data[position].profilePath
                binding!!.textViewName.text = data[position].originalName
            }
            "movie" -> {
                imgPath = data[position].posterPath
                binding!!.textViewName.text = data[position].originalTitle
                when {
                    data[position].releaseDate.isEmpty() -> {
                        binding!!.textViewRelease.text = ""
                    }

                    else -> {
                        binding!!.textViewRelease.text =
                            LocalDate.parse(data[position].releaseDate).year.toString()
                    }
                }
            }

            "tv" -> {
                imgPath = data[position].posterPath
                binding!!.textViewName.text = data[position].originalName
                when {
                    data[position].firstAirDate.isEmpty() -> {
                        binding!!.textViewRelease.text = ""
                    }

                    else -> {
                        binding!!.textViewRelease.text =
                            LocalDate.parse(data[position].firstAirDate).year.toString()
                    }
                }
            }
        }

        Picasso.get().load(Constants.IMAGE_BASE_URL + imgPath)
            .into(binding!!.imageView)


        holder.itemView.setOnClickListener {
            when (data[position].mediaType) {


                "movie" -> {
                    if (data[position].adult) {
                        GeneralUtil.generalAlertDialog(
                            holder.itemView.context,
                            holder.itemView.context.getString(R.string.warning),
                            holder.itemView.context.getString(R.string.content_may_contain_explicit_images),
                            holder.itemView.context.getString(R.string.proceed),
                            holder.itemView.context.getString(R.string.cancel),
                            { _, _ -> proceedToMovie(holder.itemView.context, position) },
                            null
                        )
                    } else
                        proceedToMovie(holder.itemView.context, position)

                }

                "person" -> {
                    PageNavUtils.toPersonDetailsPage(
                        holder.itemView.context,
                        data[position].id
                    )
                }

                "tv" -> {
                    PageNavUtils.toTvDetailsPage(
                        holder.itemView.context,
                        data[position].id
                    )

                }
            }
        }


    }

    private fun proceedToMovie(context: Context?, position: Int) {
        PageNavUtils.toMovieDetailsPage(
            context!!,
            data[position].id
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemSearchDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}