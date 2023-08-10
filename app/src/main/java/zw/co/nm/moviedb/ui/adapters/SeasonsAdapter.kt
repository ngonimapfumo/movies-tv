package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvShowDetailResponse
import zw.co.nm.moviedb.databinding.ItemSeasonDetailBinding
import zw.co.nm.moviedb.utils.Constants

class SeasonsAdapter(private var data: List<GetTvShowDetailResponse.Season>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemSeasonDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemSeasonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].posterPath
        binding!!.seasonTxt.text = data[position].name
        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath)
            .placeholder(R.drawable.sample_recycler_small)
            .into(binding!!.imageView)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemSeasonDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}