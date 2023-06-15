package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemSearchDetailBinding
import zw.co.nm.moviedb.model.SearchMultiResponse
import zw.co.nm.moviedb.utils.Constants
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
        }
        else if (data[position].mediaType == "tv"){
            imgPath = data[position].posterPath
            binding!!.textViewName.text = data[position].originalName
        }

        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath)
            .into(binding!!.imageView)


        holder.itemView.setOnClickListener {
            when (data[position].mediaType) {
                "movie" -> {
                    PageNavUtils.toMovieDetailsPage(
                        holder.itemView.context,
                        data[position].id
                    )
                }

                "person" -> {
                    PageNavUtils.toPersonDetailsPage(
                        holder.itemView.context,
                        data[position].id
                    )
                }

                "tv" -> {
                    Toast.makeText(
                        holder.itemView.context,
                        "working on tv views",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemSearchDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}