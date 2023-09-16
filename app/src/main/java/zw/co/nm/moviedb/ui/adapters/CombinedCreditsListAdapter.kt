package zw.co.nm.moviedb.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.data.remote.networkmodel.GetCombinedCreditsResponse
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class CombinedCreditsListAdapter(private var data: List<GetCombinedCreditsResponse.Cast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemMovieDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        Picasso.get().load(Constants.MED_RES_IMAGE_BASE_URL + imgPath)
            .resize(270,400)
            .into(binding!!.imageView)

        holder.itemView.setOnClickListener {
            when (data[position].mediaType) {
                "movie" -> {
                        proceedToMovie(holder.itemView.context,position)
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

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun proceedToMovie(context: Context, position: Int){
        PageNavUtils.toMovieDetailsPage(
            context,
            data[position].id
        )
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}