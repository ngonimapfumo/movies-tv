package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemCastBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class TVCastAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse.Cast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemCastBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].profilePath
        binding!!.characterNameTxt.text = data[position].name
        binding!!.characterTxt.text = data[position].character.replace(" ","\n")
        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath).placeholder(R.drawable.sample_cover_small).into(binding!!.castImgView)
        holder.itemView.setOnClickListener {
                PageNavUtils.toPersonDetailsPage(holder.itemView.context,
                    data[position].id)

        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root)
}