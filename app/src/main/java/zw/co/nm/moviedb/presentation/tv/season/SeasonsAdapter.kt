package zw.co.nm.moviedb.presentation.tv.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemSeasonDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class SeasonsAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetTVShowDetailResponse.Season>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemSeasonDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemSeasonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        binding!!.seasonTxt.text = data[position].name.replace(" ", "\n")
        Picasso.get().load(Constants.IMAGE_BASE_URL + imgPath)
            .placeholder(R.drawable.sample_recycler_small)
            .into(binding!!.imageView)

        binding!!.imageView.setOnClickListener {
            PageNavUtils.toSeasonPage(
                holder.itemView.context, data[position].seasonNumber
            )
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemSeasonDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}