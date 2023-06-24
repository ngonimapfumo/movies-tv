package zw.co.nm.moviedb.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.data.remote.model.SearchMultiResponse
import zw.co.nm.moviedb.databinding.ItemSearchDetailBinding
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.GeneralUtil
import zw.co.nm.moviedb.utils.PageNavUtils

class SearchAdapter(private var data: List<SearchMultiResponse.Result>) :
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

        if (data[position].mediaType == "person") {
            imgPath = data[position].profilePath
            binding!!.textViewName.text = data[position].originalName

        } else if (data[position].mediaType == "movie") {
            imgPath = data[position].posterPath
            binding!!.textViewName.text = data[position].originalTitle
        } else if (data[position].mediaType == "tv") {
            imgPath = data[position].posterPath
            binding!!.textViewName.text = data[position].originalName
        }

        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath)
            .into(binding!!.imageView)


        holder.itemView.setOnClickListener {
            when (data[position].mediaType) {


                "movie" -> {
                    if (data[position].adult) {
                        GeneralUtil.generalAlertDialog(
                            holder.itemView.context,
                            "Warning!",
                            "Content may contain explicit images!",
                            "Proceed",
                            "Cancel",
                            { _, _ -> proceedToMovie(holder.itemView.context, position)},
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